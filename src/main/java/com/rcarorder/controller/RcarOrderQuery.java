package com.rcarorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rcarorder.model.RcarOrderService;
import com.rcarorder.model.RcarOrderVO;

/**
 * Servlet implementation class RcarOrderQuery
 */
@WebServlet("/RcarOrder/RcarOrderQuery")
public class RcarOrderQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RcarOrderQuery() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String orderId = req.getParameter("order_id");
		
		RcarOrderVO order = new RcarOrderService().getMemberOrderDetail(Integer.valueOf(orderId));
		
		Gson g = new Gson();
		String orderStr = g.toJson(order);

		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(orderStr);
		out.close();
	}

}
