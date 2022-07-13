package com.bidding.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import utils.JedisUtil;
import utils.MysqlJDBC;

public class BiddingDAOImpl implements BiddingDAO{
	
	private static DataSource ds;
	
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}

	
	// 新增出價
	private static final String INSERT = "INSERT INTO "
			+ "BIDDING (`SCAR_NO`, `MEB_NO`, `BID_PRICE`) "
			+ "VALUES ( ? , ? , ? ) ";
	
	private static final String GET_AllPerSon_OneBid ="SELECT distinct MEB_NO FROM CGA102G4.BIDDING where SCAR_NO = ? ";
	
	
	@Override
	public boolean insert(String scar_no, String meb_no, Integer bid_price, Connection conn) {
		try{
			
			PreparedStatement pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, scar_no);
			pstmt.setString(2, meb_no);
			pstmt.setInt(3, bid_price);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}

	private static final String GET_ALL_STMT = "SELECT * FROM CGA102G4.BIDDING order by BID_NO;";
	private static final String GET_ALL_By_SCARNO_GPrice ="SELECT * FROM CGA102G4.BIDDING Where SCAR_NO=? AND BID_PRICE >=?;";
	
	private static final String GET_ALL_By_SCAR_NO ="SELECT * FROM CGA102G4.BIDDING Where SCAR_NO=? ORDER BY  BID_PRICE  DESC;";
	
	private static final String GET_One_Bid_Highest ="SELECT * FROM CGA102G4.BIDDING where SCAR_NO=? order by BID_PRICE desc limit 1;";
	
	private static final String GET_MEMBER_NO_STRING = "SELECT * FROM CGA102G4.BIDDING WHERE MEB_NO = ?";
	
	@Override
	public List<BiddingVO> getBidByMEB_NO(String MEB_NO) {
		List<BiddingVO> list = new ArrayList<BiddingVO>();
		BiddingVO bid = null;
		ResultSet rs = null;
		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(GET_MEMBER_NO_STRING)) {
			
			pstmt.setString(1, MEB_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bid = new BiddingVO();
				bid.setBid_no(rs.getInt("bid_no"));
				bid.setScar_no(rs.getString("scar_no"));
				bid.setMeb_no(rs.getString("meb_no"));
				bid.setBid_price(rs.getInt("bid_price"));
				bid.setBid_time(rs.getTimestamp("bid_time"));
				list.add(bid);
			}
			// Handle any driver errors
		} catch (SQLException se) {
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
		
		}
		return list;
	}



	@Override
	public List<BiddingVO> getAll() {
		List<BiddingVO> list = new ArrayList<BiddingVO>();
		Connection con = null;
		BiddingVO bid = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bid = new BiddingVO();
				bid.setBid_no(rs.getInt("bid_no"));
				bid.setScar_no(rs.getString("scar_no"));
				bid.setMeb_no(rs.getString("meb_no"));
				bid.setBid_price(rs.getInt("bid_price"));
				bid.setBid_time(rs.getTimestamp("bid_time"));
				list.add(bid);
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
	public List<BiddingVO> getBidByScar_NoGreaterPrice(String Scar_NO, Integer scar_price) {
		List<BiddingVO> bidSucessList = new ArrayList<BiddingVO>();
		Connection con = null;
		BiddingVO bid = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_By_SCARNO_GPrice);
			pstmt.setString(1, Scar_NO);
			pstmt.setInt(2, scar_price);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bid = new BiddingVO();
				bid.setBid_no(rs.getInt("bid_no"));
				bid.setScar_no(rs.getString("scar_no"));
				bid.setMeb_no(rs.getString("meb_no"));
				bid.setBid_price(rs.getInt("bid_price"));
				bid.setBid_time(rs.getTimestamp("bid_time"));
				bidSucessList.add(bid);
			}
			// Handle any driver errors
		} 
		catch (SQLException se) {
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
		return bidSucessList;
	}



	@Override
	public List<BiddingVO> getBidByScar_No(String Scar_NO) {
		List<BiddingVO> oneBid = new ArrayList<BiddingVO>();
		Connection con = null;
		BiddingVO bid = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_By_SCAR_NO);
			pstmt.setString(1, Scar_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bid = new BiddingVO();
				bid.setBid_no(rs.getInt("bid_no"));
				bid.setScar_no(rs.getString("scar_no"));
				bid.setMeb_no(rs.getString("meb_no"));
				bid.setBid_price(rs.getInt("bid_price"));
				bid.setBid_time(rs.getTimestamp("bid_time"));
				oneBid.add(bid);
			}
			// Handle any driver errors
		} catch (SQLException se) {
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
		return oneBid;
	}



	@Override
	public BiddingVO getBidByScar_Nohighest(String Scar_NO) {
		Connection con = null;
		BiddingVO bid;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_One_Bid_Highest);
			pstmt.setString(1, Scar_NO);
			rs = pstmt.executeQuery();
			rs.next();
			bid =new BiddingVO();
			bid.setBid_no(rs.getInt("bid_no"));
			bid.setScar_no(rs.getString("scar_no"));
			bid.setMeb_no(rs.getString("meb_no"));
			bid.setBid_price(rs.getInt("bid_price"));
			bid.setBid_time(rs.getTimestamp("bid_time"));
		
			
		
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
		return bid;
	}
	
	@Override
	public List<BiddingVO> getAllQuery(Map<String, String[]> map) {
		List<BiddingVO> QeryBid = new ArrayList<BiddingVO>();
		Connection con = null;
		BiddingVO bid = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String finalSQL = "select * from CGA102G4.BIDDING " + jdbcUtilSbid.get_WhereCondition(map)+"order by BID_TIME desc";
		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bid = new BiddingVO();
				bid.setBid_no(rs.getInt("bid_no"));
				bid.setScar_no(rs.getString("scar_no"));
				bid.setMeb_no(rs.getString("meb_no"));
				bid.setBid_price(rs.getInt("bid_price"));
				bid.setBid_time(rs.getTimestamp("bid_time"));
				QeryBid.add(bid);
			}
			// Handle any driver errors
		} catch (SQLException se) {
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
		return QeryBid;
	}
	
	@Override
	public List<String> getAllPersonByScar_No(String Scar_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> person = new ArrayList<String>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AllPerSon_OneBid);
			pstmt.setString(1, Scar_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			person.add(rs.getString("meb_no"));
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
		return person;
	}
	
	public Set<String> getAllMebBid(String scar_no){
		JedisPool pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		List<String> list = jedis.zrevrangeByScore(scar_no, "+inf", "0"); // {"meb_no":"xxx","bid_time":"xxx"}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Iterator<String> it =  list.iterator();
		List<BiddingVO> biddinglist = new ArrayList<BiddingVO>();
		while (it.hasNext()) {
			BiddingVO biddingVO = gson.fromJson(it.next(), BiddingVO.class);
			biddinglist.add(biddingVO);
		}
		Set<String> set = new HashSet<String>();
		for (BiddingVO biddingVO : biddinglist) {
			set.add(biddingVO.getMeb_no());
		}
		return set;
	}
	
}
