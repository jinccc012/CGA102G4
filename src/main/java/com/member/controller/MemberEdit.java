package com.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
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
import com.member.model.MemberService;
import com.member.model.MemberVO;

import utils.Webutils;

@WebServlet("/memberedit")
@MultipartConfig // 不怕硬碟被灌爆
public class MemberEdit extends HttpServlet {
	MemberService service = new MemberService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get id
		String meb_no = req.getParameter("meb_no");
		// get vo
		MemberVO member = service.member(meb_no);
		// store in req
		req.setAttribute("mem", member);
		// forward
		req.getRequestDispatcher("/front_end/member/MemberUpdate.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		String meb_no = req.getParameter("meb_no");
		MemberVO oldMember = service.member(meb_no);

		MemberVO member = new MemberVO();
		Map<String, String> Msgs = new LinkedHashMap<String, String>();

//		判斷姓名	為正確格式
		String meb_name = req.getParameter("meb_name");
		if (meb_name == null || (meb_name.trim()).length() == 0) {
			Msgs.put("meb_nameMsgs", "姓名不得為空白");
		} else {
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$"; // 正規表達式判斷姓名於中英文2~10碼
			if (!meb_name.matches(enameReg)) {
				Msgs.put("meb_nameMsgs", "姓名為中英文姓名2~10碼");
			}
		}

//		判斷email為正確格式  //是否要再次認證!?
		String meb_mail = req.getParameter("meb_mail");
		if (meb_mail == null || (meb_mail.trim()).length() == 0) {
			Msgs.put("meb_mailMsgs", "信箱不得為空白");
		} else {
			String format = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
			if (!meb_mail.matches(format)) {
				Msgs.put("meb_mailMsgs", "信箱格式不正確");
			} else {
				if (!oldMember.getMeb_mail().equals(meb_mail)) {
					// 確認信箱是否與其他重複
					if (service.Register(meb_mail) == true) {
						Msgs.put("meb_mailMsgs", "已有重複的E-MAIL");
					}
				}
			}
		}

//		判斷手機為正確格式
		String meb_tel = req.getParameter("meb_tel");
		System.out.println(meb_tel + (meb_tel.trim()).length());
		if (meb_tel == null || (meb_tel.trim()).length() == 0) {
			Msgs.put("meb_telMsgs", "手機不得為空白");
		} else {
			String pattern = "^09[0-9]{8}$";
			if (!meb_tel.matches(pattern)) {
				Msgs.put("meb_telMsgs", "手機格式不正確");
			}
		}

//      生日格式驗證
			String Birthday = req.getParameter("meb_bir");
			if (Birthday == null || (Birthday.trim()).length() == 0) {
				Msgs.put("meb_birMsgs", "生日不得為空白");
			} else {
				LocalDate date = LocalDate.parse(Birthday);// 使用者輸入日期
				LocalDate now = LocalDate.now();// 取得現在日期
				int nowyear = now.getYear();
				int year = date.getYear();
				if ((nowyear - year) < 20) {
					Msgs.put("meb_birMsgs", "承租者年齡需滿20歲");
				}
			}

			// 地址檢查有無輸入
			String meb_adrs = req.getParameter("meb_adrs");
			if (meb_adrs == null || (meb_adrs.trim()).length() == 0) {
				Msgs.put("meb_adrsMsgs", "地址不得為空白");
			}
//      檢查性別
			String meb_gender = req.getParameter("meb_gender");
			if (meb_gender == null || (meb_gender.trim()).length() == 0) {
				Msgs.put("meb_genderMsgs", "性別不得為空白");
			}
			
			Msgs.forEach((u, v) -> System.out.println("key:" + u + ",value:" + v));
			Webutils.copyParamToBean(req, member);

			req.setAttribute("mem", member);
			req.setAttribute("errorMsgs", Msgs);
			Gson gson = new Gson();
			PrintWriter out = res.getWriter();
			String message = gson.toJson(Msgs);

			if (!Msgs.isEmpty()) {
				out.print(message);
				return;
			}
			// deal picture
			Part photo = req.getPart("meb_profile");
			String photoName = photo.getSubmittedFileName();
			FileInputStream pho;
			if (!("".equals(photoName))) {
				InputStream in = photo.getInputStream();
				byte[] b = new byte[in.available()];
				in.read(b);
				in.close();
				member.setMeb_profile(b);
			} else {
				System.out.println("no change!");
				member.setMeb_profile(oldMember.getMeb_profile());
			}
			service.updateMember(member);
			Msgs.put("sucess", "編輯成功!!!");
			String sucess = gson.toJson(Msgs);
			out.println(sucess);
			out.close();

		}

}
