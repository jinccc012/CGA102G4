package com.rcarorder.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rcarorder.model.RcarOrderService;
import com.rcarorder.model.RcarOrderVO;


@WebServlet("/RcarOrder/RcarOrderExtraPayServlet")
public class RcarOrderExtraPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RcarOrderExtraPayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("order_id");
		RcarOrderService orderSVC = new RcarOrderService();
		RcarOrderVO order = orderSVC.getMemberOrderDetail(Integer.valueOf(id));
		order.setRcaro_extra_pay_status(1);// 付款改 已付款
		order.setRcaro_status(2);//訂單改 已結案
		orderSVC.update(order);
		
		Gson g = new Gson();
		String json = g.toJson(order);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(json);
		out.close();
		return;
	}

}
