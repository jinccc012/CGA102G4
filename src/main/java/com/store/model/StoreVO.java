package com.store.model;
import java.io.Serializable;

//門市
public class StoreVO implements Serializable{
	
//	ST_NO	門市編號	VARCHAR
//	ST_NAME	門市名稱	VARCHAR
//	ST_ADRS	門市地址	VARCHAR
//	ST_TEL	門市電話	VARCHAR
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String st_no;
	private String st_name;
	private String st_adrs;
	private String st_tel;
	
	
	
	
	
	public StoreVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StoreVO(String st_no, String st_name, String st_adrs, String st_tel) {
		super();
		this.st_no = st_no;
		this.st_name = st_name;
		this.st_adrs = st_adrs;
		this.st_tel = st_tel;
	}
	public String getSt_no() {
		return st_no;
	}
	public void setSt_no(String st_no) {
		this.st_no = st_no;
	}
	public String getSt_name() {
		return st_name;
	}
	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}
	public String getSt_adrs() {
		return st_adrs;
	}
	public void setSt_adrs(String st_adrs) {
		this.st_adrs = st_adrs;
	}
	public String getSt_tel() {
		return st_tel;
	}
	public void setSt_tel(String st_tel) {
		this.st_tel = st_tel;
	}
	@Override
	public String toString() {
		return "Store [st_no=" + st_no + ", st_name=" + st_name + ", st_adrs=" + st_adrs + ", st_tel=" + st_tel + "]";
	}
	
	
}
