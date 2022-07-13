package com.carmodel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carmodel.model.CarModelService;
import com.google.gson.Gson;

@WebServlet("/getcarModelname")
public class getcarModelname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CarModelService carservice = new CarModelService();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String scar_model =  req.getParameter("scar_model");
		//System.out.println(scar_model);
		String listJson=null;
		Gson gson = new Gson();
		if(scar_model != null) {
			List<String> model =carservice.getAllModelnoByName(scar_model);
			 listJson =	gson.toJson(model);
		}else {
		
			List<String> modelName = carservice.getCarModelName();
			 listJson =	gson.toJson(modelName);
		}
		 res.setContentType("text/html; charset=utf-8");
		 PrintWriter out = res.getWriter();
		 out.print(listJson);
		 out.close();
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
