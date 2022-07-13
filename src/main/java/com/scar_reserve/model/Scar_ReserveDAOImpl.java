package com.scar_reserve.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import utils.MysqlJDBC;

public class Scar_ReserveDAOImpl implements Scar_ReserveDAO {

	private static DataSource ds;

	private static final String INSERT_STMT = "insert into SCAR_RESERVE (meb_no, scar_no, st_no, sr_time) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select sr_no, meb_no, scar_no, st_no, sr_time from CGA102G4.SCAR_RESERVE order by sr_no";
	private static final String GET_ONE_STMT = "select sr_no, meb_no, scar_no, st_no, sr_time from CGA102G4.SCAR_RESERVE where sr_no = ?";
	private static final String DELETE = "delete from CGA102G4.SCAR_RESERVE where sr_no = ?";
	private static final String UPDATE = "update CGA102G4.SCAR_RESERVE set meb_no = ?, scar_no = ?, st_no = ?, sr_time = ? where sr_no = ?";
	private static final String GETBYTIME = "select * from CGA102G4.SCAR_RESERVE where SR_TIME between ? and ?;";
	private static final String GETBYMEBNO = "SELECT SR_NO, MEB_NO, SCAR_NO, ST_NO, SR_TIME FROM SCAR_RESERVE WHERE MEB_NO = ? ORDER BY SR_NO DESC";

	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}
	@Override
	public void insert(Scar_ReserveVO Scar_ReserveVO) {

		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(INSERT_STMT)) {

			pstmt.setString(1, Scar_ReserveVO.getMeb_no());
			pstmt.setString(2, Scar_ReserveVO.getScar_no());
			pstmt.setString(3, Scar_ReserveVO.getSt_no());
			pstmt.setTimestamp(4, Scar_ReserveVO.getSr_time());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		
		}
	}

	@Override
	public void update(Scar_ReserveVO Scar_ReserveVO) {

		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(UPDATE)) {

			pstmt.setString(1, Scar_ReserveVO.getMeb_no());
			pstmt.setString(2, Scar_ReserveVO.getScar_no());
			pstmt.setString(3, Scar_ReserveVO.getSt_no());
			pstmt.setTimestamp(4, Scar_ReserveVO.getSr_time());
			pstmt.setInt(5, Scar_ReserveVO.getSr_no());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		} 
	}

	@Override
	public void delete(Integer sr_no) {

		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(DELETE)) {

			pstmt.setInt(1, sr_no);

			pstmt.executeUpdate();

			} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		} 
	}

	@Override
	public Scar_ReserveVO findByPrimaryKey(Integer sr_no) {
		Scar_ReserveVO scar_ReserveVO = null;
		ResultSet rs = null;

		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(GET_ONE_STMT)) {

			pstmt.setInt(1, sr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				scar_ReserveVO = new Scar_ReserveVO();
				scar_ReserveVO.setSr_no(rs.getInt("sr_no"));
				scar_ReserveVO.setMeb_no(rs.getString("meb_no"));
				scar_ReserveVO.setScar_no(rs.getString("scar_no"));
				scar_ReserveVO.setSt_no(rs.getString("st_no"));
				scar_ReserveVO.setSr_time(rs.getTimestamp("sr_time"));

			}
	} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		} 
		return scar_ReserveVO;
	}

	@Override
	public List<Scar_ReserveVO> getAll() {
		List<Scar_ReserveVO> list = new ArrayList<Scar_ReserveVO>();
		Scar_ReserveVO scar_ReserveVO = null;

		ResultSet rs = null;

		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(GET_ALL_STMT)) {
			rs = pstmt.executeQuery();

			while (rs.next()) {
				scar_ReserveVO = new Scar_ReserveVO();
				scar_ReserveVO.setSr_no(rs.getInt("sr_no"));
				scar_ReserveVO.setMeb_no(rs.getString("meb_no"));
				scar_ReserveVO.setScar_no(rs.getString("scar_no"));
				scar_ReserveVO.setSt_no(rs.getString("st_no"));
				scar_ReserveVO.setSr_time(rs.getTimestamp("sr_time"));
				list.add(scar_ReserveVO); // Store the row in the list
			}
			} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		} 

		return list;
	}
	
	
	@Override
	public List<Scar_ReserveVO> getByTime(Timestamp start, Timestamp end) {

		List<Scar_ReserveVO> list = new ArrayList<Scar_ReserveVO>();
		Scar_ReserveVO scar_ReserveVO = null;

		ResultSet rs = null;

		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(GETBYTIME)) {
			pstmt.setTimestamp(1, start); // 不寫會產生 RuntimeException
			pstmt.setTimestamp(2, end);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				scar_ReserveVO = new Scar_ReserveVO();
				scar_ReserveVO.setSr_no(rs.getInt("sr_no"));
				scar_ReserveVO.setMeb_no(rs.getString("meb_no"));
				scar_ReserveVO.setScar_no(rs.getString("scar_no"));
				scar_ReserveVO.setSt_no(rs.getString("st_no"));
				scar_ReserveVO.setSr_time(rs.getTimestamp("sr_time"));

				list.add(scar_ReserveVO); // Store the row in the list
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		} 

		return list;
	}
	
		@Override
	public List<Scar_ReserveVO> getAll(Map<String, String[]> map) {
		List<Scar_ReserveVO> list = new ArrayList<Scar_ReserveVO>();
		Scar_ReserveVO scar_ReserveVO = null;
		String finalSQL = "select * from CGA102G4.SCAR_RESERVE " + jdbcUtilScarReserve.get_WhereCondition(map);
		
		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(finalSQL)) {
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				scar_ReserveVO = new Scar_ReserveVO();
				scar_ReserveVO.setSr_no(rs.getInt("sr_no"));
				scar_ReserveVO.setMeb_no(rs.getString("meb_no"));
				scar_ReserveVO.setScar_no(rs.getString("scar_no"));
				scar_ReserveVO.setSt_no(rs.getString("st_no"));
				scar_ReserveVO.setSr_time(rs.getTimestamp("sr_time"));
				list.add(scar_ReserveVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

		@Override
		public List<Scar_ReserveVO> getMebScarReserve(String meb_no) {
			List<Scar_ReserveVO> list = new ArrayList<>();
			try(Connection conn = ds.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(GETBYMEBNO)){
				pstmt.setString(1, meb_no);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Scar_ReserveVO scar_reserveVO = new Scar_ReserveVO();
					scar_reserveVO.setSr_no(rs.getInt("sr_no"));
					scar_reserveVO.setMeb_no(meb_no);
					scar_reserveVO.setScar_no(rs.getString("scar_no"));
					scar_reserveVO.setSt_no(rs.getString("st_no"));
					scar_reserveVO.setSr_time(rs.getTimestamp("sr_time"));
					list.add(scar_reserveVO);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	

	



}
