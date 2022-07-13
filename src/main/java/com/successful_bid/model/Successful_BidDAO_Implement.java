package com.successful_bid.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import utils.MysqlJDBC;

public class Successful_BidDAO_Implement implements Successful_BidDAO {
	
	private static DataSource ds;

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/CGA102G4?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO `CGA102G4`.`SUCCESSFUL_BID` (`SB_NO`, `SCAR_NO`, `MEB_NO`, `SB_PRICE`, `SB_WIN_TIME`, `SB_NON_PAYING`, `SB_RANK`) VALUES (?,?,?,?,?,?,?);";

	private static final String UPDATE_NON_PAYING = "UPDATE CGA102G4.SUCCESSFUL_BID SET SB_NON_PAYING=? where SB_NO=? and MEB_NO=?;";

	private static final String GET_ALL_STMT = "select `SB_NO`, `SCAR_NO`, `MEB_NO`, `SB_PRICE`, `SB_WIN_TIME`, `SB_NON_PAYING`, `SB_RANK` from CGA102G4.SUCCESSFUL_BID  order by SB_WIN_TIME desc";

	private static final String GET_ONE_SBNO = "select `SB_NO`, `SCAR_NO`, `MEB_NO`, `SB_PRICE`, `SB_WIN_TIME`, `SB_NON_PAYING`, `SB_RANK` from CGA102G4.SUCCESSFUL_BID where SB_NO =?;";

	private static final String GET_ONE_Bid = "select `SB_NO`, `SCAR_NO`, `MEB_NO`, `SB_PRICE`, `SB_WIN_TIME`, `SB_NON_PAYING`, `SB_RANK` from CGA102G4.SUCCESSFUL_BID where SCAR_NO =?;";

	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	
	}
	
	@Override
	public void insert(Successful_BidVO successful_bidvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, successful_bidvo.getSb_no());
			pstmt.setString(2, successful_bidvo.getScar_no());
			pstmt.setString(3, successful_bidvo.getMeb_no());
			pstmt.setInt(4, successful_bidvo.getSb_price());
			pstmt.setTimestamp(5, successful_bidvo.getSb_win_time());
			pstmt.setInt(6, successful_bidvo.getSb_non_paying());
			pstmt.setInt(7, successful_bidvo.getSb_rank());
			pstmt.executeUpdate();
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void insertTransaction(Successful_BidVO successful_bidvo, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, successful_bidvo.getSb_no());
			pstmt.setString(2, successful_bidvo.getScar_no());
			pstmt.setString(3, successful_bidvo.getMeb_no());
			pstmt.setInt(4, successful_bidvo.getSb_price());
			pstmt.setTimestamp(5, successful_bidvo.getSb_win_time());
			pstmt.setInt(6, successful_bidvo.getSb_non_paying());
			pstmt.setInt(7, successful_bidvo.getSb_rank());
			//pstmt.setInt(8, successful_bidvo.getSb_rank());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
		}

	}

	@Override
	public void updateBySb_non_paying(Integer sb_no, String meb_no, Integer payState) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_NON_PAYING);

			pstmt.setInt(1, payState);
			pstmt.setInt(2, sb_no);
			pstmt.setString(3, meb_no);
			pstmt.executeUpdate();

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<Successful_BidVO> getAll() {
		List<Successful_BidVO> list = new ArrayList<Successful_BidVO>();
		Successful_BidVO successful_bidvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				successful_bidvo = new Successful_BidVO();
				successful_bidvo.setSb_no(rs.getInt("sb_no"));
				successful_bidvo.setScar_no(rs.getString("scar_no"));
				successful_bidvo.setMeb_no(rs.getString("meb_no"));
				successful_bidvo.setSb_price(rs.getInt("sb_price"));
				successful_bidvo.setSb_win_time(rs.getTimestamp("sb_win_time"));
				successful_bidvo.setSb_non_paying(rs.getInt("sb_non_paying"));
				successful_bidvo.setSb_rank(rs.getInt("sb_rank"));
				list.add(successful_bidvo);
			}
			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	@Override
	public Successful_BidVO getOneSbNo(Integer sb_no) {
		Successful_BidVO successful_bidvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_SBNO);

			pstmt.setInt(1, sb_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				successful_bidvo = new Successful_BidVO();
				successful_bidvo.setSb_no(rs.getInt("sb_no"));
				successful_bidvo.setScar_no(rs.getString("scar_no"));
				successful_bidvo.setMeb_no(rs.getString("meb_no"));
				successful_bidvo.setSb_price(rs.getInt("sb_price"));
				successful_bidvo.setSb_win_time(rs.getTimestamp("sb_win_time"));
				successful_bidvo.setSb_non_paying(rs.getInt("sb_non_paying"));
				successful_bidvo.setSb_rank(rs.getInt("sb_rank"));

			}

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return successful_bidvo;
	}

	@Override
	public List<Successful_BidVO> getWin2BySCar(String scar_no) {
		List<Successful_BidVO> list = new ArrayList<Successful_BidVO>();
		Successful_BidVO successful_bidvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_Bid);

			pstmt.setString(1, scar_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				successful_bidvo = new Successful_BidVO();
				successful_bidvo.setSb_no(rs.getInt("sb_no"));
				successful_bidvo.setScar_no(rs.getString("scar_no"));
				successful_bidvo.setMeb_no(rs.getString("meb_no"));
				successful_bidvo.setSb_price(rs.getInt("sb_price"));
				successful_bidvo.setSb_win_time(rs.getTimestamp("sb_win_time"));
				successful_bidvo.setSb_non_paying(rs.getInt("sb_non_paying"));
				successful_bidvo.setSb_rank(rs.getInt("sb_rank"));
				list.add(successful_bidvo);
			}

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

//	public static void main(String[] args) {
//		Successful_BidDAO_Implement dao = new Successful_BidDAO_Implement();
	// 增加
//		Successful_BidVO sucessful_bidvo = new Successful_BidVO();
//		sucessful_bidvo.setSb_no(19);
//		sucessful_bidvo.setScar_no("CDEFGHI1234567890");
//		sucessful_bidvo.setMeb_no("M179923293");
//		sucessful_bidvo.setSb_price(200000);
//		sucessful_bidvo.setSb_win_date(java.sql.Timestamp.valueOf("2022-05-30 12:00:00"));
//		sucessful_bidvo.setSb_non_paying(0);
//		sucessful_bidvo.setSb_rank(1);
//		dao.insert(sucessful_bidvo);
	// 更改付款狀態
//		dao.updateBySb_non_paying(18, "O240902098");

	// 查詢一條得標紀錄
//		List<Successful_BidVO> list = dao.getOneBidMEM(18);
//		list.forEach((n)->System.out.println(n.toString()));

	// 查詢一場得標紀錄有誰
//		
//		List<Successful_BidVO> list = dao.getWin2BySCar("CDEFGHI1234567890");
//		list.forEach((n)->System.out.println(n.toString()));
//		list.forEach((n)->System.out.println(n.getScarVO().toString()));
//		list.forEach((n)->System.out.println(n.getMemberVO().toString()));
//		
	// 查詢所有拍賣
//		List<Successful_BidVO> listAll = dao.getAll();
//		listAll.forEach((n) ->System.out.println(n.toString()));
	// }

}
