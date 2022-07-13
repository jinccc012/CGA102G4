package com.emp_function.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import utils.MysqlJDBC;

public class Emp_functionDAOImpl  implements Emp_functionDAO{
	
	private static DataSource ds ;
	private final String INSERT ="INSERT INTO `cga102g4`.`emp_function` (`EMPF_NAME`) VALUES (?);";
	private final String UPDATE ="UPDATE `cga102g4`.`emp_function` SET `EMPF_NAME` = ? WHERE (`EMPF_NO` = ? );";
	private final String SELECT ="SELECT EMPF_NO, EMPF_NAME FROM emp_function ";
	
//	連線池
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}
	
	@Override
	public void insert(Emp_functionVO emp_functionVO) {
		// TODO Auto-generated method stub
		try (Connection ct = ds.getConnection();
			PreparedStatement ps = ct.prepareStatement(INSERT);){
			ps.setString(1,emp_functionVO.getEmpf_name());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Emp_functionVO emp_functionVO) {
//		try (Connection ct = ds.getConnection();
//			PreparedStatement ps = ct.prepareStatement(UPDATE)) {
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		Connection ct = null;
		PreparedStatement ps = null;
		
		try {
			ct = ds.getConnection();			
			ps = ct.prepareStatement(UPDATE);
			
			ps.setString(1,emp_functionVO.getEmpf_name() );
			ps.setInt(2,emp_functionVO.getEmpf_no());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (ps!= null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ct!= null) {
				try {
					ct.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	@Override
	public List<Emp_functionVO> getAll() {
		List<Emp_functionVO> List = new ArrayList<Emp_functionVO>();
		Emp_functionVO VO = null;
		
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
				
		try {
			ct = ds.getConnection();
			ps = ct.prepareStatement(SELECT);
			 rs= ps.executeQuery();
			 
			 while (rs.next()) {
				 VO = new Emp_functionVO();
				 VO.setEmpf_no(rs.getInt("empf_no"));
				 VO.setEmpf_name(rs.getString("empf_name"));
				 List.add(VO);
			 }	 
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ct != null) {
				try {
					ct.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return List;
	}

	
	
	
}
