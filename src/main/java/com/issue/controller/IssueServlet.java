package com.issue.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.issue.model.IssueService;
import com.issue.model.IssueVO;

@WebServlet("/issueServlet")
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IssueServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IssueService svc = new IssueService();
		List<IssueVO> list = svc.getAll();
		request.getSession().setAttribute("getByComposite", list);
		request.getRequestDispatcher("/back_end/Issue/Issue.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String[]> paramMap = request.getParameterMap();
		IssueService svc = new IssueService();
		List<IssueVO> list = svc.getByComposite(paramMap);

		request.getSession().setAttribute("getByComposite", list);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/back_end/Issue/Issue.jsp");
		requestDispatcher.forward(request, response);
	}

}
