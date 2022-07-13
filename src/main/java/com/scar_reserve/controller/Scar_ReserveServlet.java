package com.scar_reserve.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scar_reserve.model.Scar_ReserveService;
import com.scar_reserve.model.Scar_ReserveVO;

@WebServlet("/scar_ReserveServlet") // annotation註冊
public class Scar_ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	 	Scar_ReserveService svc = new Scar_ReserveService();
	 	List<Scar_ReserveVO> list = svc.getAll();
	 	req.getSession().setAttribute("getByComposite", list);
	 	req.getRequestDispatcher("back_end/Scar_Reserve/Scar_Reserve.jsp").forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOneScar_ReserveByNo".equals(action)) {

			List<String> errorMsgs = new ArrayList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String srNo = req.getParameter("sr_no");
			srNo = srNo.trim(); // trim會創造一個新的物件，所以trim完畢須指定給舊的

//			一、.把errormsg放入list中		
//			二.把list放入reqAttribute中  
//			三.forward回原本的jsp

			// *****預約編號搜尋*****
			if ("".equals(srNo)) {

//				一、.把errormsg放入list中 
				errorMsgs.add("請勿輸入空白");

//				二.把list放入reqAttribute中  
				req.setAttribute("errorMsgs", errorMsgs);

//				三.forward回原本的jsp
				String url = "/back_end/Scar_Reserve/Scar_Reserve.jsp"; // 1.指定目的地
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(url); // 2.火車
				requestDispatcher.forward(req, res); // 3. 出發
				return;
			}

			Integer srNoInt = null;
			try {
				srNoInt = Integer.valueOf(srNo);

			} catch (NumberFormatException e) {

//				一、.把errormsg放入list中 
				errorMsgs.add("請輸入數字格式");

//				二.把list放入reqAttribute中  
				req.setAttribute("errorMsgs", errorMsgs);

//				三.forward回原本的jsp
				String url = "/back_end/Scar_Reserve/Scar_Reserve.jsp"; // 1.指定目的地
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(url); // 2.火車
				requestDispatcher.forward(req, res); // 3. 出發
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/

			Scar_ReserveService svc = new Scar_ReserveService();
			Scar_ReserveVO scar_ReserveVO = svc.getOneScar_Reserve(srNoInt);
			if (scar_ReserveVO == null) {
				errorMsgs.add("查無預約編號");
			}

			// 查無編號時，錯誤訊息產生就會跳轉回查詢
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/back_end/Scar_Reserve/Scar_Reserve.jsp");
				fail.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			req.setAttribute("scar_ReserveVO", scar_ReserveVO);
			RequestDispatcher succes = req.getRequestDispatcher("/back_end/Scar_Reserve/Scar_Reserve.jsp");
			succes.forward(req, res);

			/*************************** 1.接收請求參數 ****************************************/

			/*************************** 2.開始查詢資料 ****************************************/

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
		}

		// *****時間搜尋*****
		else if ("getByTime".equals(action)) {
			//1
			String startTime = req.getParameter("sr_start_time");
			String endTime = req.getParameter("sr_end_time");
			//2
			Scar_ReserveService srSVC = new Scar_ReserveService();
			List<Scar_ReserveVO> list = srSVC.getByTime(java.sql.Timestamp.valueOf(startTime),java.sql.Timestamp.valueOf(endTime));
			
			req.setAttribute("listByTime", list);
			//3
			String url = "/back_end/Scar_Reserve/Scar_Reserve.jsp"; // 1.指定目的地
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(url); // 2.火車
			requestDispatcher.forward(req, res); // 3. 出發
			return;
			
		}

		
		// 複合查詢
//		if ("getByComposite".equals(action)) {
//			List<String> errorMsgs = new ArrayList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.將輸入資料轉為Map**********************************/ 
			//採用Map<String,String[]> getParameterMap()的方法 
			//注意:an immutable java.util.Map 
			Map<String, String[]> map = req.getParameterMap();
			
			/***************************2.開始複合查詢***************************************/
			Scar_ReserveService svc = new Scar_ReserveService();
			List<Scar_ReserveVO> list = svc.getAll(map);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.getSession().setAttribute("getByComposite", list);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/back_end/Scar_Reserve/Scar_Reserve.jsp");
			requestDispatcher.forward(req, res);
//		}
	}
}
