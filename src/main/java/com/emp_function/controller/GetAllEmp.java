package com.emp_function.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp_function.model.Emp_functionService;
import com.emp_function.model.Emp_functionVO;
import com.google.gson.Gson;

@WebServlet("/GetAllEmp")
public class GetAllEmp  extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String statusStr = req.getParameter("status");
		Emp_functionService emp_functionService = new Emp_functionService();
		if("getall".equals(statusStr)) {
			List<Emp_functionVO> all = emp_functionService.getAll();
			
			Gson gson = new Gson();
			resp.getWriter().write(gson.toJson(all));
		}
		 
		
	}
	

}
