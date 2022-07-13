package com.qa.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qa.model.QAService;
import com.qa.model.QAVO;
import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;

@WebServlet("/back_end/qa.do")
public class QAServlet extends HttpServlet {
	
	
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
		
		if ("insert".equals(action)) {//ADD
			
			QAVO qaVO = new QAVO();
			
			qaVO.setQa_title(req.getParameter("title"));
			qaVO.setQa_tag(req.getParameter("tag"));
			qaVO.setQa_content(req.getParameter("editor"));		
			
			QAService qaSvc =new QAService();
			List<QAVO> nowlist =  qaSvc.getAll();
			int nQANO =Integer.parseInt(nowlist.get(nowlist.size()-1).getQa_no())+1;
			qaVO.setQa_no(String.valueOf(nQANO));
			qaSvc.insert(qaVO);
			
			String url = "/back_end/qa/QA.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交
			successView.forward(req, res);
		}
		
		if ("delet".equals(action)) { //UPDATE
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer qano = Integer.valueOf(req.getParameter("qano"));
				/***************************2.開始修改****************************************/
				QAService qaSvc = new QAService();
				qaSvc.delet(qano);
				/***************************3.完成,準備轉交(Send the Success view)************/
				String url = "/back_end/qa/QA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}
		
		if("modify".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數****************************************/
			String qano = req.getParameter("qano");
			req.setAttribute("qano", qano);
			
			String url = "/back_end/qa/QAUpdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			
			
		}
	
		
		
		if ("update".equals(action)) { //UPDATE
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				String qano = req.getParameter("qano");
				
				
				/***************************2.開始修改****************************************/
				QAService qaSvc = new QAService();
				QAVO qaVO = qaSvc.findByPrimaryKey(qano);
				qaVO.setQa_title(req.getParameter("title"));
				qaVO.setQa_content(req.getParameter("content"));
				qaVO.setQa_tag(req.getParameter("tag"));
				qaSvc.update(qaVO);
				/***************************3.完成,準備轉交(Send the Success view)************/
				String url = "/back_end/qa/QA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}
		
	}
	

}
