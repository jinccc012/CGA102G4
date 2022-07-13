package com.rcarorder.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcarorder.model.RcarOrderService;
import com.rcarorder.model.RcarOrderVO;

@WebServlet("/rcarOrderBackendServlet")
public class RcarOrderBackendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RcarOrderService svc = new RcarOrderService();
		List<RcarOrderVO> list = svc.getAll();
		request.getSession().setAttribute("getByCompositeQuery", list);
		request.getRequestDispatcher("/back_end/Rcar_Order/RcarOrder.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String[]> paramMap = request.getParameterMap();
		RcarOrderService svc = new RcarOrderService();
		List<RcarOrderVO> list = svc.getByCompositeQuery(paramMap);
		
		request.getSession().setAttribute("getByCompositeQuery", list);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/back_end/Rcar_Order/RcarOrder.jsp");
		requestDispatcher.forward(request, response);
	}

}
