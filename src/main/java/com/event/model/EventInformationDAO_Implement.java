package com.event.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import utils.MysqlJDBC;



public class EventInformationDAO_Implement implements EventInformationDAO {
	private static DataSource ds;

	
	private static final String INSERT_STMT="INSERT INTO `CGA102G4`.`EVENT_INFORMATION` (`EVENT_TITLE`, `EVENT_CONTENT`, `EVENT_START`, `EVENT_END`,`ST_NAME_START`, `ST_NAME_END`,`MODEL_NO`, `EVENT_DISCOUNT`,`EVENT_PHOTO`) VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String GET_ALL_STMT="SELECT EVENT_NO,EVENT_TITLE,EVENT_CONTENT,EVENT_START,EVENT_END,ST_NAME_START,ST_NAME_END,MODEL_NO,EVENT_DISCOUNT,EVENT_PHOTO FROM CGA102G4.EVENT_INFORMATION order by EVENT_NO;";
	
	private static final String GET_ONE_STMT="SELECT EVENT_NO,EVENT_TITLE,EVENT_CONTENT,EVENT_START,EVENT_END,ST_NAME_START,ST_NAME_END,MODEL_NO,EVENT_DISCOUNT,EVENT_PHOTO FROM CGA102G4.EVENT_INFORMATION where EVENT_NO =?;";
	
	
	private static final String UPDATE="UPDATE CGA102G4.EVENT_INFORMATION SET EVENT_TITLE = ?, EVENT_CONTENT = ?, EVENT_START = ?, EVENT_END = ?, ST_NAME_START = ?, ST_NAME_END = ?, MODEL_NO = ?, EVENT_DISCOUNT = ?,EVENT_PHOTO = ? WHERE EVENT_NO = ?;";
	
	
	private static final String UPDATE_ONE_PHOTO="UPDATE CGA102G4.EVENT_INFORMATION SET EVENT_PHOTO = ? WHERE EVENT_NO = ?;";
	
	private static final String GET_EVENT_By_ORDERTIME="SELECT * FROM CGA102G4.EVENT_INFORMATION WHERE ? >= EVENT_START && ? <= EVENT_END;";
	
	
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	
	}
	
	
	
	@Override
	public void insert(EventInformationVO event_InformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,event_InformationVO.getEvent_title());
			pstmt.setString(2, event_InformationVO.getEvent_content());
			pstmt.setDate(3, event_InformationVO.getEvent_start());
			pstmt.setDate(4, event_InformationVO.getEvent_end());
			pstmt.setString(5, event_InformationVO.getSt_name_start());
			pstmt.setString(6, event_InformationVO.getSt_name_end());
			pstmt.setString(7, event_InformationVO.getModel_no());
			pstmt.setString(8, event_InformationVO.getEvent_discount());
			pstmt.setBytes(9, event_InformationVO.getEvent_photo());
		
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(EventInformationVO event_InformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,event_InformationVO.getEvent_title());
			pstmt.setString(2, event_InformationVO.getEvent_content());
			pstmt.setDate(3, event_InformationVO.getEvent_start());
			pstmt.setDate(4, event_InformationVO.getEvent_end());
			pstmt.setString(5, event_InformationVO.getSt_name_start());
			pstmt.setString(6, event_InformationVO.getSt_name_end());
			pstmt.setString(7, event_InformationVO.getModel_no());
			pstmt.setString(8, event_InformationVO.getEvent_discount());
			pstmt.setBytes(9, event_InformationVO.getEvent_photo());
			pstmt.setInt(10, event_InformationVO.getEvent_no());
		
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public EventInformationVO findByPrimaryKey(Integer event_no) {
		EventInformationVO event_InformationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, event_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				event_InformationVO = new EventInformationVO();
				event_InformationVO.setEvent_no(rs.getInt("event_no"));
				event_InformationVO.setEvent_title(rs.getString("event_title"));
				event_InformationVO.setEvent_content(rs.getString("event_content"));
				event_InformationVO.setEvent_start(rs.getDate("event_start"));
				event_InformationVO.setEvent_end(rs.getDate("event_end"));
				event_InformationVO.setSt_name_start(rs.getString("st_name_start"));
				event_InformationVO.setSt_name_end(rs.getString("st_name_end"));
				event_InformationVO.setModel_no(rs.getString("model_no"));
				event_InformationVO.setEvent_discount(rs.getString("event_discount"));
				event_InformationVO.setEvent_photo(rs.getBytes("event_photo"));
				
			}
			// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

		return event_InformationVO;
	}

	@Override
	public List<EventInformationVO> getAll() {
		List<EventInformationVO> list = new ArrayList<EventInformationVO>();
		EventInformationVO event_InformationVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url,userid,passwd);
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				event_InformationVO = new EventInformationVO();
				event_InformationVO.setEvent_no(rs.getInt("event_no"));
				event_InformationVO.setEvent_title(rs.getString("event_title"));
				event_InformationVO.setEvent_content(rs.getString("event_content"));
				event_InformationVO.setEvent_start(rs.getDate("event_start"));
				event_InformationVO.setEvent_end(rs.getDate("event_end"));
				event_InformationVO.setSt_name_start(rs.getString("st_name_start"));
				event_InformationVO.setSt_name_end(rs.getString("st_name_end"));
				event_InformationVO.setModel_no(rs.getString("model_no"));
				event_InformationVO.setEvent_discount(rs.getString("event_discount"));
				event_InformationVO.setEvent_photo(rs.getBytes("event_photo"));
				list.add(event_InformationVO);
			}
			// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	public List<EventInformationVO> getEventsByOrderTime(Timestamp start) {
		List<EventInformationVO> list = new ArrayList<EventInformationVO>();
		EventInformationVO event_InformationVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_EVENT_By_ORDERTIME);
			pstmt.setTimestamp(1, start);
			pstmt.setTimestamp(2, start);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				event_InformationVO = new EventInformationVO();
				event_InformationVO.setEvent_no(rs.getInt("event_no"));
				event_InformationVO.setEvent_title(rs.getString("event_title"));
				event_InformationVO.setEvent_content(rs.getString("event_content"));
				event_InformationVO.setEvent_start(rs.getDate("event_start"));
				event_InformationVO.setEvent_end(rs.getDate("event_end"));
				event_InformationVO.setSt_name_start(rs.getString("st_name_start"));
				event_InformationVO.setSt_name_end(rs.getString("st_name_end"));
				event_InformationVO.setModel_no(rs.getString("model_no"));
				event_InformationVO.setEvent_discount(rs.getString("event_discount"));
				event_InformationVO.setEvent_photo(rs.getBytes("event_photo"));
				list.add(event_InformationVO);
			}
			// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	
	

	
}
