package com.rcar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;

@WebServlet("/back_end/rcarList")
public class Rcarlist extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RcarService rcarSvc =new RcarService();
		List<RcarVO> list = rcarSvc.getAll();
		
		
		Gson gson= new Gson();
		String json = gson.toJson(list);
		System.out.println(json);
		resp.getWriter().write(json);
		return;
	}
	
	

}
