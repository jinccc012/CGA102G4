package com.linenotify.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.*;
@WebServlet("/LineURLServlet")
public class LineURLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LineURLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("LineURLServlet doPost()");
		
//		LineUrlClass.NGROK_URL = req.getParameter("ngrok");
//		LineUrlClass.CLIENT_ID = req.getParameter("client_id");
//		LineUrlClass.CLIENT_SECRET = req.getParameter("client_secret");
		
//		LineUrlClass.display();
	}

}
