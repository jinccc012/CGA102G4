package com.rcarorder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carrentable.model.CarRentableService;
import com.event.model.EventInformationService;
import com.member.model.MemberVO;
import com.rcarorder.model.OrderPaymentService;
import com.rcarorder.model.OrderPointService;
import com.rcarorder.model.RcarOrderService;
import com.rcarorder.model.RcarOrderVO;
import com.member.*;
@WebServlet({"/order/cancel","/order/memberOrders","/order/memberOrdersDesc","/order/memberOrderDetail"})
public class RcarOrderServlet extends HttpServlet{
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
		
		String servletPath = request.getServletPath();
		String action = request.getParameter("action");
		
		// ************從session裡面拿到登入的帳號資訊***************
		MemberVO memberVO =  (MemberVO) request.getSession().getAttribute("account");
		String meb_no = memberVO.getMeb_no();
		
		// 取消訂單
		if ("cancelOrder".equals(action)) {
			int rcaro_no =Integer.parseInt(request.getParameter("rcaro_no"));
			RcarOrderService rcar_OrderService = new RcarOrderService();
			rcar_OrderService.cancelOneOrder(rcaro_no);
			response.sendRedirect(request.getContextPath()+"/order/memberOrders");
		}
		
		// 查詢全部訂單(遞增)
		if ("/order/memberOrders".equals(servletPath)) {
			System.out.println("/order/memberOrders");
			// 呼叫service進行查詢
			RcarOrderService rcar = new RcarOrderService();
			List<RcarOrderVO> list = rcar.getMemberOrders(meb_no);
			request.setAttribute("orderby", "/order/memberOrdersDesc");
			request.setAttribute("imgtype", "up");
			request.setAttribute("list", list);
			request.getRequestDispatcher("/front_end/order/memberOrders.jsp").forward(request, response);
		}
		
		// 查詢全部訂單(遞減)
		if ("/order/memberOrdersDesc".equals(servletPath)) {
			RcarOrderService rcar = new RcarOrderService();
			List<RcarOrderVO> list = rcar.getMemberOrdersDesc(meb_no);
			request.setAttribute("orderby", "/order/memberOrders");
			request.setAttribute("imgtype", "down");
			request.setAttribute("list", list);
			request.getRequestDispatcher("/front_end/order/memberOrders.jsp").forward(request, response);
		}
		
		// 訂單詳細資訊
		if ("/order/memberOrderDetail".equals(servletPath)) {
			// 取得訂單編號
			int rcaro_no =Integer.parseInt(request.getParameter("rcaro_no"));
			RcarOrderService rcar_OrderService = new RcarOrderService();
			RcarOrderVO rcar_OrderVO = rcar_OrderService.getMemberOrderDetail(rcaro_no);
			request.setAttribute("rcar_OrderVO", rcar_OrderVO);
			request.getRequestDispatcher("/front_end/order/memberOrderDetail.jsp").forward(request, response);
		}
	}
	

	
}
