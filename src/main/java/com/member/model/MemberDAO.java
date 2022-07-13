package com.member.model;
import java.util.List;


public interface MemberDAO {
	
	
	
	//新增
	boolean Insert( MemberVO MemberVO);
	//修改
	boolean Update(MemberVO MemberVO);
	//修改圖片
	boolean ImgUpdate(MemberVO MemberVO);
	//預覽ALL
	List<MemberVO> GetAll();
	//登入驗證
//	boolean Login(String account,String password);
	//取得會員基本資料
	MemberVO FindByPrimaryKey(String meb_no) ;
	//登入判斷email不可與資料庫重複
	boolean Register(String email);
	//登入驗證
	MemberVO Login(String username, String password);
	//1.忘記密碼 驗證信箱&身分證 
	 boolean verifyid (String mail, String id);
	//2.忘記密碼  修改密碼
	 boolean updatepassword(String account,String newPassword);
	//取得會員基本資料
	MemberVO member(String account);
	//修改會員
	boolean updateinformation(MemberVO MemberVO);
	// 修改密碼
	boolean checkpassword(String meb_no,String oldPassword);

}
