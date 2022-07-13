package com.permission.model;

import java.util.List;

public class PermissionService {

	private PermissionDAO dao;
	
	public PermissionService() {
		dao = new PermissionDAOImpl();
	}
	
	public int insert(List<PermissionVO> list) {
		return dao.insert(list);
	}
	
	public void update(PermissionVO permissionVO, int pervo) {
		 dao.update(permissionVO, pervo);
	}
	
	public List<PermissionVO> getAll() {
		return dao.getAll();
	}
	
	public List<PermissionVO> getByEmpNo(String empNo) {
		return dao.getByEmpNo(empNo);
	}
	
	public int delete(List<PermissionVO> list) {
		return dao.delete(list);
	}
}
