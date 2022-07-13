package com.rcarorder.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;
import com.rcarorder.model.RcarOrderService;
import com.store.model.StoreService;
import com.store.model.StoreVO;

import utils.ReDay;

@WebServlet("/storeshow")
public class StoreShow extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String getclick = req.getParameter("click");
		String getmonth = req.getParameter("month");
		String getstore = req.getParameter("store");
//		String getother = req.getParameter("other");
		HttpSession session = req.getSession();
		RcarService rcarService = new RcarService();
		RcarOrderService orderService = new RcarOrderService();

//		if(getother != null) { //取得外站車輛
//			int month = Integer.parseInt(getmonth);
////			List<RcarVO> list = rcarService.getOtherCar("TC");
////			List<ReDay> getallday = orderService.getallday(list, month);
////			LocalDateTime now = LocalDateTime.parse("2022-06-29T13:18:38");
//			LocalDate now = LocalDate.parse("2022-06-29");
//			List<ReDay> list = orderService.getOthercar("TC", now);
//			List<ReDay> otherCar = rcarService.getOtherStoreCar("TC");
//			otherCar.addAll(list); //將無訂單車輛 與 訂單車輛裝載一起
//			/// 轉json
//			ObjectMapper mapper = new ObjectMapper();
//			String json = mapper.writeValueAsString(otherCar);
//			resp.getWriter().write(json);
//			return;
//		}

		//////////////
		if (getmonth != null) { // 初始會經過
			LocalDate localDate = LocalDate.parse(getmonth);
			if(getstore.equals("TPEHO")) {
				getstore = "TPE";
			}
			List<RcarVO> list2 = rcarService.getSt_noAll(getstore);
			List<ReDay> getallday = orderService.getallday(list2, localDate);
			/// 轉json
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(getallday);
			resp.getWriter().write(json);
			return;
		}

		if (getclick == null) {
			List<StoreVO> all;
			// 重新整理會經過 取得所有門市資訊
			if (session.getAttribute("store") == null) {
				StoreService sc = new StoreService();
				all = sc.getAll();
				session.setAttribute("store", all);
			} else {
				all = (List<StoreVO>) session.getAttribute("store");
			}
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(all);
			resp.getWriter().write(json);
			return;
			
			// 先將所有門市車輛訊息存入session 以便切換調用 可以不用一直呼叫連線池 //目前有BUG 車輛更動 無法及時更新
//			if (session.getAttribute("TPE") == null) {
//				for (StoreVO store : all) {
//					//RcarDAOImpl rcarDAOImpl = new RcarDAOImpl();
//					List<RcarVO> list = rcarService.getSt_noAll(store.getSt_no());
//					session.setAttribute(store.getSt_no(), list);
//				}
//			}

		} else {
			// 切換門市,秀出該門市所有車輛
			String city = req.getParameter("store");
			String month = req.getParameter("changemonth");
			LocalDate date = LocalDate.parse(month);
			System.out.println(city);
			// 取出相對應的session 可以不用調用連線池 //目前有BUG 車輛更動 無法及時更新
//			@SuppressWarnings("unchecked")
//			List<RcarVO> list = (List<RcarVO>) session.getAttribute(city);

			List<RcarVO> list = rcarService.getSt_noAll(city);
			List<ReDay> getallday = orderService.getallday(list, date);
			/// 轉json
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(getallday);
			resp.getWriter().write(json);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);

	}
}
