package com.scar.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.scar.model.ScarService;
import com.scar.model.ScarVO;

import utils.Webutils;

@WebServlet("/scar/scarAdd")
@MultipartConfig
public class ScarAdd  extends HttpServlet{
private ScarService service = new ScarService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/back_end/scar/ScarAdd.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		ScarVO vo = new ScarVO();
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		// 檢查邏輯判斷
		Map<String, String> Msgs = new LinkedHashMap<String, String>();
		Enumeration<String> enums = req.getParameterNames();
		req.setAttribute("scar", vo);
		String scar_no = req.getParameter("scar_no");
		String scarReg = "^[(A-Z0-9)]{17}$";// ok
		java.sql.Timestamp scar_startime = null;
		java.sql.Timestamp scar_endtime = null;
		while (enums.hasMoreElements()) {
			// 獲取表單元素名稱
			String name = enums.nextElement();
			// 獲取相對值
			String value = req.getParameter(name);
			if (value == null || (value.trim()).length() == 0) {
				if ("scar_no".equals(name))
					Msgs.put(name, "中古車編號請勿空白");
				if ("st_no".equals(name))
					Msgs.put(name, "門市請勿空白");
				if ("scar_brand".equals(name))
					Msgs.put(name, "中古車品牌請勿空白");
				if ("scar_model".equals(name))
					Msgs.put(name, "中古車車型請勿空白");
				if ("scar_color".equals(name))
					Msgs.put(name, "中古車顏色請勿空白");
				if ("scar_year".equals(name))
					Msgs.put(name, "中古車年分請勿空白");
				if ("scar_cc".equals(name))
					Msgs.put(name, "中古車排氣量請勿空白");
				if ("scar_trans".equals(name))
					Msgs.put(name, "中古車變數系統請勿空白");
				if ("scar_fuel".equals(name))
					Msgs.put(name, "中古車燃料請勿空白");
				if ("scar_carrying".equals(name))
					Msgs.put(name, "乘載人數請勿空白");
				if ("scar_carringpkg".equals(name))
					Msgs.put(name, "乘載行李數請勿空白");
			}
			
			if ("scar_no".equals(name) && value != null && (value.trim()).length() != 0) {
				//check主見是否重複
				 ScarVO isChecksc =  service.getScar(scar_no);
				 if(scar_no.equals(isChecksc.getScar_no())) {
					 Msgs.put("repeate", "中古車編號不得重複");
				 }
				if (!scar_no.trim().matches(scarReg))
					Msgs.put("scar_no", "中古車編號格式為中文、大寫英文、共17碼");
			}
			
			if ("scar_year".equals(name) && value != null && (value.trim()).length() != 0) {
				Integer scar_year = null;
				try {
					scar_year = Integer.valueOf(req.getParameter("scar_year").trim());
					if (value.trim().length() != 4 ) {
						Msgs.put("scar_year", "年份需符合年分格式");
					}
				} catch (NumberFormatException e) {
					Msgs.put("scar_year", "年份請符合格式");
				}
			}

			if ("scar_carrying".equals(name)) {
				Integer scar_carrying = null;
				try {
					scar_carrying = Integer.valueOf(req.getParameter("scar_carrying").trim());
					if (scar_carrying >= 255) {
						Msgs.put("scar_carrying", "乘載人數請符合標準");
					}
				} catch (NumberFormatException e) {
					Msgs.put("scar_carrying", "乘載人數請填數字");
				}
			}
			if ("scar_miles".equals(name)) {
				Integer scar_miles = null;
				try {
					scar_miles = Integer.valueOf(req.getParameter("scar_miles").trim());
					if(scar_miles <=0) {
						Msgs.put("scar_miles", "里程數需為正整數");}
				} catch (NumberFormatException e) {
					Msgs.put("scar_miles", "里程請填數字");
				}
			}
			// 底標價格
			if ("scar_price".equals(name)) {
				Integer scar_price = null;
				try {
					scar_price = Integer.valueOf(req.getParameter("scar_price").trim());
					if(scar_price <=0) {
						Msgs.put("scar_price", "底標為正整數");
					}
				} catch (NumberFormatException e) {
					Msgs.put("scar_price", "底標請填數字");
				}
			}
			
			if ("scar_startprice".equals(name)) {
				Integer scar_price = null;
				try {
					scar_price = Integer.valueOf(req.getParameter("scar_startprice").trim());
					if(scar_price <=0) {
						Msgs.put("scar_startprice", "起拍價為正整數");
					}
				} catch (NumberFormatException e) {
					Msgs.put("scar_startprice", "起拍價為正整數");
				}
			}
			// 起標日期
			if ("scar_startime".equals(name)) {
				try {
					scar_startime = java.sql.Timestamp.valueOf(req.getParameter("scar_startime").trim());
				} catch (IllegalArgumentException e) {
					Msgs.put("scar_startime", "請輸入起標時間");
				}
			}

			// 結標日期
			if ("scar_endtime".equals(name)) {
				try {
					scar_endtime = java.sql.Timestamp.valueOf(req.getParameter("scar_endtime").trim());
//					if(scar_endtime.getMinutes()!=0||scar_endtime.getSeconds()!=0) {
//						Msgs.put("scar_endtime","結標時間時間須為整點");
//					}
				} catch (IllegalArgumentException e) {
					Msgs.put("scar_endtime", "請輸入結標時間");
				}
			}
			
			//不得晚於今日
			if(scar_startime != null) {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				//確認一下
				if(scar_startime.before(now)==true)//if now earliy timestap return false
					Msgs.put("startime_erro","起標時間時間至少不得晚於現在");
			}		
			if(scar_startime != null && scar_endtime != null) {
				if(scar_startime.compareTo(scar_endtime)>0) {
					Msgs.put("date_erro","起標時間時間不得晚於結標時間");
				}
			}		
		}

		Part photo = req.getPart("scar_photo");
		String photoName = photo.getSubmittedFileName();
		if (photoName == null || (photoName.trim()).length()==0) {
			Msgs.put("scar_photo", "請上傳中古車照片");
		}		
		Gson gson = new Gson();
		String msg = gson.toJson(Msgs);
		PrintWriter out = res.getWriter();
		if (!Msgs.isEmpty()) {
		//	Msgs.forEach((u, v) -> System.out.println("key:" + u + ",value:" + v));
			out.print(msg);
			return;
		}		
		Webutils.copyParamToBean(req, vo);
		// pitcuer	

		if (!("".equals(photoName))) {
			InputStream in = photo.getInputStream();
			byte[] b = new byte[in.available()];
			in.read(b);
			in.close();
			vo.setScar_photo(b);
		}
		service.insert(vo);
		req.getSession().setAttribute("message", "新增成功!!!");

		//res.sendRedirect("ScarManage");
		Msgs.put("redirect", req.getContextPath()+"/scar/scarManage");
		String sucess = gson.toJson(Msgs);
		out.print(sucess);
		out.close();

	}
}
