package com.rcar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import utils.MysqlJDBC;

public class RcarDAOImpl implements RcarDAO {

	private static DataSource ds;

	private final String STALL = "SELECT * FROM RCAR WHERE ST_NO = ?";

	private final String INSERT = "INSERT INTO `CGA102G4`.`RCAR` (`RCAR_NO`, `ST_NO`, `MODEL_NO`, `RCAR_LOC`, `MILES`) "
			+ "VALUES ( ? ,  ? ,  ? , ? ,  ? );";

	private final String UPDATE = "UPDATE `CGA102G4`.`RCAR` " 
			+ "SET " 
			+ "    `ST_NO` = ? ," 
			+ "    `MODEL_NO` = ? ,"
			+ "    `RCAR_LOC` = ? ," 
			+ "    `MILES` = ? ," 
			+ "    `RCAR_STATUS` = ? " 
			+ "WHERE "
			+ "    (`RCAR_NO` = ? );";

	private final String ALL = "SELECT RCAR_NO, ST_NO, MODEL_NO, RCAR_LOC, MILES, RCAR_STATUS " + "FROM CGA102G4.RCAR;";

	private final String OTHERCAR = "SELECT " 
			+ "    RCAR_NO, ST_NO, MODEL_NO, RCAR_LOC, MILES, RCAR_STATUS " 
			+ "FROM "
			+ "    CGA102G4.RCAR " 
			+ "WHERE " 
			+ "    RCAR_LOC = ? AND ST_NO != ? ;";

	private final String GETCAR = "SELECT " 
			+ "    RCAR_NO, ST_NO, MODEL_NO, RCAR_LOC, MILES, RCAR_STATUS " 
			+ "FROM "
			+ "    CGA102G4.RCAR " 
			+ "WHERE " 
			+ "    RCAR_NO = ? ;";
	private final String OUTCAR = "UPDATE `CGA102G4`.`RCAR` " 
			+ "SET " 
			+ "    `RCAR_LOC` = ? ,"
			+ "    `RCAR_STATUS` = ? " 
			+ "WHERE " 
			+ "    (`RCAR_NO` = ? );";
	
	private final String INCAR = "UPDATE `CGA102G4`.`RCAR` "
			+ "SET "
			+ "    `MILES` = ? ,"
			+ "    `RCAR_STATUS` = 1 "
			+ "WHERE "
			+ "    (`RCAR_NO` = ? );";
	
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}

	@Override
	public boolean insert(RcarVO RcarVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(INSERT)) {
			ps.setString(1, RcarVO.getRcar_no());
			ps.setString(2, RcarVO.getSt_no());
			ps.setString(3, RcarVO.getModel_no());
			ps.setString(4, RcarVO.getRcar_loc());
			ps.setInt(5, RcarVO.getMiles());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(RcarVO RcarVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(UPDATE)) {

			ps.setString(1, RcarVO.getSt_no());
			ps.setString(2, RcarVO.getModel_no());
			ps.setString(3, RcarVO.getRcar_loc());
			ps.setInt(4, RcarVO.getMiles());
			ps.setInt(5, RcarVO.getRcar_status());
			ps.setString(6, RcarVO.getRcar_no());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<RcarVO> getSt_noAll(String st_no) {
		List<RcarVO> allcar = new ArrayList<>();
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(STALL);) {
			ps.setString(1, st_no);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RcarVO vo = new RcarVO();
				vo.setRcar_loc(rs.getString("RCAR_LOC"));
				vo.setRcar_no(rs.getString("RCAR_NO"));
				vo.setModel_no(rs.getString("MODEL_NO"));
				vo.setRcar_status(rs.getInt("RCAR_STATUS"));
				vo.setMiles(rs.getInt("MILES"));
				vo.setSt_no(rs.getString("ST_NO"));
				allcar.add(vo);
			}
//			System.out.println(allcar.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allcar;
	}

	@Override
	public List<RcarVO> getAll() {
		List<RcarVO> list = new ArrayList<>();
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(ALL)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RcarVO vo = new RcarVO();
				vo.setRcar_no(rs.getString("RCAR_NO"));
				vo.setSt_no(rs.getString("ST_NO"));
				vo.setModel_no(rs.getString("MODEL_NO"));
				vo.setRcar_loc(rs.getString("RCAR_LOC"));
				vo.setMiles(rs.getInt("MILES"));
				vo.setRcar_status(rs.getInt("RCAR_STATUS"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<RcarVO> getOtherStoreCar(String st_no) { // 取出非本站車輛
		List<RcarVO> list = new ArrayList<>();
		try (Connection ct = ds.getConnection(); 
				PreparedStatement ps = ct.prepareStatement(OTHERCAR)) {
			ps.setString(1, st_no);
			ps.setString(2, st_no);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RcarVO vo = new RcarVO();
				vo.setRcar_no(rs.getString("RCAR_NO"));
				vo.setSt_no(rs.getString("ST_NO"));
				vo.setModel_no(rs.getString("MODEL_NO"));
				vo.setRcar_loc(rs.getString("RCAR_LOC"));
				vo.setMiles(rs.getInt("MILES"));
				vo.setRcar_status(rs.getInt("RCAR_STATUS"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public RcarVO getCar(String rc_no) { // 取得車輛資訊
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(GETCAR);) {
			ps.setString(1, rc_no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			RcarVO vo = new RcarVO();
			vo.setRcar_no(rs.getString("RCAR_NO"));
			vo.setSt_no(rs.getString("ST_NO"));
			vo.setModel_no(rs.getString("MODEL_NO"));
			vo.setRcar_loc(rs.getString("RCAR_LOC"));
			vo.setMiles(rs.getInt("MILES"));
			vo.setRcar_status(rs.getInt("RCAR_STATUS"));
			return vo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void outCar(Connection ct, RcarVO RcarVO) throws SQLException { //調度出車
		PreparedStatement ps = null;

		ps = ct.prepareStatement(OUTCAR);
		ps.setString(1, RcarVO.getRcar_loc());
		ps.setInt(2, 2);
		ps.setString(3, RcarVO.getRcar_no());

		ps.executeUpdate();

	}

	@Override
	public void inCar(Connection ct, RcarVO RcarVO)  { //調度還車 修改車輛狀態
		PreparedStatement ps = null;
		
		try {
			ct.setAutoCommit(false);
			ps = ct.prepareStatement(INCAR);
			ps.setInt(1,RcarVO.getMiles());
			ps.setString(2,RcarVO.getRcar_no());
		
			ps.executeUpdate();
//			ct.commit();
//			ct.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
