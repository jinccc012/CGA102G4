package com.permission.model;

import java.util.List;


public interface PermissionDAO {

	

	// 新增
	int insert(List<PermissionVO> permissionVO);

	// 修改
	void update(PermissionVO permissionVO,int pervo);
	

	// 預覽全部權限
	List<PermissionVO> getAll();
	
	List<PermissionVO> getByEmpNo(String empNo);
	//	員編		功能編號
	// 	23 		19
	// 	23		21
	//	23		24
	
	// 刪除權限
	int delete(List<PermissionVO> permissionVO);
	
}
