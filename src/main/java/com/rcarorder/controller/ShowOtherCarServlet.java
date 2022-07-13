package com.rcarorder.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcar.model.RcarService;
import com.rcarorder.model.RcarOrderService;

import utils.ReDay;

@WebServlet("/showothercar")
public class ShowOtherCarServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String getdate = req.getParameter("date");
		String getstore = req.getParameter("store");
		RcarOrderService orderService = new RcarOrderService();
		RcarService rcarService = new RcarService();
		LocalDate now = LocalDate.parse(getdate);
		//HttpSession session = req.getSession();
		
		
		//@SuppressWarnings("unchecked")// 取得區間內 有調度 "無訂單"車輛
		//List<Car_Dispatch_RecordVO> otherCarDispatch = (List<Car_Dispatch_RecordVO>)session.getAttribute("otherCarDispatch");
		//List<ReDay> nullOrderCar = rcarService.getNullOrderCar(otherCarDispatch);
		
		
		
//		LocalDate now = LocalDate.parse("2022-06-29");
		List<ReDay> list = orderService.getOthercar(getstore, now);
		List<ReDay> otherCar = rcarService.getOtherStoreCar(getstore);
		otherCar.addAll(list); //將無訂單車輛 與 訂單車輛裝載一起
		//otherCar.addAll(nullOrderCar);
		
		/// 轉json
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(otherCar);
		resp.getWriter().write(json);
		return;
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		doGet(req, resp);
	};
}
