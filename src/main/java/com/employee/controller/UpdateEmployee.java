package com.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.EmployeeDAOImpl;
import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");// 第一步驟
		EmployeeService employeeService = new EmployeeService(); // 第二步驟
		String status = req.getParameter("status");
		if ("getall".equals(status)) {
			List<EmployeeVO> all = employeeService.getAll(); // 第三步驟
			Gson create = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();// 轉gson格式
			String json = create.toJson(all);
			resp.getWriter().write(json);
			return;
		}
		
		if("update".equals(status)) {
			String name = req.getParameter("name");           //用update方法修改各欄位的值
			String storeno = req.getParameter("storeno");
			String phone = req.getParameter("phone");
			String adrs = req.getParameter("adrs");
			String email = req.getParameter("email");
			String title = req.getParameter("title");
			String parameter = req.getParameter("statue");
			String id = req.getParameter("empno");
			try {
				EmployeeVO employeeVO = new EmployeeVO();   //呼叫EmployeeVO
				 employeeVO.setEmp_name(name);               //用EmployeeVO把值傳回到資料庫
				 employeeVO.setSt_no(storeno);
				 employeeVO.setEmp_tel(phone);
				 employeeVO.setEmp_adrs(adrs);
				 employeeVO.setEmp_mail(email);
				 employeeVO.setEmp_title(title);
				 employeeVO.setEmp_no(id);
				 employeeVO.setEmp_status(Integer.parseInt(parameter));
				
				 EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
				 boolean update = employeeDAOImpl.update(employeeVO);
				 if(update) {
					resp.getWriter().write("true");
				 }else {
					 resp.getWriter().write("false");
				 }
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				 resp.getWriter().write("false");
			}
			 
		}
		
		
		

		// getServletContext() 最大範圍(大家共用)
		// getSession() 中等範圍
		// request 小範圍(我給你才可以使用)
//		 req.setAttribute("member", json);
//		 req.getRequestDispatcher("").forward(req, resp);

	}

}
