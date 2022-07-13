package com.emp_function.model;

public class Emp_functionVO {

	private Integer empf_no;     //功能編號
	private String empf_name;	 //功能名稱
	
	
	
	public Integer getEmpf_no() {
		return empf_no;
	}
	public void setEmpf_no(Integer empf_no) {
		this.empf_no = empf_no;
	}
	public String getEmpf_name() {
		return empf_name;
	}
	public void setEmpf_name(String empf_name) {
		this.empf_name = empf_name;
	}
	@Override
	public String toString() {
		return "emp_function [empf_no=" + empf_no + ", empf_name=" + empf_name + "]";
	}
	public Emp_functionVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emp_functionVO(Integer empf_no, String empf_name) {
		super();
		this.empf_no = empf_no;
		this.empf_name = empf_name;
	}
	
	
	
	
	
}
