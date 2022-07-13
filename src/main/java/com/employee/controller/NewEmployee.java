package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;

@WebServlet("/NewEmployee")
public class NewEmployee extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		EmployeeService employeeService = new EmployeeService(); // 呼叫service
		PrintWriter out = resp.getWriter();
		EmployeeVO employeeVO = new EmployeeVO();

//		EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
//		String maxempno = employeeDAOImpl.maxempno(); //目前無工用
//		System.out.println(maxempno);
		
		// 姓名
		String name = req.getParameter("Name1");
		employeeVO.setEmp_name(name);

		// 員工生日不能小於20歲
		String bir = req.getParameter("bd1");
		if (bir != null) {
			LocalDate date = LocalDate.parse(bir);// 使用者輸入日期
			LocalDate now = LocalDate.now();// 取得現在日期
			int nowyear = now.getYear();
			int year = date.getYear();
			if ((nowyear - year) < 20) { // 不可小於20歲
				return;
			} else {
				employeeVO.setEmp_bir(Date.valueOf(LocalDate.parse(bir))); // 強轉型成Date格式
			}

		}
		// 判斷性別不可為空值
		String gender = req.getParameter("gender");
		if (gender != null) { // 輸入不可為空值
			gender = gender.trim(); // 去空白
			int genderInt = Integer.parseInt(gender); // 轉型
			if (genderInt < 3) {
				employeeVO.setEmp_gender(genderInt);
			}
		}
		// 門市
		String store = req.getParameter("store");
		employeeVO.setSt_no(store);
		// mail
		String email = req.getParameter("email");
		employeeVO.setEmp_mail(email);
		// 手機
		String phone = req.getParameter("phone");
		if (phone != null) { // 不可為空值
			phone = phone.trim(); // 去空白
			String pattern = "^09[0-9]{8}$"; // 判斷手機號碼格式
			if (phone.matches(pattern)) {
				employeeVO.setEmp_tel(phone);
			} else {
				return;
			}
		}
		// 地址
		String street = req.getParameter("street");
		if (street != null) {
			street = street.trim(); // 去空白
			employeeVO.setEmp_adrs(street);
		}
		// 職稱
		String title = req.getParameter("title");
		if (title != null) {
			title = title.trim();
			employeeVO.setEmp_title(title);
		}

		// 判斷員工編號只能是數字
		String id = req.getParameter("id");
		String pattern = "^[0-9]*$";
		if (id != null) {
			id = id.trim();
			if (id.matches(pattern)) {
				employeeVO.setEmp_no(id);
			} else {
				return;
			}
		}

//		判斷密碼是否為6~12碼
		String password = req.getParameter("password");
		if (password != null) {
			password = password.trim(); // 去空白
			if (password.length() >= 6 && password.length() <= 12) {
				employeeVO.setEmp_pass(password);
			} else {
				return;
			}
		}

		// 格式正確執行insert
		boolean insert = employeeService.insert(employeeVO);
		System.out.println(insert);
		if (insert == true) {
			out.write("true");
			System.out.println("新增成功");
		} else {
			out.write("false");
		}
		System.out.println(employeeVO.toString());

	}
}
