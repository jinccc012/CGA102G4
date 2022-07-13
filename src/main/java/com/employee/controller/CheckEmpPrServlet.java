package com.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeVO;
import com.google.gson.Gson;
import com.permission.model.PermissionService;
import com.permission.model.PermissionVO;

@WebServlet("/checkempprservlet")
public class CheckEmpPrServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		確認權限
		
		HttpSession session = req.getSession();
		EmployeeVO emp = (EmployeeVO)session.getAttribute("employee");
		
		PermissionService service = new PermissionService();
		List<PermissionVO> empPr = service.getByEmpNo(emp.getEmp_no());
		
		
		Gson gson = new Gson();
		String json = gson.toJson(empPr);
		resp.getWriter().write(json);
		System.out.println(json);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
