package com.rcarorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;
import com.rcarorder.model.OrderPaymentService;
import com.rcarorder.model.RcarOrderService;
import com.rcarorder.model.RcarOrderVO;

@WebServlet("/RcarOrder/RcarOrderDropOff")
public class RcarOrderDropOff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RcarOrderDropOff() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Call doPost once");
		req.setCharacterEncoding("UTF-8");
		String orderId = req.getParameter("order_id");
		String dropOffTimeActual = req.getParameter("RCARO_RRETTIME").replace('T', ' ');
		
		String rcarNo = req.getParameter("RCAR_NO");
		String dropOffLocActual = req.getParameter("RCARO_RETURNLOC_ACTUAL");
		
		
		RcarOrderService orderSVC = new RcarOrderService();
		RcarOrderVO order = orderSVC.getMemberOrderDetail(Integer.valueOf(orderId));
//		
		order.setRcaro_rrettime(Timestamp.valueOf(dropOffTimeActual));
		order.setRcaro_returnloc_actual(dropOffLocActual);
	
		OrderPaymentService paySVC = new OrderPaymentService();
		int extraPay = paySVC.calExtraPay(order);
		int orderStatus = extraPay == 0? 2 : 3;// 結案(2) 未結案(3)
		
		order.setRcaro_extra_pay(extraPay);
		order.setRcaro_extra_pay_status(0);
		order.setRcaro_status(orderStatus);
		
		/****永續層存取****/
		orderSVC.update(order);
		
		RcarService carSVC = new RcarService();
		RcarVO carVO = carSVC.getCar(rcarNo);
		carVO.setRcar_status(3);//還車
		carSVC.update(carVO);
		
		/****轉交****/
		Gson g = new Gson();
		String json = g.toJson(order);
		
		PrintWriter writer = res.getWriter();
		writer.print(json);
		writer.close();
	}

}
