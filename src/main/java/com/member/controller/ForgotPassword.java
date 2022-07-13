package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAOImpl;

import utils.JMail;

@WebServlet("/ForgotPassword")
public class ForgotPassword  extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		HttpSession session = req.getSession();
		ServletContext context = getServletContext();
		PrintWriter out = resp.getWriter();
		MemberDAOImpl memberDAOImpl = new MemberDAOImpl();
		//判斷輸入的email資料庫是否有資料
		String mail = req.getParameter("email");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		System.out.println(mail);
		System.out.println(id);
		boolean verifyid = memberDAOImpl.verifyid(mail, id);
		System.out.println(verifyid);
		if(verifyid == true) {  //mail&id正確時判斷
			int asInt = new Random().ints(0,100).limit(1).findFirst().getAsInt();
			String newint = String.valueOf(asInt);
			System.out.println(asInt);
			//String check = "http://10.1.18.115:8081/CGA102G4/mailverify?acc="+id+"&&num="+asInt;
			InetAddress addr = InetAddress.getLocalHost();
		    
			String check = "<a href=\""+"http://"+addr.getHostAddress()+":8081/CGA102G4/mailverify?acc="+id+"&&num="+asInt +"\">點擊驗證</a>";
			//session.setAttribute(id, asInt);
			JMail.Send_mail(mail, "驗證信",check);
			Map<String, String> meb = new HashMap<String, String>();
			meb.put("id", id);
			meb.put("password", password);
			meb.put("int", newint);
			context.setAttribute(id, meb);
			out.write("true");
//			boolean updatepassword = memberDAOImpl.updatepassword(id, password);
//			if(updatepassword) {
//				out.write("true");
//				System.out.println("修改成功");
//			}
		}
		
		
		
		
		
		
	}
	
}
