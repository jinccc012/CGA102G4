package com.lineserver.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import utils.MysqlJDBC;

public class LineServerDAOImpl implements LineServerDAO{
	private static DataSource ds;
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}
	String GETONE = "SELECT * FROM CGA102G4.LINE_SERVER";
	@Override
	public LineServerVO getOne() {
		LineServerVO serverVO = new LineServerVO();
		
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(GETONE);) {
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				serverVO.setNgrokURL(rs.getString("NGROK_URL"));
				serverVO.setQrCode(rs.getBytes("QRcode"));
				return serverVO;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
