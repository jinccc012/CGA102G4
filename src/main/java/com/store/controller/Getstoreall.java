package com.store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/Getstoreall")
public class Getstoreall extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");    //第一步 傳到前端資料，編碼要轉成utf-8
		StoreService storeService = new StoreService();    //第二步 呼叫store Service
		List<StoreVO> all = storeService.getAll();         //第三部 取得Getall方法
		
		Gson gson = new Gson();
		String json = gson.toJson(all);
		resp.getWriter().write(json);
	}
	
	
}
