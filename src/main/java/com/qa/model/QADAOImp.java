package com.qa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import utils.MysqlJDBC;


public class QADAOImp implements QADAO {

	private static DataSource ds;

	private static final String INSERT_STMT = "INSERT INTO qa (QA_NO, QA_TITLE, QA_CONTENT, QA_TAG) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT qa_no, qa_title, qa_content, qa_tag FROM qa";
	private static final String GET_ONE_STMT = "SELECT qa_no, qa_title, qa_content, qa_tag FROM qa where qa_no = ?";
	private static final String DELETE = "DELETE FROM qa where qa_no = ?";
	private static final String UPDATE = "UPDATE qa set qa_no=?, qa_title=?, qa_content=?, qa_tag=? where qa_no = ?";
	
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}
	@Override
	public boolean insert(QAVO qaVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(INSERT_STMT)) {
			pstmt.setString(1, qaVO.getQa_no());
			pstmt.setString(2, qaVO.getQa_title());
			pstmt.setString(3, qaVO.getQa_content());
			pstmt.setString(4, qaVO.getQa_tag());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(QAVO qaVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(UPDATE)) {
			pstmt.setString(1, qaVO.getQa_no());
			pstmt.setString(2, qaVO.getQa_title());
			pstmt.setString(3, qaVO.getQa_content());
			pstmt.setString(4, qaVO.getQa_tag());
			pstmt.setString(5, qaVO.getQa_no());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delet(Integer qa_no) {
		try (Connection ct = ds.getConnection(); PreparedStatement pstmt = ct.prepareStatement(DELETE)) {
			pstmt.setInt(1, qa_no);

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public QAVO findByPrimaryKey(String qa_no) {
		QAVO qaVO = null;
		ResultSet rs = null;
		try (Connection ct = ds.getConnection();
				PreparedStatement pstmt = ct.prepareStatement(GET_ONE_STMT)) {
			pstmt.setInt(1, Integer.valueOf(qa_no));
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				qaVO = new QAVO();
				qaVO.setQa_no(rs.getString("qa_no"));
				qaVO.setQa_title(rs.getString("qa_title"));
				qaVO.setQa_content(rs.getString("qa_content"));
				qaVO.setQa_tag(rs.getString("qa_tag"));
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
		return qaVO;
	}

	@Override
	public List<QAVO> getAll() {
		List<QAVO> list = new ArrayList<QAVO>();
		try (Connection ct = ds.getConnection();
			PreparedStatement pstmt = ct.prepareStatement(GET_ALL_STMT);){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				QAVO vo = new QAVO();
				vo.setQa_no(rs.getString("qa_no"));
				vo.setQa_title(rs.getString("qa_title"));
				vo.setQa_content(rs.getString("qa_content"));
				vo.setQa_tag(rs.getString("qa_tag"));
				list.add(vo); // Store the row in the list
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		return list;
	}
}
