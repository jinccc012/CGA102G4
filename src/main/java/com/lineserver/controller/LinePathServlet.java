package com.lineserver.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lineserver.model.LineServerService;


@WebServlet("/Member/LinePathServlet")
public class LinePathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LinePathServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = LineServerService.getLinePathInfo();
		Gson g = new Gson();
		String json = g.toJson(path);
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		out.print(json);
		out.close();
		return;
	}

}
