package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		MemberService memberService = new MemberService();

		String username = req.getParameter("username"); // 取得使用者帳號
		String password = req.getParameter("password"); // 取得使用者密碼

		HttpSession session = req.getSession();

		// 格式正確登入

		if (session.getAttribute("account") == null) { // 判斷使否登入過
			MemberVO Login = memberService.login(username, password);
			if (Login == null) {
				out.write("false");
				System.out.println("登入失敗，請重新登入");
				return;
			} else {
				// 登入成功
				session.setAttribute("account", Login);
				System.out.println("登入成功");

				try {
					String Location = (String) session.getAttribute("location"); // 取出你從哪個受保護網頁導到登入畫面
					if (Location != null && Location.equals("/CGA102G4/front_end/member/ForgotPassword.html") && Location.equals("/CGA102G4/front_end/member/mailcheck.jsp") ) {
						session.removeAttribute("location"); // 刪掉這個session資料
						out.write(Location);
						return;
					} else { // 要是不是從受保護網頁登入則轉到首頁
						out.write(req.getContextPath() + "/index");
						return;
					}
				} catch (Exception e) {

				}
			}
		} else { // 登出
			session.removeAttribute("account");
			resp.sendRedirect(req.getContextPath() + "/index");
			return;
		}

	}
	
}
