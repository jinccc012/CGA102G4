package com.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/empdata")
public class EmpData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String statusStr = req.getParameter("status");
		HttpSession session = req.getSession();
		EmployeeService service = new EmployeeService();
		if("updata".equals(statusStr)) { //會員自行更動資料
			String jsonStr = req.getParameter("json");
			try {
				EmployeeVO loginemp = (EmployeeVO)session.getAttribute("employee");
				EmployeeVO employeeVO = new EmployeeVO();
				Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				employeeVO = gson.fromJson(jsonStr, employeeVO.getClass());
				employeeVO.setEmp_pass(loginemp.getEmp_pass());
				System.out.println(employeeVO);
				int update = service.empUpdata(employeeVO);
				if(update == 1) {
					resp.getWriter().write("true");
					loginemp.setEmp_adrs(employeeVO.getEmp_adrs());
					loginemp.setEmp_name(employeeVO.getEmp_name());
					loginemp.setEmp_gender(employeeVO.getEmp_gender());
					loginemp.setEmp_bir(employeeVO.getEmp_bir());;
					loginemp.setEmp_tel(employeeVO.getEmp_tel());
					loginemp.setEmp_mail(employeeVO.getEmp_mail());
					session.setAttribute("employee",loginemp);
					return;
				}else {
					resp.getWriter().write("false");
				}
			} catch (Exception e) {
				resp.getWriter().write("false");
			}
			
		}
		
		if("updatapass".equals(statusStr)) { //更新密碼
			EmployeeVO loginemp = (EmployeeVO)session.getAttribute("employee");
			String passStr = req.getParameter("pass");
			loginemp.setEmp_pass(passStr);
			int update = service.empUpdata(loginemp);
			if(update == 1) {
				resp.getWriter().write("true");
//				session.setAttribute("employee",loginemp);
				session.removeAttribute("employee");
			}else {
				resp.getWriter().write("false");
			}
			return;
		}
		
		if("checkpass".equals(statusStr)) { //確認舊密碼
			EmployeeVO loginemp = (EmployeeVO)session.getAttribute("employee");
			String passStr = req.getParameter("pass");
			
			if(loginemp.getEmp_pass().equals(passStr)) {
				resp.getWriter().write("true");
			}else {
				resp.getWriter().write("false");
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
