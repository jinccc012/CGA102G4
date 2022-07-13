package com.rcarorder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;
import com.rcarorder.model.RcarOrderService;
import com.rcarorder.model.RcarOrderVO;

@WebServlet("/disCar.do")
public class DisCarServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		System.out.println("discarServlet");
		req.setAttribute("errorMsgs", errorMsgs);
		
		/***************************1.接收請求參數****************************************/
		Integer orderNo = Integer.valueOf(req.getParameter("rcarO"));
		String rcarNo =req.getParameter("rcarno");
		
		
		
		/***************************2.開始修改****************************************/
		RcarOrderService orderSvc = new RcarOrderService();
		RcarOrderVO orderVO = orderSvc.getMemberOrderDetail(orderNo);
		orderVO.setRcar_no(rcarNo);
		orderSvc.update(orderVO);
		/***************************3.完成,準備轉交(Send the Success view)************/
		String url = "/back_end/cardispatcher/DisCarJsp.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
		successView.forward(req, res);
	}

}
