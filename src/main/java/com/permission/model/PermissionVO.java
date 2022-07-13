package com.permission.model;

public class PermissionVO {

	private String emp_no;       //員工編號
	private Integer empf_no;     //功能編號
	
	
	
	
	public PermissionVO(String emp_no, Integer empf_no) {
		this.emp_no = emp_no;
		this.empf_no = empf_no;
	}
	public PermissionVO() {
		
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Integer getEmpf_no() {
		return empf_no;
	}
	public void setEmpf_no(Integer empf_no) {
		this.empf_no = empf_no;
	}
	@Override
	public String toString() {
		return "permissionvo [emp_no=" + emp_no + ", empf_no=" + empf_no + "]";
	}
	
	
	
	
	
}
