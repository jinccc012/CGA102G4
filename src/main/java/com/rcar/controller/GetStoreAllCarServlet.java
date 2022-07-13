package com.rcar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeVO;
import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;

@WebServlet("/getstoreallcarservlet")
public class GetStoreAllCarServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		EmployeeVO emp = (EmployeeVO)session.getAttribute("employee");
		RcarService rcarService = new RcarService();
		List<RcarVO> carAll = rcarService.getSt_noAll(emp.getSt_no());
		req.setAttribute("car", carAll);
		req.getRequestDispatcher("back_end/storecarcontrol/storecarcontrol.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
