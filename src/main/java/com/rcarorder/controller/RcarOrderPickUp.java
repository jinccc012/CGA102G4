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
import com.rcarorder.model.RcarOrderService;
import com.rcarorder.model.RcarOrderVO;

@WebServlet("/RcarOrder/RcarOrderPickUp")
public class RcarOrderPickUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RcarOrderPickUp() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Call pick UP doPost()");
		String orderId = req.getParameter("order_id");
		String pickUpTime = req.getParameter("RCARO_RPICKTIME");
		String rcarNo = req.getParameter("RCAR_NO");
		
		RcarOrderService orderSVC = new RcarOrderService();
		RcarOrderVO order = orderSVC.getMemberOrderDetail(Integer.valueOf(orderId));
		
		
		
		pickUpTime = pickUpTime.replace('T', ' ');
//		System.out.println(pickUpTime);
		order.setRcaro_rpicktime(Timestamp.valueOf(pickUpTime));
		order.setRcaro_status(1);
		orderSVC.update(order);
		
		RcarService carSVC = new RcarService();
		RcarVO carVO = carSVC.getCar(rcarNo);
		carVO.setRcar_status(2);//出車
		carSVC.update(carVO);
		
		// Response
		Gson g = new Gson();
		
		String json = g.toJson(order);
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(json);
		out.close();
		return;
	}

}
