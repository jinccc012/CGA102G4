package com.issue.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.issue.model.IssueService;
import com.issue.model.IssueVO;

@WebServlet("/ClosedIssueList")
public class ClosedIssueList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClosedIssueList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IssueService svc = new IssueService();
		List<IssueVO> list = svc.getByOrderStatus(1);
		
		request.setAttribute("getByComposite", list);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/back_end/Issue/Issue.jsp");
		requestDispatcher.forward(request, response);
//		request.getRequestDispatcher("/back_end/Issue/Issue.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
