package com.linenotify.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linenotify.model.LineNotifyService;
import com.linenotify.model.LineNotifyVO;

import utils.GetLineToken;

@WebServlet("/Member/LineServlet")
public class LineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LineServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get in LineServlet doGet()");
	}
	
	// 使用者註冊完 會重導到這隻控制器的POST方法 
	// | 如何拿到code? getP("code");
	// | 如何拿到state? getP("state");
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("進來LineServlet的doPost");
		
		LineNotifyService lineSVC = new LineNotifyService();
		
		// 應改成從session中拿到會員資料
		// 0710 測試無法從請求參數 會導致API路徑錯誤
		String meb = "B123456789";// 目前寫死
		
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		
//		LineNotifyVO lineVO = new LineNotifyVO();
//		lineVO.setMebNo(meb);
//		lineVO.setCode(code);
//		lineVO.setState(state);
//		lineVO.setLineToken("");
		System.out.println("code is "+code);
		
//		if(lineSVC.getOne(meb) == null) {
//			lineSVC.insert(lineVO);
//		}else {
//			lineSVC.update(lineVO);
//		}
		
		// 去拿token
		try {
			String lineToken = GetLineToken.getLineToken(code);
//			lineVO.setLineToken(lineToken);
//			lineSVC.update(lineVO);
			
			req.getSession().setAttribute("theToken", lineToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher finish = req.getRequestDispatcher("/front_end/line_notify/LineBindFamilyRent.html");
		finish.forward(req, res);
		return;
	}

}


