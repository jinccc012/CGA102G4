package com.car_dispatch_record.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.rcar.model.RcarDAOImpl;
import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;

import utils.MysqlJDBC;

public class Car_Dispatch_RecordDAOImpl implements Car_Dispatch_RecordDAO {

	private static DataSource ds;

	// 取得全部
	private static final String GET_ALL_PSTMT = "SELECT " 
			+ "DR_NO, " 
			+ "DR_APPLY_ST," 
			+ "DR_APPROVE_ST,"
			+ "DR_APPLY_EMP, " 
			+ "DR_APPROVE_EMP, " 
			+ "RCAR_NO, DR_START_TIME, " 
			+ "DR_END_TIME, "
			+ "DR_START_TIME_ACTUAL, " 
			+ "DR_END_TIME_ACTUAL, " 
			+ "DR_APPROVE, " 
			+ "DR_CANCEL, " 
			+ "MILES_BEFORE, "
			+ "MILES_AFTER " 
			+ "FROM CAR_DISPATCH_RECORD; ";
	// 新增
//	private static final String APPLY_DISPATCH = "INSERT INTO CAR_DISPATCH_RECORD "
//			+ "(DR_APPLY_EMP, "
//			+ "DR_APPROVE_EMP, "
//			+ "RCAR_NO, "
//			+ "DR_START_TIME, "
//			+ "DR_END_TIME) "
//			+     "VALUES (?, ?, ?, ?, ?);";
	// 新增
	private static final String APPLY_DISPATCH = "INSERT INTO `CGA102G4`.`CAR_DISPATCH_RECORD` " 
			+ "(`DR_APPLY_ST`, "
			+ "`DR_APPROVE_ST`, " 
			+ "`DR_APPLY_EMP`, " 
			+ "`RCAR_NO`, " 
			+ "`DR_START_TIME`, " 
			+ "`DR_END_TIME`) "
			+ "VALUES (?, ?, ?, ?, ?, ?);";

	// 調度核准
	private static final String APPROVE_DISPATCH = "UPDATE CAR_DISPATCH_RECORD " 
			+ "SET DR_APPROVE = ?, "
			+ "DR_APPROVE_EMP = ? " 
			+ "WHERE (DR_NO = ?);";
	// 調度駁回
	private static final String NO_APPROVE_DISPATCH = "UPDATE CAR_DISPATCH_RECORD " 
			+ "SET DR_APPROVE = 2 "
			+ "WHERE (DR_NO = ?);";
	// 取消申請
	private static final String CANCEL_DISPATCH = "UPDATE CAR_DISPATCH_RECORD " 
			+ "SET DR_CANCEL = 1 "
			+ "WHERE (DR_NO = ?);";

	private static final String UPDATE_DISPATCH = "UPDATE CAR_DISPATCH_RECORD " 
			+ "SET DR_START_TIME_ACTUAL = ?, "
			+ "DR_END_TIME_ACTUAL = ?, " 
			+ "MILES_BEFORE = ?, " 
			+ "MILES_AFTER = ? " 
			+ "WHERE (DR_NO = ?);";
	// 調度出站
	private static final String DISPATCH = "UPDATE `CGA102G4`.`CAR_DISPATCH_RECORD` " 
			+ "SET "
			+ "    `DR_START_TIME_ACTUAL` = ? ," 
			+ "    `MILES_BEFORE` = ?, "
			+ "	   `DR_APPROVE` = '3' " 
			+ "WHERE " 
			+ "    (`DR_NO` = ? );";

	// 到站
	private static final String DISPATCH_RETURN = "UPDATE `CGA102G4`.`CAR_DISPATCH_RECORD` " 
			+ "SET "
			+ "    `DR_END_TIME_ACTUAL` = ? ," 
			+ "    `MILES_AFTER` = ?, " 
			+ "	   `DR_APPROVE` = '4' " 
			+ "WHERE " 
			+ "    (`DR_NO` = ? );";

	private static final String GETRECORD = "SELECT "
			+ "    DR_NO,"
			+ "    DR_APPLY_ST,"
			+ "    DR_APPROVE_ST,"
			+ "    DR_APPLY_EMP,"
			+ "    DR_APPROVE_EMP,"
			+ "    RCAR_NO,"
			+ "    DR_START_TIME,"
			+ "    DR_END_TIME,"
			+ "    DR_START_TIME_ACTUAL,"
			+ "    DR_END_TIME_ACTUAL,"
			+ "    DR_APPROVE,"
			+ "    DR_CANCEL,"
			+ "    MILES_BEFORE,"
			+ "    MILES_AFTER "
			+ "FROM "
			+ "    CGA102G4.CAR_DISPATCH_RECORD "
			+ "WHERE "
			+ "	DR_NO = ?;";

	// 取得月份 調車紀錄 OR // 取得區間內 調車紀錄
	private static final String MONTH_DISPATCH_RECORD = "SELECT " 
			+ "	   DR_NO," 
			+ "    DR_APPLY_ST,"
			+ "    DR_APPROVE_ST," 
			+ "    DR_APPLY_EMP, " 
			+ "    DR_APPROVE_EMP, " 
			+ "    RCAR_NO,"
			+ "    DR_START_TIME," 
			+ "    DR_END_TIME," 
			+ "    DR_START_TIME_ACTUAL," 
			+ "    DR_END_TIME_ACTUAL,"
			+ "    DR_APPROVE," 
			+ "    DR_CANCEL," 
			+ "    MILES_BEFORE," 
			+ "    MILES_AFTER " + "FROM "
			+ "    CGA102G4.CAR_DISPATCH_RECORD " 
			+ "WHERE "
//			+ "    DR_APPROVE = 1 AND "
			+ "    DR_START_TIME BETWEEN ? AND ? " 
			+ "        OR DR_END_TIME BETWEEN ? AND ? "
			+ "ORDER BY DR_START_TIME DESC ;";

	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}

	//
	@Override
	public List<Car_Dispatch_RecordVO> getAll() {

		List<Car_Dispatch_RecordVO> list = new ArrayList<Car_Dispatch_RecordVO>();
		Car_Dispatch_RecordVO car_dispatch_recordVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				car_dispatch_recordVO = new Car_Dispatch_RecordVO();
				car_dispatch_recordVO.setDr_no(rs.getInt("dr_no"));
				car_dispatch_recordVO.setDr_apply_st(rs.getString("dr_apply_st"));
				car_dispatch_recordVO.setDr_approve_st(rs.getString("dr_approve_st"));
				car_dispatch_recordVO.setDr_apply_emp(rs.getString("dr_apply_emp"));
				car_dispatch_recordVO.setDr_approve_emp(rs.getString("dr_approve_emp"));
				car_dispatch_recordVO.setRcar_no(rs.getString("rcar_no"));
				car_dispatch_recordVO.setDr_start_time(rs.getTimestamp("dr_start_time"));
				car_dispatch_recordVO.setDr_end_time(rs.getTimestamp("dr_end_time"));
				car_dispatch_recordVO.setDr_start_time_actual(rs.getTimestamp("dr_start_time_actual"));
				car_dispatch_recordVO.setDr_end_time_actual(rs.getTimestamp("dr_end_time_actual"));
				car_dispatch_recordVO.setDr_approve(rs.getByte("dr_approve"));
				car_dispatch_recordVO.setDr_cancel(rs.getByte("dr_cancel"));
				car_dispatch_recordVO.setMiles_before(rs.getInt("miles_before"));
				car_dispatch_recordVO.setMiles_after(rs.getInt("miles_after"));
				list.add(car_dispatch_recordVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	//
	@Override
	public void applyDispatch(Car_Dispatch_RecordVO car_dispatch_recordVO) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(APPLY_DISPATCH);

			pstmt.setString(1, car_dispatch_recordVO.getDr_apply_st());
			pstmt.setString(2, car_dispatch_recordVO.getDr_approve_st());
			pstmt.setString(3, car_dispatch_recordVO.getDr_apply_emp());
			pstmt.setString(4, car_dispatch_recordVO.getRcar_no());
			pstmt.setTimestamp(5, car_dispatch_recordVO.getDr_start_time());
			pstmt.setTimestamp(6, car_dispatch_recordVO.getDr_end_time());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	//
	@Override
	public void aprroveDispatch(int status, String emp_no, int dr_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(APPROVE_DISPATCH);

			pstmt.setInt(1, status);
			pstmt.setString(2, emp_no);
			pstmt.setInt(3, dr_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	//
	@Override
	public void cancelDispatch(int dr_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(CANCEL_DISPATCH);

			pstmt.setInt(1, dr_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	//
	@Override
	public void updateDispatch(Car_Dispatch_RecordVO car_dispatch_recordVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_DISPATCH);

			pstmt.setTimestamp(1, car_dispatch_recordVO.getDr_start_time_actual());
			pstmt.setTimestamp(2, car_dispatch_recordVO.getDr_end_time_actual());
			pstmt.setInt(3, car_dispatch_recordVO.getMiles_before());
			pstmt.setInt(4, car_dispatch_recordVO.getMiles_after());
			pstmt.setInt(5, car_dispatch_recordVO.getDr_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	/// ZOU新增
	@Override
	public boolean carDispatch(Car_Dispatch_RecordVO car_dispatch_recordVO) { // 出車
		Connection ct = null;
		PreparedStatement ps = null;
		RcarService rcarService = new RcarService();
		RcarVO rcarVO = rcarService.getCar(car_dispatch_recordVO.getRcar_no());
		car_dispatch_recordVO.setDr_start_time_actual(Timestamp.valueOf(LocalDateTime.now()));
		car_dispatch_recordVO.setMiles_before(rcarVO.getMiles());
		rcarVO.setRcar_status(2);
		rcarVO.setRcar_loc(car_dispatch_recordVO.getDr_apply_st());
		try {
			ct = ds.getConnection();
			ps = ct.prepareStatement(DISPATCH);
			ct.setAutoCommit(false);
			ps.setTimestamp(1, car_dispatch_recordVO.getDr_start_time_actual());
			ps.setInt(2, car_dispatch_recordVO.getMiles_before());
			ps.setInt(3, car_dispatch_recordVO.getDr_no());
			ps.executeUpdate();
			
			
			RcarDAOImpl rcarDAOImpl = new RcarDAOImpl();
			rcarDAOImpl.outCar(ct, rcarVO);
			///
//			ps.setInt(3, car_dispatch_recordVO.getDr_no()); 
			///
			ct.commit();
			ct.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			try {
				if (ct != null) {
					ct.rollback();
					System.out.println("rollback");
					return false;
				}
				System.err.println("Inserting is rolled back because of " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (ct != null) {
					ct.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean dispatchReturn(Car_Dispatch_RecordVO car_dispatch_recordVO) { //還車

		Connection ct = null;
		PreparedStatement ps = null;
		RcarService rcarService = new RcarService();
		RcarVO rcarVO = rcarService.getCar(car_dispatch_recordVO.getRcar_no());
		car_dispatch_recordVO.setDr_end_time_actual(Timestamp.valueOf(LocalDateTime.now()));
		rcarVO.setMiles(car_dispatch_recordVO.getMiles_after());
		try  {
			ct = ds.getConnection(); 
			ps = ct.prepareStatement(DISPATCH_RETURN);
			ct.setAutoCommit(false);
			ps.setTimestamp(1, car_dispatch_recordVO.getDr_end_time_actual());
			ps.setInt(2, car_dispatch_recordVO.getMiles_after());
			ps.setInt(3, car_dispatch_recordVO.getDr_no());
			
			ps.executeUpdate();
			
			RcarDAOImpl rcarDAOImpl = new RcarDAOImpl();
			rcarDAOImpl.inCar(ct, rcarVO);
			///
//			ps.setInt(4, car_dispatch_recordVO.getDr_no()); 
			///
			ct.commit();
			ct.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			try {
				if (ct != null) {
					ct.rollback();
					System.out.println("rollback");
					return false;
				}
				System.err.println("Inserting is rolled back because of " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (ct != null) {
					ct.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override // 取得 現在 ~ 7天 內調車紀錄
	public List<Car_Dispatch_RecordVO> betweenDispatchRecord(LocalDate date) {
		List<Car_Dispatch_RecordVO> list = new ArrayList<Car_Dispatch_RecordVO>();
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(MONTH_DISPATCH_RECORD);) {
			ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIN)));
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MAX).plusDays(7)));
			ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIN)));
			ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MAX).plusDays(7)));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Car_Dispatch_RecordVO vo = new Car_Dispatch_RecordVO();
				vo.setDr_no(rs.getInt("dr_no"));
				vo.setDr_apply_st(rs.getString("dr_apply_st"));
				vo.setDr_approve_st(rs.getString("dr_approve_st"));
				vo.setDr_apply_emp(rs.getString("dr_apply_emp"));
				vo.setDr_approve_emp(rs.getString("dr_approve_emp"));
				vo.setRcar_no(rs.getString("rcar_no"));
				vo.setDr_start_time(rs.getTimestamp("dr_start_time"));
				vo.setDr_end_time(rs.getTimestamp("dr_end_time"));
				vo.setDr_start_time_actual(rs.getTimestamp("dr_start_time_actual"));
				vo.setDr_end_time_actual(rs.getTimestamp("dr_end_time_actual"));
				vo.setDr_approve(rs.getByte("dr_approve"));
				vo.setDr_cancel(rs.getByte("dr_cancel"));
				vo.setMiles_before(rs.getInt("miles_before"));
				vo.setMiles_after(rs.getInt("miles_after"));
				list.add(vo);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override // 取得月份 調車紀錄 //
	public List<Car_Dispatch_RecordVO> monthDispatchRecord(LocalDate date) {
		List<Car_Dispatch_RecordVO> list = new ArrayList<Car_Dispatch_RecordVO>();
		LocalDate first = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
		LocalDate last = date.with(TemporalAdjusters.lastDayOfMonth());
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(MONTH_DISPATCH_RECORD)) {
			ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.of(first, LocalTime.MIN)));
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(last, LocalTime.MAX)));
			ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(first, LocalTime.MIN)));
			ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.of(last, LocalTime.MAX)));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Car_Dispatch_RecordVO vo = new Car_Dispatch_RecordVO();
				vo.setDr_no(rs.getInt("dr_no"));
				vo.setDr_apply_st(rs.getString("dr_apply_st"));
				vo.setDr_approve_st(rs.getString("dr_approve_st"));
				vo.setDr_apply_emp(rs.getString("dr_apply_emp"));
				vo.setDr_approve_emp(rs.getString("dr_approve_emp"));
				vo.setRcar_no(rs.getString("rcar_no"));
				vo.setDr_start_time(rs.getTimestamp("dr_start_time"));
				vo.setDr_end_time(rs.getTimestamp("dr_end_time"));
				vo.setDr_start_time_actual(rs.getTimestamp("dr_start_time_actual"));
				vo.setDr_end_time_actual(rs.getTimestamp("dr_end_time_actual"));
				vo.setDr_approve(rs.getByte("dr_approve"));
				vo.setDr_cancel(rs.getByte("dr_cancel"));
				vo.setMiles_before(rs.getInt("miles_before"));
				vo.setMiles_after(rs.getInt("miles_after"));
				list.add(vo);
			}
//			System.out.println("impl : "+list);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override //取得單筆調車紀錄
	public Car_Dispatch_RecordVO getDispatchRecord(int dr_no) {
		
		try (Connection ct = ds.getConnection();
				PreparedStatement ps = ct.prepareStatement(GETRECORD);
				){
			ps.setInt(1, dr_no);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Car_Dispatch_RecordVO vo = new Car_Dispatch_RecordVO();
				vo.setDr_no(rs.getInt("dr_no"));
				vo.setDr_apply_st(rs.getString("dr_apply_st"));
				vo.setDr_approve_st(rs.getString("dr_approve_st"));
				vo.setDr_apply_emp(rs.getString("dr_apply_emp"));
				vo.setDr_approve_emp(rs.getString("dr_approve_emp"));
				vo.setRcar_no(rs.getString("rcar_no"));
				vo.setDr_start_time(rs.getTimestamp("dr_start_time"));
				vo.setDr_end_time(rs.getTimestamp("dr_end_time"));
				vo.setDr_start_time_actual(rs.getTimestamp("dr_start_time_actual"));
				vo.setDr_end_time_actual(rs.getTimestamp("dr_end_time_actual"));
				vo.setDr_approve(rs.getByte("dr_approve"));
				vo.setDr_cancel(rs.getByte("dr_cancel"));
				vo.setMiles_before(rs.getInt("miles_before"));
				vo.setMiles_after(rs.getInt("miles_after"));
				return vo;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}