	package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.member.model.MemberService;


@WebServlet("/memberpassedit")
@MultipartConfig
public class MemberPassEdit extends HttpServlet{
	MemberService service = new MemberService();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//解碼
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out =  res.getWriter();
		String meb_no = req.getParameter("meb_no");
		String oPassword = req.getParameter("oPassword");
		String nPassword = req.getParameter("nPassword");
		String confirmPassword = req.getParameter("confirmPassword");

		Map<String,String> message =new LinkedHashMap<String,String>();
		
		if(oPassword == null || oPassword.trim().length()==0) {
			message.put("oPassword","舊密碼不得為空");}
		else if(!service.checkPass(meb_no, oPassword)){
			message.put("oPassword","舊密碼輸入錯誤");}
		if(nPassword == null || nPassword.trim().length()==0)
			message.put("nPassword","新密碼不得為空");
		else if(nPassword.trim().length()<6 ||  nPassword.trim().length()>12)
			message.put("nPassword","新密碼長度需為6~12");
		if(confirmPassword == null || confirmPassword.trim().length()==0)
			message.put("confirmPassword","確認密碼不得為空");
		else if(!confirmPassword.equals(nPassword))
			message.put("confirmPassword","新密碼與確認密碼需相等");
		
		if(message.isEmpty()) {
			service.updatePass(meb_no, nPassword);
			message.put("sucessmsg","修改密碼成功!");
		}
		Gson gson =new Gson();
		String messageMap = gson.toJson(message);
		out.print(messageMap);
		out.close();
	}

}
