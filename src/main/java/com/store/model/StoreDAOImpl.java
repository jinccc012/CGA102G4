package com.store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import utils.MysqlJDBC;

public class StoreDAOImpl implements StoreDAO{
	
	private final String ALLSQL= "SELECT * FROM STORE";
	private final String INSERT = "INSERT INTO `cga102g4`.`store` (`ST_NO`, `ST_NAME`, `ST_ADRS`, `ST_TEL`) VALUES (?, ?, ?, ?);";
	private final String UPDATE = "UPDATE `CGA102G4`.`STORE` SET `ST_NAME` = ?, `ST_ADRS` = ?, `ST_TEL` = ? WHERE (`ST_NO` = ?);";

	private static DataSource ds;
	
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}
	
	@Override
	public void insert(StoreVO StoreVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(INSERT);) {
			ps.setString(1, StoreVO.getSt_no());
			ps.setString(2, StoreVO.getSt_name());
			ps.setString(3, StoreVO.getSt_adrs());
			ps.setString(4, StoreVO.getSt_tel());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(StoreVO StoreVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(UPDATE);) {
			ps.setString(1, StoreVO.getSt_name());
			ps.setString(2, StoreVO.getSt_adrs());
			ps.setString(3, StoreVO.getSt_tel());
			ps.setString(4, StoreVO.getSt_no());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<StoreVO> getAll() {
		ArrayList<StoreVO> list = new ArrayList<>();
		
		try (Connection ct = ds.getConnection();
			PreparedStatement ps = ct.prepareStatement(ALLSQL);){
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				StoreVO vo = new StoreVO();
				vo.setSt_no(rs.getString("st_no"));
				vo.setSt_name(rs.getString("st_name"));
				vo.setSt_adrs(rs.getString("st_adrs"));
				vo.setSt_tel(rs.getString("st_tel"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
