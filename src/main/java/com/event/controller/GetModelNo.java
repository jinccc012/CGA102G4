package com.event.controller;

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

@WebServlet("/getmodel")
public class GetModelNo extends HttpServlet {
	CarModelService service =new CarModelService();
	 public void doGet(HttpServletRequest req, HttpServletResponse res)
             throws ServletException, IOException {
		 List<String> model =  service.getModelNo();
		 model.add("全部");
		 Gson gson = new Gson();
		 String listJson =	gson.toJson(model);
		 res.setContentType("text/html; charset=utf-8");
		 PrintWriter out = res.getWriter();
		 out.print(listJson);
		 out.close();	
	 }
}
