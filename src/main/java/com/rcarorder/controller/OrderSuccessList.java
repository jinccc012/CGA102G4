package com.rcarorder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcarorder.model.RcarOrderService;
import com.rcarorder.model.RcarOrderVO;

@WebServlet("/OrderSuccessList")
public class OrderSuccessList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderSuccessList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RcarOrderService svc = new RcarOrderService();
		List<RcarOrderVO> list = svc.getByOrderStatus(0);
		
		request.setAttribute("getByCompositeQuery", list);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/back_end/Rcar_Order/RcarOrder.jsp");
		requestDispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
