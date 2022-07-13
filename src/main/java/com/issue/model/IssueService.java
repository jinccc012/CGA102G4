package com.issue.model;

import java.util.List;
import java.util.Map;

public class IssueService {

	private IssueDAO dao;

	public IssueService() {
		dao = new IssueDAOImpl();
	}

	public boolean insert(IssueVO issueVO) {
		return dao.insert(issueVO);
	}

	public void update(IssueVO issueVO) {
		dao.update(issueVO);
	}
	
	public void cancel(int issue_no) {
		dao.cancel(issue_no);
	}

	public void delete(Integer issue_no) {
		dao.delete(issue_no);
	}

	public IssueVO getOneIssue(Integer issue_no) {
		return dao.findByPrimaryKey(issue_no);
	}

	public List<IssueVO> getAll() {
		return dao.getAll();
	}
	
	public List<IssueVO> getByOrderStatus(int status){
		return dao.getByOrderStatus(status);
	}

	public List<IssueVO> getByComposite(Map<String, String[]> map){
		return dao.getByComposite(map);
	}

}
