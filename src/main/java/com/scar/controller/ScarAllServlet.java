package com.scar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidding.model.BiddingService;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.scar.model.ScarService;
import com.scar.model.ScarVO;
import com.websocket.controller.WebSocket;


@WebServlet({"/scar/scarAuctionAll","/scar/scarAuctionDetail","/scar/offerPrice"})
public class ScarAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String servletPath = request.getServletPath();
		String action = request.getParameter("action");
		// ************從session裡面拿到登入的帳號資訊***************
		MemberVO memberVO =  (MemberVO) request.getSession().getAttribute("account");
		String meb_no = memberVO.getMeb_no();


		/*******************
		 * 顯示全部中古車 *
		 *******************/
		if ("/scar/scarAuctionAll".equals(servletPath)) {
			// 呼叫service
			ScarService ss = new ScarService();
			// 呼叫查詢方法，回傳list
			List<ScarVO> list = ss.getScarOnAuctionInRedis();
			// 將list放進請求中
			request.setAttribute("list", list);
			// 轉發到要顯示的頁面(scarauctionall.jsp)
			request.getRequestDispatcher("/front_end/scar/scarAuctionAll.jsp").forward(request, response);
		}
		
		/****************************
		 * 顯示一台競拍中的中古車 *
		 ****************************/
		if ("scardetail".equals(action)) {
			
			// 取得送過來的中古車編號
			String scar_no = request.getParameter("scar_no");
			// 呼叫service
			ScarService ss = new ScarService();
			MemberService ms = new MemberService();
			String topMebNo = ss.getMebNoInBidRecord(scar_no);
			if(topMebNo != null) {
				String mebName = ms.member(topMebNo).getMeb_name();
				// 目前最高價的人
				request.setAttribute("mebName", mebName);
			}
			// 再呼叫方法取得特定車輛編號, 回傳scarVO物件
			ScarVO scarVO = ss.getOneScarOnAuctionInRedis(scar_no);
			request.setAttribute("scarVO", scarVO);
			request.getRequestDispatcher("/front_end/scar/scarAuctionDetail.jsp").forward(request, response);
		}
		
		/************
		 * 競標出價 *
		 ************/
		if ("iwanttobuy".equals(action)) {
			// 1. 取得參數
			String str = request.getParameter("bid_price");	
			String scar_no = request.getParameter("scar_no");
			ScarService ss = new ScarService();
			
			
			
			// 2. 錯誤處理
			Map<String, String> errorMessages = new LinkedHashMap<String, String>();
			request.setAttribute("errorMessages", errorMessages);
			// 3. 正規表達式
			String priceReg = "^[0-9]*$";
			// 4. 出價的格式判斷
			if ("".equals(str.trim())) {
				errorMessages.put("bid_price", "出價不能為空");
			}else if(!str.trim().matches(priceReg)) {
				errorMessages.put("bid_price", "請輸入正確數字格式");
			}
			// **新增出價人判斷**
			String topOneMebNo = ss.getMebNoInBidRecord(scar_no);
			if(meb_no.equals(topOneMebNo)) {
				errorMessages.put("bid_price", "最高價是你了!");
			}
			// 5. (有錯誤時轉發回去顯示錯誤訊息)
			if (!errorMessages.isEmpty()) {
				ScarVO scarVO = ss.getOneScarOnAuctionInRedis(scar_no);
				MemberService ms = new MemberService();
				String topMebNo = ss.getMebNoInBidRecord(scar_no);
				String mebName = ms.member(topMebNo).getMeb_name();
				// 目前最高價的人
				request.setAttribute("mebName", mebName);
				request.setAttribute("scarVO", scarVO);
//				request.getRequestDispatcher("/front_end/scar/scarAuctionDetail.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/scar/scarAuctionDetail?action=scardetail&scar_no="+scar_no);
				return;
			}
			// 6. 確認正確後轉型成int
			int bid_price = Integer.parseInt((request.getParameter("bid_price")).trim());
			// 7. 呼叫Scarservice方法取得目前最高價
			int maxprice = ss.getOneScarOnAuctionMaxpriceInRedis(scar_no);
			// 8. 判斷價格有沒有大於目前最高價格並且是1000的倍數
			if (bid_price > maxprice && bid_price % 1000 == 0) {
				boolean success = ss.updateOneScarOnAuctionMaxpriceInRedis(scar_no, bid_price, meb_no);
				if (success) {
					ScarVO scarVO = ss.getOneScarOnAuctionInRedis(scar_no);
					//告知其他有參與競標者 目前最高價為
					BiddingService bidservice =new BiddingService();
					Set<String> allpersonOneBid = bidservice.getAllMebBid(scar_no);
					List<String> allperson =new ArrayList<String>();
					allpersonOneBid.forEach((n)->allperson.add(n));
					
					//排除自己
					allperson.remove(meb_no);
					allperson.forEach((n)->System.out.println(n));
					WebSocket push = new WebSocket();
					String message = "您競標中的商品"+scarVO.getScar_brand()+"，目前最高價為"+bid_price;
					push.sentMessageToPerson(allperson, message);
					// 轉交競拍紀錄servlet(BiddingServlet)
					ScarVO scarVO2 = ss.getOneScarOnAuctionInRedis(scar_no);
					request.setAttribute("scarVO", scarVO2);
					// =========7/10新增最高出價人
					MemberService ms = new MemberService();
					String topMebNo = ss.getMebNoInBidRecord(scar_no);
					String mebName = ms.member(topMebNo).getMeb_name();
					// 目前最高價的人
					request.setAttribute("mebName", mebName);
					// =========7/10新增最高出價人
					request.getRequestDispatcher("/front_end/scar/scarAuctionDetail.jsp").forward(request, response);
				}else {
					errorMessages.put("bid_price", "資料庫發生錯誤，請洽技術人員");
					ScarVO scarVO = ss.getOneScarOnAuctionInRedis(scar_no);
					MemberService ms = new MemberService();
					String topMebNo = ss.getMebNoInBidRecord(scar_no);
					String mebName = ms.member(topMebNo).getMeb_name();
					// 目前最高價的人
					request.setAttribute("mebName", mebName);
					request.setAttribute("scarVO", scarVO);
					request.getRequestDispatcher("/front_end/scar/scarAuctionDetail.jsp").forward(request, response);
				}
			}else {
				errorMessages.put("bid_price", "出價須1000為單位且高於目前價格");
				ScarVO scarVO = ss.getOneScarOnAuctionInRedis(scar_no);
				request.setAttribute("scarVO", scarVO);
				request.getRequestDispatcher("/front_end/scar/scarAuctionDetail.jsp").forward(request, response);
			}
		}
		
	}
}
