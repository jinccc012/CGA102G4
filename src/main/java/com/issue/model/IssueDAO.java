package com.issue.model;

import java.util.List;
import java.util.Map;

public interface IssueDAO {
	boolean insert(IssueVO IssueVO);

	boolean update(IssueVO IssueVO);

	boolean delete(Integer issue_no);

	// 點完成結案
	boolean cancel(int issue_no);

	public IssueVO findByPrimaryKey(Integer issue_no);
	
	// 用訂單狀態查詢
	List<IssueVO> getByOrderStatus(int status);
	
	public List<IssueVO> getAll();
	
	// 複合查詢
	public List<IssueVO> getByComposite(Map<String, String[]> map);


}
