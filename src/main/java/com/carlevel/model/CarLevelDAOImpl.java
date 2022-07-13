package com.carlevel.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import utils.MysqlJDBC;

public class CarLevelDAOImpl implements CarLevelDAO{
	private static DataSource ds;
	
	private final String INSERT = "INSERT INTO `CGA102G4`.`CAR_LEVEL` (`LEVEL_NO`, `LEVEL_NAME`) VALUES ( ? , ? );";
	
	private final String UPDATE = "UPDATE `CGA102G4`.`CAR_LEVEL` SET `LEVEL_NAME` = ? WHERE (`LEVEL_NO` = ? );";
	
	private final String ALL = "SELECT LEVEL_NO,LEVEL_NAME FROM CGA102G4.CAR_LEVEL;";
	
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}
	
	
	@Override
	public boolean insert(CarLevelVO CarLevelVO) {
		try (Connection ct = ds.getConnection();
			PreparedStatement ps = ct.prepareStatement(INSERT);){
			ps.setString(1, CarLevelVO.getLevel_no());
			ps.setString(2, CarLevelVO.getLevel_name());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CarLevelVO CarLevelVO) {
		try (Connection ct = ds.getConnection();
			PreparedStatement ps = ct.prepareStatement(UPDATE);){
			ps.setString(1, CarLevelVO.getLevel_name());
			ps.setString(2, CarLevelVO.getLevel_no());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CarLevelVO> getAll() {
		List<CarLevelVO> list = new ArrayList<>();
		try (Connection ct = ds.getConnection();
			PreparedStatement ps = ct.prepareStatement(ALL);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CarLevelVO vo = new CarLevelVO();
				vo.setLevel_no(rs.getString("level_no"));
				vo.setLevel_name(rs.getString("level_name"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
