package com.issue.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.issue.model.IssueService;
import com.issue.model.IssueVO;

@WebServlet("/IssueController")
public class IssueController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		IssueService issueService = new IssueService();
		PrintWriter out = resp.getWriter();
		IssueVO issueVO = new IssueVO();

		String name = req.getParameter("issue_name");
		
		String phone = req.getParameter("issue_tel");
		
		String mail = req.getParameter("issue_mail");
		
		String content = req.getParameter("issue_content");
		
		
		
		if(name != null & phone != null & mail != null & content != null) {
			
			issueVO.setIssue_name(name);
			issueVO.setIssue_tel(phone);
			issueVO.setIssue_mail(mail);
			issueVO.setIssue_content(content);
					
			boolean insert = issueService.insert(issueVO);
			if(insert == true) {
				out.write("true");
				System.out.println("輸入成功");
			}else {
				out.write("false");
			}
			System.out.println(issueVO.toString());
		}else {
			return; 
		}
		
		
		
	}
	

}
