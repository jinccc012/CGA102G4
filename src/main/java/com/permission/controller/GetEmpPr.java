package com.permission.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.permission.model.PermissionDAOImpl;
import com.permission.model.PermissionVO;

@WebServlet("/getemppr")
public class GetEmpPr extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String statusStr = req.getParameter("status");
		PermissionDAOImpl permissionDAOImpl = new PermissionDAOImpl();
		if("getemppr".equals(statusStr)) { //取得會員權限
			String empNo = req.getParameter("emp_no");
			List<PermissionVO> list = permissionDAOImpl.getByEmpNo(empNo);
			
			Gson gson = new Gson();
			String json = gson.toJson(list);
			System.out.println(list);
			resp.getWriter().write(json);
		}
		
		if("addorrom".equals(statusStr)) { //新增與刪除 權限
			String rom = req.getParameter("json");
			String add = req.getParameter("addjson");
			
			System.out.println(rom);
			System.out.println(add);
			Type userListType = new TypeToken<ArrayList<PermissionVO>>(){}.getType();  //設定gson格式
			Gson gson = new Gson();
			
			ArrayList<PermissionVO> romlist = gson.fromJson(rom, userListType);  
			ArrayList<PermissionVO> addlist = gson.fromJson(add, userListType);  
			
			System.out.println("add"+addlist);
			System.out.println("rom"+romlist);
			
			PermissionDAOImpl impl = new PermissionDAOImpl();
			boolean romBoolean = false;
			boolean addBoolean = false;
			if(romlist.size() != 0) { //權限刪除
				int delete = impl.delete(romlist);      //回傳刪除幾筆資料
				if(delete == romlist.size()) {			//判斷是不是跟前端回傳的數量一樣
					System.out.println("rom"+true);
					romBoolean = true;
				}else {
					System.out.println("rom"+false);
				}
			}
			
			if(addlist.size() != 0) { //權限新增
				int insert = impl.insert(addlist);  //前端回傳0的話，回傳新增幾筆資料
				if(insert == addlist.size()) { 		//判斷新增跟傳到前端的數量是否一致
					System.out.println("add"+true);
					addBoolean = true;				//正確回傳true
				}else {
					System.out.println("add"+false);
				}
			}
			
			if(addlist.size() != 0 && romlist.size() != 0) { //同時新增 刪除
				if(romBoolean && addBoolean) {
					resp.getWriter().write("true");
				}else {
					resp.getWriter().write("false");
				}
				return;
			}else if(addlist.size() != 0) { //只有新增
				if(addBoolean) {
					resp.getWriter().write("true");
				}else {
					resp.getWriter().write("false");
				}
				return;
			}else if(romlist.size() != 0) { //只有刪除
				if(romBoolean) {
					resp.getWriter().write("true");
				}else {
					resp.getWriter().write("false");
				}
				return;
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
