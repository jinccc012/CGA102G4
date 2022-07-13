package com.employee.model;
import java.io.Serializable;
import java.sql.Date;
//員工
public class EmployeeVO implements Serializable {
	
//	EMP_NO	員工編號	VARCHAR
//	EMP_NAME	員工姓名	VARCHAR
//	EMP_PASS	員工密碼	VARCHAR
//	ST_NO	門市編號	VARCHAR
//	EMP_GENDER	性別	TINYINT
//	EMP_BIR	生日	DATE
//	EMP_TEL	電話	VARCHAR
//	EMP_ADRS	地址	VARCHAR
//	EMP_MAIL	電子郵件	VARCHAR
//	EMP_TITLE	職稱	VARCHAR
//	EMP_STATUS	員工狀態	TINYINT
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emp_no;
	private String emp_name;
	private String emp_pass;
	private String st_no;
	private Integer emp_gender;
	private Date emp_bir;
	private String emp_tel;
	private String emp_adrs;
	private String emp_mail;
	private String emp_title;
	private Integer emp_status;
	
	
	public EmployeeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeVO(String emp_no, String emp_name, String emp_pass, String st_no, Integer emp_gender, Date emp_bir,
			String emp_tel, String emp_adrs, String emp_mail, String emp_title, Integer emp_status) {
		super();
		this.emp_no = emp_no;
		this.emp_name = emp_name;
		this.emp_pass = emp_pass;
		this.st_no = st_no;
		this.emp_gender = emp_gender;
		this.emp_bir = emp_bir;
		this.emp_tel = emp_tel;
		this.emp_adrs = emp_adrs;
		this.emp_mail = emp_mail;
		this.emp_title = emp_title;
		this.emp_status = emp_status;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_pass() {
		return emp_pass;
	}
	public void setEmp_pass(String emp_pass) {
		this.emp_pass = emp_pass;
	}
	public String getSt_no() {
		return st_no;
	}
	public void setSt_no(String st_no) {
		this.st_no = st_no;
	}
	public Integer getEmp_gender() {
		return emp_gender;
	}
	public void setEmp_gender(Integer emp_gender) {
		this.emp_gender = emp_gender;
	}
	///////////////////////
	public Date getEmp_bir() {
		return emp_bir;
	}
	public void setEmp_bir(Date emp_bir) {
		this.emp_bir = emp_bir;
	}
	///////////////////////
	public String getEmp_tel() {
		return emp_tel;
	}
	public void setEmp_tel(String emp_tel) {
		this.emp_tel = emp_tel;
	}
	public String getEmp_adrs() {
		return emp_adrs;
	}
	public void setEmp_adrs(String emp_adrs) {
		this.emp_adrs = emp_adrs;
	}
	public String getEmp_mail() {
		return emp_mail;
	}
	public void setEmp_mail(String emp_mail) {
		this.emp_mail = emp_mail;
	}
	public String getEmp_title() {
		return emp_title;
	}
	public void setEmp_title(String emp_title) {
		this.emp_title = emp_title;
	}
	public Integer getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(Integer emp_status) {
		this.emp_status = emp_status;
	}
	@Override
	public String toString() {
		return "EmployeeVO [emp_no=" + emp_no + ", emp_name=" + emp_name + ", emp_pass=" + emp_pass + ", st_no=" + st_no
				+ ", emp_gender=" + emp_gender + ", emp_bir=" + emp_bir + ", emp_tel=" + emp_tel + ", emp_adrs="
				+ emp_adrs + ", emp_mail=" + emp_mail + ", emp_title=" + emp_title
				+ ", emp_status=" + emp_status + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
