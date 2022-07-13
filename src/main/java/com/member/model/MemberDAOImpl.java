package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAOImpl implements MemberDAO {
//	Calendar myCalendar = new GregorianCalendar(1993, 0, 28); //注意一月從'0'開始
//	long millis = myCalendar.getTimeInMillis(); //使用GregorianCalendar 設定日期 再轉long放入 Date內

//	Date.valueOf(LocalDate.of(1993, 06, 29)); //使用LocalDate指定日期 再用Date.valueOf 存入 LocalDate
//  Date.valueOf(LocalDate.parse("1993-05-22")) //使用LocalDate.parse 可將自串日期放入 
	private static DataSource ds = null;
	private final String INSERT = "INSERT INTO `CGA102G4`.`MEMBER` "
			+ "(`MEB_NO`, `MEB_PASS`, `MEB_NAME`, `MEB_GENDER`, `MEB_BIR`, `MEB_TEL`, `MEB_ADRS`, `MEB_MAIL`) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?,?);";

	private final String UPDATE = "UPDATE `CGA102G4`.`MEMBER` " + "SET " + "    `MEB_PASS` = ?," + "    `MEB_NAME` = ?,"
			+ "    `MEB_GENDER` = ?," + "    `MEB_BIR` = ?," + "    `MEB_TEL` = ?," + "    `MEB_ADRS` = ?,"
			+ "    `MEB_MAIL` = ?," + "    `MEB_BONUS` = ?," + "    `MEB_STATUS` = ? " + "WHERE `MEB_NO` = ? ;";

	private final String IMG_UPDATE = "UPDATE `CGA102G4`.`MEMBER` " + "SET " + "    MEB_PROFILE = ? "
			+ "WHERE `MEB_NO` = ? ;";

	private final String ALL = "SELECT * FROM MEMBER;";

	private final String MEMBER = "SELECT * FROM MEMBER WHERE `MEB_MAIL` = ? ;";

	private final String MEMBERBYNO = "SELECT * FROM MEMBER WHERE `MEB_NO` = ? ;";

	private final String LOGIN = "SELECT * FROM MEMBER WHERE MEB_MAIL = ? and MEB_PASS = ? ;";

	private final String EMAIL = "SELECT MEB_MAIL FROM cga102g4.member where MEB_MAIL in (?) ;";

	private final String verifyid = "SELECT * FROM  member where MEB_MAIL = ? and MEB_NO = ?;";

	private final String UpdatePass = "UPDATE `CGA102G4`.`MEMBER` SET `MEB_PASS` = ? WHERE `MEB_NO` = ?;";
	
	private final String UpdateMemBer = "UPDATE `CGA102G4`.`MEMBER` SET `MEB_NAME` = ?, `MEB_GENDER` = ?, `MEB_BIR` = ?, `MEB_TEL` = ?, `MEB_ADRS` = ?, `MEB_MAIL` =?, `MEB_PROFILE` = ? WHERE `MEB_NO` = ?;";
	
	private final String CheckPass = "SELECT * FROM MEMBER WHERE MEB_NO = ? and MEB_PASS = ? ;";
	
	static {
//		ds = MysqlJDBC.getInstance().getDataSource();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga102g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean Insert(MemberVO MemberVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(INSERT);) {
			ps.setString(1, MemberVO.getMeb_no());
			ps.setString(2, MemberVO.getMeb_pass());
			ps.setString(3, MemberVO.getMeb_name());
			ps.setInt(4, MemberVO.getMeb_gender());
			ps.setDate(5, MemberVO.getMeb_bir());
			ps.setString(6, MemberVO.getMeb_tel());
			ps.setString(7, MemberVO.getMeb_adrs());
			ps.setString(8, MemberVO.getMeb_mail());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean Update(MemberVO MemberVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(UPDATE);) {
			ps.setString(1, MemberVO.getMeb_pass());
			ps.setString(2, MemberVO.getMeb_name());
			ps.setInt(3, MemberVO.getMeb_gender());
			ps.setDate(4, MemberVO.getMeb_bir());
			ps.setString(5, MemberVO.getMeb_tel());
			ps.setString(6, MemberVO.getMeb_adrs());
			ps.setString(7, MemberVO.getMeb_mail());
			ps.setInt(8, MemberVO.getMeb_bonus());
			ps.setInt(9, MemberVO.getMeb_status());
			ps.setString(10, MemberVO.getMeb_no());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean ImgUpdate(MemberVO MemberVO) {
		try {
			Connection ct = ds.getConnection();
			PreparedStatement ps = ct.prepareStatement(IMG_UPDATE);
			ps.setBytes(1, MemberVO.getMeb_profile());
			ps.setString(2, MemberVO.getMeb_no());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<MemberVO> GetAll() {
		MemberVO memberVO;
		ArrayList<MemberVO> list = new ArrayList<>();
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(ALL);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMeb_no(rs.getString("meb_no"));
				memberVO.setMeb_name(rs.getString("meb_name"));
				memberVO.setMeb_gender(rs.getInt("meb_gender"));
				memberVO.setMeb_bir(rs.getDate("meb_bir"));
				memberVO.setMeb_tel(rs.getString("meb_tel"));
				memberVO.setMeb_adrs(rs.getString("meb_adrs"));
				memberVO.setMeb_mail(rs.getString("meb_mail"));
				memberVO.setMeb_profile(rs.getBytes("meb_profile"));
				memberVO.setMeb_bonus(rs.getInt("meb_bonus"));
				memberVO.setMeb_status(rs.getInt("meb_status"));
				list.add(memberVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured." + e.getMessage());// SQL語法錯誤
		}
		return list;

	}

	@Override
	public MemberVO Login(String username, String password) {
		MemberVO memberVO = new MemberVO();
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(LOGIN);) {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMeb_no(rs.getString("meb_no"));
				memberVO.setMeb_name(rs.getString("meb_name"));
				memberVO.setMeb_gender(rs.getInt("meb_gender"));
				memberVO.setMeb_bir(rs.getDate("meb_bir"));
				memberVO.setMeb_tel(rs.getString("meb_tel"));
				memberVO.setMeb_adrs(rs.getString("meb_adrs"));
				memberVO.setMeb_mail(rs.getString("meb_mail"));
				memberVO.setMeb_profile(rs.getBytes("meb_profile"));
				memberVO.setMeb_bonus(rs.getInt("meb_bonus"));
				memberVO.setMeb_status(rs.getInt("meb_status"));
				return memberVO;
			} else {
				return null;
			}

		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public MemberVO FindByPrimaryKey(String meb_no) {
		MemberVO memberVO = null;
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(MEMBER);) {
			ps.setString(1, meb_no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			memberVO = new MemberVO();
			memberVO.setMeb_no(rs.getString("meb_no"));
			memberVO.setMeb_name(rs.getString("meb_name"));
			memberVO.setMeb_pass(rs.getString("meb_pass"));
			memberVO.setMeb_gender(rs.getInt("meb_gender"));
			memberVO.setMeb_bir(rs.getDate("meb_bir"));
			memberVO.setMeb_tel(rs.getString("meb_tel"));
			memberVO.setMeb_adrs(rs.getString("meb_adrs"));
			memberVO.setMeb_mail(rs.getString("meb_mail"));
			memberVO.setMeb_profile(rs.getBytes("meb_profile"));
			memberVO.setMeb_bonus(rs.getInt("meb_bonus"));
			memberVO.setMeb_status(rs.getInt("meb_status"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}

//		註冊email傳入資料庫做判斷
	@Override
	public boolean Register(String email) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(EMAIL);) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 忘記密碼驗證mail&id
	@Override
	public boolean verifyid(String mail, String id) {

		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(verifyid);) {
			ps.setString(1, mail);
			ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// 忘記密碼 修改密碼
	@Override
	public boolean updatepassword(String account, String newPassword) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(UpdatePass);) {
			ps.setString(1, newPassword);
			ps.setString(2, account);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	@Override
	public MemberVO member(String account) {
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		MemberVO memberVO = null;
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(MEMBERBYNO);) {

			pstmt.setString(1, account);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			memberVO = new MemberVO();
			memberVO.setMeb_no(rs.getString("meb_no"));
			memberVO.setMeb_name(rs.getString("meb_name"));
			memberVO.setMeb_pass(rs.getString("meb_pass"));
			memberVO.setMeb_gender(rs.getInt("meb_gender"));
			memberVO.setMeb_bir(rs.getDate("meb_bir"));
			memberVO.setMeb_tel(rs.getString("meb_tel"));
			memberVO.setMeb_adrs(rs.getString("meb_adrs"));
			memberVO.setMeb_mail(rs.getString("meb_mail"));
			memberVO.setMeb_profile(rs.getBytes("meb_profile"));
			memberVO.setMeb_bonus(rs.getInt("meb_bonus"));
			memberVO.setMeb_status(rs.getInt("meb_status"));
		}

		catch (SQLException e) {
			e.printStackTrace();

		}
		return memberVO;
	}
	
	@Override
	public boolean updateinformation(MemberVO MemberVO) {
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(UpdateMemBer);) {

			pstmt.setString(1, MemberVO.getMeb_name());
			pstmt.setInt(2, MemberVO.getMeb_gender());
			pstmt.setDate(3, MemberVO.getMeb_bir());
			pstmt.setString(4, MemberVO.getMeb_tel());
			pstmt.setString(5, MemberVO.getMeb_adrs());
			pstmt.setString(6, MemberVO.getMeb_mail());
			pstmt.setBytes(7, MemberVO.getMeb_profile());
			pstmt.setString(8, MemberVO.getMeb_no());
			pstmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@Override
	public boolean checkpassword(String meb_no, String oldPassword) {
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try (Connection con =  ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(CheckPass);) {
			pstmt.setString(1, meb_no);
			pstmt.setString(2, oldPassword);
			ResultSet rs = pstmt.executeQuery();
			//System.out.println(rs.next());
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}
