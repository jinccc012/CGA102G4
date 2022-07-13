package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.google.gson.Gson;

@WebServlet("/EmployeeLogin")
public class EmployeeLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		EmployeeService employeeService = new EmployeeService(); // 呼叫Service

		String username = req.getParameter("username"); // 取得帳號
		String password = req.getParameter("password"); // 取得密碼

		HttpSession session = req.getSession();

		// 判斷是否登入過
		if (session.getAttribute("employee") == null) {
			System.out.println(username);
			System.out.println(password);
			EmployeeVO emplogin = employeeService.emplogin(username, password);
			if (emplogin == null) {
				// 登入失敗
				out.write("false");
				System.out.println("登入失敗");
				return;
			} else {
				// 登入成功
				session.setAttribute("employee", emplogin);
				System.out.println("登入成功");

				
				Gson gson = new Gson();
				String json = gson.toJson(emplogin);
				session.setAttribute("employeejson", json);
				try {
					String Location = (String) session.getAttribute("location");
					if (Location != null) {
						session.removeAttribute("location"); // 刪除這筆session資料
						out.write(Location);
					} else {
						// 要是重不是保護的網頁登入，則導入首頁
						out.write(req.getContextPath() + "/back_end/ReviseEmp/empUpdata.jsp"); 
						return;
					}
				} catch (Exception e) {

				}
			}
		} else { //登出
			session.removeAttribute("employee");
			session.removeAttribute("employeejson");			
			resp.sendRedirect(req.getContextPath() + "/back_end/EmployeeLogin/EmployeeLogin.html");
			return;
		}
	}
}
