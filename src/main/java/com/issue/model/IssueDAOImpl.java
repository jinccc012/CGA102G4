package com.issue.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import utils.MysqlJDBC;
import utils.jdbcUtilIssue;

public class IssueDAOImpl implements IssueDAO {
	private static DataSource ds;
	private static final String INSERT = "INSERT INTO issue (issue_name, issue_tel, issue_mail, issue_content) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT issue_no, issue_name, issue_tel, issue_mail, issue_content, issue_process_state FROM issue order by issue_no";
	private static final String GET_ONE_STMT = "SELECT issue_no, issue_name, issue_tel, issue_mail, issue_content, issue_process_state FROM issue where issue_no = ?";
	private static final String DELETE = "DELETE FROM issue where issue_no = ?";
	private static final String UPDATE = "UPDATE issue set issue_name=?, ssue_tel=?, issue_mail=?, issue_content=?, issue_process_state=? where issue_no = ?";
	private final String GET_BY_STATUS = "SELECT * FROM ISSUE WHERE ISSUE_PROCESS_STATE = ?";

	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}

	@Override
	public boolean insert(IssueVO issueVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(INSERT)) {
			pstmt.setString(1, issueVO.getIssue_name());
			pstmt.setString(2, issueVO.getIssue_tel());
			pstmt.setString(3, issueVO.getIssue_mail());
			pstmt.setString(4, issueVO.getIssue_content());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(IssueVO issueVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(UPDATE)) {
			pstmt.setString(1, issueVO.getIssue_name());
			pstmt.setString(2, issueVO.getIssue_tel());
			pstmt.setString(3, issueVO.getIssue_mail());
			pstmt.setString(4, issueVO.getIssue_content());
			pstmt.setInt(5, issueVO.getIssue_process_state());
			pstmt.setInt(6, issueVO.getIssue_no());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer issue_no) {
		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(DELETE)) {
			pstmt.setInt(1, issue_no);

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public IssueVO findByPrimaryKey(Integer issue_no) {
		IssueVO issueVO = null;
		ResultSet rs = null;

		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(GET_ONE_STMT)) {
			rs = pstmt.executeQuery();

			while (rs.next()) {
				issueVO = new IssueVO();
				issueVO.setIssue_no(rs.getInt("issue_no"));
				issueVO.setIssue_name(rs.getString("issue_tel"));
				issueVO.setIssue_mail(rs.getString("issue_mail"));
				issueVO.setIssue_content(rs.getString("issue_content"));
				issueVO.setIssue_process_state(rs.getInt("issue_process_state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return issueVO;
	}

	@Override
	public List<IssueVO> getAll() {
		List<IssueVO> list = new ArrayList<IssueVO>();
		IssueVO issueVO = null;
		ResultSet rs = null;
		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(GET_ALL_STMT)) {
			rs = pstmt.executeQuery();

			while (rs.next()) {
				issueVO = new IssueVO();
				issueVO.setIssue_no(rs.getInt("issue_no"));
				issueVO.setIssue_name(rs.getString("issue_name"));
				issueVO.setIssue_tel(rs.getString("issue_tel"));
				issueVO.setIssue_mail(rs.getString("issue_mail"));
				issueVO.setIssue_content(rs.getString("issue_content"));
				issueVO.setIssue_process_state(rs.getInt("issue_process_state"));
				list.add(issueVO); // Store the row in the list
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return list;
	}


	@Override
	public List<IssueVO> getByOrderStatus(int status) {
		List<IssueVO> list = new ArrayList<>();
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(GET_BY_STATUS);) {
			ps.setInt(1, status);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				IssueVO issueVO = new IssueVO();
				issueVO.setIssue_no(rs.getInt("issue_no"));
				issueVO.setIssue_name(rs.getString("issue_name"));
				issueVO.setIssue_tel(rs.getString("issue_tel"));
				issueVO.setIssue_mail(rs.getString("issue_mail"));
				issueVO.setIssue_content(rs.getString("issue_content"));
				issueVO.setIssue_process_state(rs.getInt("issue_process_state"));
				list.add(issueVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<IssueVO> getByComposite(Map<String, String[]> map) {
		List<IssueVO> list = new ArrayList<IssueVO>();

		String finalSQL = "SELECT * FROM CGA102G4.ISSUE " + jdbcUtilIssue.get_WhereCondition(map);
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(finalSQL);) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				IssueVO issueVO = new IssueVO();
				issueVO.setIssue_no(rs.getInt("issue_no"));
				issueVO.setIssue_name(rs.getString("issue_name"));
				issueVO.setIssue_tel(rs.getString("issue_tel"));
				issueVO.setIssue_mail(rs.getString("issue_mail"));
				issueVO.setIssue_content(rs.getString("issue_content"));
				issueVO.setIssue_process_state(rs.getInt("issue_process_state"));
				list.add(issueVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	private final String CANCEL = "UPDATE " + "ISSUE " + "SET ISSUE_PROCESS_STATE = ? " + "WHERE ISSUE_NO = ?";
	
	//結案
	@Override
	public boolean cancel(int issue_no) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(CANCEL);){
			ps.setInt(1, 1);
			ps.setInt(2, issue_no);
			
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
