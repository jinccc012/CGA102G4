package com.rcar.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;

@WebServlet("/back_end/rcar.do")
public class RcarServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("update".equals(action)) { // 來自Rcar.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				String rcarno = req.getParameter("rcarno");
				String storeno =req.getParameter("storeno");
				String modelno =req.getParameter("modelno");
				String loc =req.getParameter("loc");
				Integer miles =Integer.valueOf(req.getParameter("miles"));
				Integer rcarStatus =Integer.valueOf(req.getParameter("rcarStatus"));
				if(rcarStatus==-1) {
					errorMsgs.add("請選擇欲修改之狀態");
				}
				
				
				/***************************2.開始修改****************************************/
				
				RcarService rcarSvc = new RcarService();
				RcarVO rcarVO = rcarSvc.getCar(rcarno);
				rcarVO.setSt_no(storeno);
				rcarVO.setModel_no(modelno);
				rcarVO.setRcar_loc(loc);
				rcarVO.setMiles(miles);
				rcarVO.setRcar_status(rcarStatus);
				System.out.println(rcarVO.toString());
				boolean update = rcarSvc.update(rcarVO);
				System.out.println(update);
				res.getWriter().write("true");
				/***************************3.完成,準備轉交(Send the Success view)************/
				
//				String url = "/back_end/rcarManager/rcar.html";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
				System.out.println("update");
			
				
		}
		
        if ("insert".equals(action)) {//ADD
//			System.out.println("insert");
        	RcarVO rcarVO = new RcarVO();
			
        	rcarVO.setRcar_no(req.getParameter("rcarno"));
        	rcarVO.setSt_no(req.getParameter("storeno"));
        	rcarVO.setModel_no(req.getParameter("modelno"));
        	rcarVO.setMiles(Integer.valueOf(req.getParameter("miles")));
        	rcarVO.setRcar_loc(req.getParameter("storeno"));
        	rcarVO.setRcar_status(1);
			
        	RcarService rcarSvc =new RcarService();
			
			rcarSvc.insert(rcarVO);
			res.getWriter().write("true");
//			System.out.println(rcarVO.toString());
//			String url = "/back_end/rcarManager/rcar.html";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交
//			successView.forward(req, res);
		}
	}

}
