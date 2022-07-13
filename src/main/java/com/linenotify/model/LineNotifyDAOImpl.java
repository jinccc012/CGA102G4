package com.linenotify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import utils.MysqlJDBC;

public class LineNotifyDAOImpl implements LineNotifyDAO{
	private static DataSource ds;
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}
	
	private static String INSERT = "INSERT INTO `CGA102G4`.`LINE_NOTIFY` (`MEB_NO`, `CODE`, `STATE`, `LINE_TOKEN`) VALUES (?, ?, ?, ?)";
	private static String UPDATE = "UPDATE `CGA102G4`.`LINE_NOTIFY` SET `CODE` = ?, `STATE` = ?, `LINE_TOKEN` = ? WHERE (`MEB_NO` = ?)";
	private static String GETONE = "SELECT * FROM `CGA102G4`.`LINE_NOTIFY` WHERE (`MEB_NO` = ?);";
	
	@Override
	public void insert(LineNotifyVO lineVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(INSERT);) {
			ps.setString(1, lineVO.getMebNo());
			ps.setString(2, lineVO.getCode());
			ps.setString(3, lineVO.getState());
			ps.setString(4, lineVO.getLineToken());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(LineNotifyVO lineVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(UPDATE);) {

			ps.setString(1, lineVO.getCode());
			ps.setString(2, lineVO.getState());
			ps.setString(3, lineVO.getLineToken());
			
			ps.setString(4, lineVO.getMebNo());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 查到回傳vo，查不到回傳null
	@Override
	public LineNotifyVO getOne(String mebNo) {
		LineNotifyVO lineVO = new LineNotifyVO();
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(GETONE);) {
			ps.setString(1, mebNo);
			
			// 如果沒有查到 直接return null
			if(ps.executeQuery().next() == false) {
				return null;
			}
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				lineVO.setMebNo(rs.getString("MEB_NO"));
				lineVO.setCode(rs.getString("CODE"));
				lineVO.setState(rs.getString("STATE"));
				lineVO.setLineToken(rs.getString("LINE_TOKEN"));
				return lineVO;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
