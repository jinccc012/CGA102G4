package com.linenotify.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linenotify.model.LineNotifyService;
import com.linenotify.model.LineNotifyVO;
import com.linenotify.model.LineSendMessageService;


@WebServlet("/Member/LineBindFamilyRentServlet")
public class LineBindFamilyRentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LineBindFamilyRentServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String mebNo = req.getParameter("meb_no");
		String token = (String)req.getSession().getAttribute("theToken");
		
		System.out.println("mebNo 是 " + mebNo);
		System.out.println("Token 是 " + token);
		
		LineNotifyService lineSVC = new LineNotifyService();
		LineNotifyVO lineVO = new LineNotifyVO();
		lineVO.setMebNo(mebNo);
		lineVO.setCode("");
		lineVO.setState("");
		lineVO.setLineToken(token);
		
		
		if(lineSVC.getOne(mebNo) == null) {
			lineSVC.insert(lineVO);
		}else {
			lineSVC.update(lineVO);
		}
		
		try {
			LineSendMessageService.notifyMember(mebNo, "您的「Family Rent Line服務」已經註冊成功，此為測試訊息(By Darren Ho)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}

}
