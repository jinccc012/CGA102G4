package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/memberImage")
public class MemberImages extends HttpServlet{
	MemberService service = new MemberService();
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		//you sould check mebvo is exist?
		String meb_no = req.getParameter("meb_no");
		MemberVO m = service.member(meb_no);
		byte[] imageData = null;
		if(m.getMeb_profile() != null)
			imageData = m.getMeb_profile();
		
		res.setContentType("image/jpeg");
		res.getOutputStream().write(imageData);
		res.getOutputStream().flush();
		res.getOutputStream().close();
		
	}

}
