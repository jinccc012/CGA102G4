package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/Register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		MemberVO memberVO = new MemberVO();
//		Gson gson = new Gson();
//		MemberVO fromJson = gson.fromJson(req.getParameter("xxx"), MemberVO.class);
//		System.out.println(fromJson);
//		判斷密碼是否為6~12碼
		String password = req.getParameter("password");
		if (password != null) {
			password = password.trim(); // 去空白
			if (password.length() >= 6 && password.length() <= 12) {
				memberVO.setMeb_pass(password);
			} else {
				return;
			}
		}

//		判斷姓名	為正確格式
		String firstname = req.getParameter("firstname");
		if (firstname != null) {
			firstname = firstname.trim(); // 去空白
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$"; // 正規表達式判斷姓名於中英文2~10碼
			if (firstname.matches(enameReg)) {
				memberVO.setMeb_name(firstname);
			} else {
				return;
			}
		}
		
//		判斷email為正確格式
		String email = req.getParameter("email");
		if (email != null) {
			email = email.trim(); // 去空白
			String format = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
			if (email.matches(format)) {
				MemberService memberService = new MemberService();
				String cheak = req.getParameter("cheak");
				
				if(memberService.Register(email) && cheak != null) {
					out.print(true);
					return;
				}else if(memberService.Register(email) == false && cheak != null){
					return;
				}
				memberVO.setMeb_mail(email);
			} else {
				return;
			}
		}
		
//		判斷手機為正確格式
		String phone = req.getParameter("phone");
		if (phone != null) {
			phone = phone.trim(); // 去空白
			String pattern = "^09[0-9]{8}$";
			if (phone.matches(pattern)) {
				memberVO.setMeb_tel(phone);
			} else {
				return;
			}
		}
//      生日格式驗證
		String Birthday = req.getParameter("Birthday");
		if (Birthday != null) {
			LocalDate date = LocalDate.parse(Birthday);// 使用者輸入日期
			LocalDate now = LocalDate.now();// 取得現在日期
			int nowyear = now.getYear();
			int year = date.getYear();
			if ((nowyear - year) < 20) {
				return;
			} else {
				memberVO.setMeb_bir(Date.valueOf(LocalDate.parse(Birthday)));
			}

		}

//		地址檢查有無輸入
		String street = req.getParameter("street");
		if (street != null) {
			street = street.trim();
			memberVO.setMeb_adrs(street);
		}
		
//      檢查性別
		String gender = req.getParameter("gender");
		if (gender != null) {
			gender = gender.trim();
			int genderInt = Integer.parseInt(gender);
			if(genderInt < 3 ) {
				memberVO.setMeb_gender(genderInt);
			}
		}
		
//      生分證格式
		String idcard = req.getParameter("IDCARD");
		String idcardReg = "^[A-Z]{1}[0-9]{9}$";
		if (idcard != null) {
			idcard = idcard.trim();
			if (idcard.matches(idcardReg)) {
				memberVO.setMeb_no(idcard);
			} else {
				return;
			}
		}
		
//      格式正確執行insert
		MemberService memberService = new MemberService();
		boolean insert = memberService.insert(memberVO);
		System.out.println(insert);
		if(insert == true) {
			out.write("true");
			System.out.println("新增成功");
		}else {
			out.write("false");
		}
		System.out.println(memberVO.toString());
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
