package com.rcarorder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcarorder.model.RcarOrderService;

@WebServlet("/RcarOrder/cancel")
public class Cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cancel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RcarOrderService svc = new RcarOrderService();
		String rcarnoStr = request.getParameter("no"); // 從ajax取
		System.out.println(rcarnoStr);
		int rcarno = Integer.parseInt(rcarnoStr);
		System.out.println(rcarno);
		svc.cancelOneOrder(rcarno);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
