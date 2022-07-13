package com.permission.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import utils.MysqlJDBC;

public class PermissionDAOImpl implements PermissionDAO {

	private static DataSource ds;
	private final String INSERT = "INSERT INTO `cga102g4`.`permission` (`EMP_NO`, `EMPF_NO`) VALUES (?, ?);";
	private final String UPDATE = "UPDATE `cga102g4`.`permission` SET `EMPF_NO` = ? WHERE (`EMP_NO` = ?) and (`EMPF_NO` = ?);";
	private final String SELECT = "select emp_no, empf_no from permission;";

//	連線池
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}

	@Override
	public int insert(List<PermissionVO> list) {
		int count = 0;
		Connection ct = null;
		PreparedStatement ps = null;
		try {
			ct = ds.getConnection();
			ps = ct.prepareStatement(INSERT);
			ct.setAutoCommit(false);
			for (PermissionVO vo : list) {
				ps.setString(1, vo.getEmp_no());
				ps.setInt(2, vo.getEmpf_no());

				ps.executeUpdate();
				count++;
			}
			ct.commit();
			ct.setAutoCommit(true);
			return count;
		} catch (SQLException e) {
			try {
				if (ct != null) {
					ct.rollback();
					System.out.println("rollback");
					return 0;
				}
				System.err.println("Inserting is rolled back because of " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		} finally {
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
	public void update(PermissionVO permissionVO, int pervo) {

		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(UPDATE);) {
			ps.setInt(1, pervo);
			ps.setString(2, permissionVO.getEmp_no());
			ps.setInt(3, permissionVO.getEmpf_no());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<PermissionVO> getAll() {
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO vo = null;

		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(SELECT);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vo = new PermissionVO();
				vo.setEmp_no(rs.getString("emp_no"));
				vo.setEmpf_no(rs.getInt("empf_no"));
				list.add(vo);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<PermissionVO> getByEmpNo(String empNo) {
		String sql = "SELECT EMP_NO,EMPF_NO FROM CGA102G4.PERMISSION where EMP_NO = ?;";
		PermissionVO vo = null;
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(sql);) {
			ps.setString(1, empNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vo = new PermissionVO();
				vo.setEmp_no(rs.getString("emp_no"));
				vo.setEmpf_no(rs.getInt("empf_no"));
				list.add(vo);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int delete(List<PermissionVO> list) {
		String sql = "DELETE FROM `CGA102G4`.`PERMISSION` WHERE (`EMP_NO` = ? ) and (`EMPF_NO` = ? );";
		int count = 0;
		Connection ct = null;
		PreparedStatement ps = null;
		try {
			ct = ds.getConnection();
			ps = ct.prepareStatement(sql);
			ct.setAutoCommit(false);
			for (PermissionVO vo : list) {
				ps.setString(1, vo.getEmp_no());
				ps.setInt(2, vo.getEmpf_no());

				ps.executeUpdate();
				count++;
			}

			ct.commit();
			ct.setAutoCommit(true);
			return count;
		} catch (SQLException e) {
			try {
				if (ct != null) {
					ct.rollback();
					System.out.println("rollback");
					return 0;
				}
				System.err.println("Inserting is rolled back because of " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		} finally {
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

}
