package com.rcarorder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carrentable.model.CarRentableService;
import com.event.model.EventInformationService;
import com.linenotify.model.LineSendMessageService;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.rcarorder.model.OrderPaymentService;
import com.rcarorder.model.OrderPointService;
import com.rcarorder.model.RcarOrderService;
import com.rcarorder.model.RcarOrderVO;

@WebServlet("/RcarOrder/RcarOrderServlet")
public class MakeOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
				// 下訂單(未付款)使用者填單
				if("try_make_an_order".equals(action)) {
					tryMakeAnOrder(request, response);
					return;
				}
				
				// 下訂單(付款)使用者付款
				if("pay_order".equals(action)) {
					payOrder(request, response);
					return;
				}
				
	}
	
	protected void tryMakeAnOrder(HttpServletRequest req, HttpServletResponse res) {						
		Map<String,String> errorMessage = new LinkedHashMap<String,String>();
//		System.out.println("我是RcarOrderServlet");
		RcarOrderVO orderVO = new RcarOrderVO();
		
		String lesseeName = req.getParameter("lessee_name");
		String carLevel = req.getParameter("car_level");
		String carModel = req.getParameter("car_model");
		String  startStore = req.getParameter("start_store");
		String returnStore = req.getParameter("return_store");
		String orderCarTime = req.getParameter("order_car_time");
		String orderReturnCarTime = req.getParameter("order_return_car_time");
		
		MemberVO memberVO = (MemberVO)req.getSession().getAttribute("account");
		orderVO.setMeb_no(memberVO.getMeb_no());
		
		
		if("".equals(lesseeName)) {
			errorMessage.put("lesseeName","請填入駕駛人姓名");	
		}
		
		if("unpicked".equals(carLevel)) {
			errorMessage.put("carLevel", "請選擇車量級距");
		}
		
		if("unpicked".equals(carModel)) {
			errorMessage.put("carModel", "請選擇車型");
		}
		
		if("unpicked".equals(startStore)) {
			errorMessage.put("startStore", "請選擇起租站點");
		}
		
		if("unpicked".equals(returnStore)) {
			errorMessage.put("returnStore", "請選擇還車站點");
		}
		
		if("".equals(orderCarTime)) {
			errorMessage.put("order_car_time", "請選擇取車時間");
		}else {
			orderVO.setRcaro_ppicktime(Timestamp.valueOf(orderCarTime +":00")); //debug: valueOf 必須是完整格式
		}
		
		if("".equals(orderReturnCarTime)) {//
			errorMessage.put("order_return_car_time", "請選擇還車時間");
		}else {
			
			orderVO.setRcaro_pprettime(Timestamp.valueOf(orderReturnCarTime+":00"));
		}
		
		// 0711 DEBUG MOVE HERE
		orderVO.setLessee_name(lesseeName);
		orderVO.setModel_no(carModel);
		orderVO.setLevel_no(carLevel);//0624 新增
		orderVO.setRcaro_pickuploc(startStore);
		orderVO.setRcaro_returnloc(returnStore);
		//
		
//		req.setAttribute("orderVO", orderVO);//0711 DEBUG
		req.getSession().setAttribute("orderVO", orderVO);
		
		if(!errorMessage.isEmpty()) {
			req.setAttribute("errorMessage", errorMessage);
			RequestDispatcher fail = req.getRequestDispatcher("/front_end/rcar_order/makeOrder.jsp");
			try {
				fail.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		// 0711 DEBUG ORIGINAL POSITION
		
		//
		/***************************2.判斷是否有車，有車才能往下付錢 ***************************************/
		CarRentableService rentableSVC = new CarRentableService();
		
		
		boolean flag = rentableSVC.hasCar(
									orderVO.getRcaro_pickuploc(),
									orderVO.getModel_no(),
									orderVO.getRcaro_ppicktime(),
									orderVO.getRcaro_pprettime()
						);
		if(!flag) {
			errorMessage.put("no_car_to_rent", "true");
			RequestDispatcher fail = req.getRequestDispatcher("/front_end/rcar_order/makeOrder.jsp");
			try {
				fail.forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		errorMessage.put("no_car_to_rent", "false");
		req.setAttribute("errorMessage", errorMessage);

		/***************************3.目前有車,準備轉交(Send the Success view)*************/
		OrderPaymentService paySVC = new OrderPaymentService();
		EventInformationService eventSVC = new EventInformationService();
		
		// 設定原本金額
		orderVO.setRcaro_pay(paySVC.originOrderPay(orderVO));
		req.setAttribute("originalPay", orderVO.getRcaro_pay());
		
		// 設定套用合適活動金額
		orderVO.setRcaro_pay(paySVC.applyEventDiscount(orderVO));
		// 設定套用活動編號(代改良 現在寫得很醜) 如果沒有合適活動 此欄位填-1
		orderVO.setEvent_no((Integer)eventSVC.getEventForOrder(orderVO).get("eventNo"));
		
		req.getSession().setAttribute("eventTitle", (String)eventSVC.getEventForOrder(orderVO).get("eventTitle"));
		req.setAttribute("orderVO", orderVO);
		
		req.getSession().setAttribute("orderVO", orderVO);
		
		RequestDispatcher goToPayment = req.getRequestDispatcher("/front_end/rcar_order/payOrder.jsp");
		try {
			goToPayment.forward(req, res);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void payOrder(HttpServletRequest req, HttpServletResponse res) {
		Map<String,String> errorMsgs = new HashMap<String, String>();
		
		RcarOrderVO orderVO = (RcarOrderVO)req.getSession().getAttribute("orderVO");
		
		String cardNo = req.getParameter("card_no").trim();
		String cardDate = req.getParameter("card_date").trim();
		String cardValidate = req.getParameter("card_validate").trim();
		Integer consumePoint = Integer.valueOf(req.getParameter("consumePoint"));
		
		String cardNoReg = "^[(0-9)]{16}$";
		String cardValidateReg = "^[0-9]{3}$";
		
		
		
		if("".equals(cardNo)) {
			errorMsgs.put("card_no", "請輸入卡號");
		}else if(!cardNo.matches(cardNoReg)) {
			errorMsgs.put("card_no", "請輸入16碼卡號，卡號僅為0-9之數字");
		}
	
		
		if("".equals(cardDate)) {
			errorMsgs.put("card_date", "請輸入卡片到期月");
		}
		
		if("".equals(cardValidate)) {
			errorMsgs.put("card_validate", "請輸入卡片末三碼");
		} else if(!cardValidate.matches(cardValidateReg)) {
			errorMsgs.put("card_validate", "請輸入卡片末三碼，卡片末三碼僅為0-9之數字");
		}
		
		req.setAttribute("errorMsgs", errorMsgs);
		
		if(!errorMsgs.isEmpty()) {
			String url = "/front_end/rcar_order/payOrder.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			try {
				rd.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		/********************* 永續層存取         ************************/
		OrderPointService pointSVC = new OrderPointService(orderVO);
		OrderPaymentService paySVC = new OrderPaymentService();
		
		orderVO.setMeb_no(  ((MemberVO)req.getSession().getAttribute("account")).getMeb_no()  );
		orderVO.setConsume_point(consumePoint);
		
		pointSVC.consumeMemberPoint(consumePoint);
		pointSVC.earnPoint(orderVO);// 0713 DEBUG
		paySVC.applyPoint(orderVO, consumePoint);
		
		RcarOrderService orderSVC = new RcarOrderService();
//		orderSVC.insert(orderVO);
		
		
		CarRentableService rentableSVC = new CarRentableService();
		
		
		boolean hasCar = rentableSVC.hasCar(
									orderVO.getRcaro_pickuploc(),
									orderVO.getModel_no(),
									orderVO.getRcaro_ppicktime(),
									orderVO.getRcaro_pprettime()
						);
		
		
		if(!hasCar) {
			errorMsgs.put("no_car_to_rent", "true");
			req.setAttribute("errorMsgs", errorMsgs);
			
			RequestDispatcher fail = req.getRequestDispatcher("/front_end/rcar_order/makeOrder.jsp");
			try {
				fail.forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
//		System.out.println("MakeOrderServlet@268 getEarn_point ->" + orderVO.getEarn_point());
//		orderSVC.insert(orderVO);
//		req.setAttribute("orderSuccess", "orderSuccess");
		
		int orderID = orderSVC.insert(orderVO);
		
		try {
			sendMessageAfterOrder(orderVO,orderID);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		/* 轉交 */
		//RequestDispatcher goToOrderDetail = req.getRequestDispatcher("/order/memberOrdersDesc");
		try {
			res.sendRedirect("/CGA102G4/order/memberOrdersDesc");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void sendMessageAfterOrder(RcarOrderVO orderVO, int orderID) throws Exception {
		String mebNo = orderVO.getMeb_no();
		MemberVO member = new MemberService().member(mebNo);
		
		String store = "";
		if(orderVO.getRcaro_pickuploc().equals("TPE")) {
			store = "台北分店";
		}else if(orderVO.getRcaro_pickuploc().equals("TC")) {
			store = "台中分店";
		}else {
			store = "高雄分店";
		}
		
		String time = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(orderVO.getRcaro_ppicktime());
		
		String msg = 	"親愛的 " + member.getMeb_name() + "先生/小姐您好\n" 
					+	"您已於Family Rent訂車\n" 
					+	"訂單編號: " + orderID + "\n"
					+	"請於 " + time + "攜帶身分證、駕照至" + store +"取車\n" 
					+	"感謝您的訂購";
		
		LineSendMessageService.notifyMember(mebNo, msg);
	}
	
}
