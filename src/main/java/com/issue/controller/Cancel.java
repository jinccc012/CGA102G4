package com.issue.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.issue.model.IssueService;
import com.rcarorder.model.RcarOrderService;

@WebServlet("/Issue/cancel")
public class Cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cancel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IssueService svc = new IssueService();
		String issueNostr = request.getParameter("no"); // 從ajax取
		System.out.println("test");
		int issueno = Integer.parseInt(issueNostr);
		System.out.println("TEST");
		svc.cancel(issueno);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
