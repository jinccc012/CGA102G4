package com.scar_reserve.model;

import java.sql.Timestamp;

public class Scar_ReserveVO {
	private Integer sr_no;
	private String meb_no;
	private String scar_no;
	private String st_no;
	private Timestamp sr_time;
	
	public Integer getSr_no() {
		return sr_no;
	}
	public void setSr_no(Integer sr_no) {
		this.sr_no = sr_no;
	}
	public String getMeb_no() {
		return meb_no;
	}
	public void setMeb_no(String meb_no) {
		this.meb_no = meb_no;
	}
	public String getScar_no() {
		return scar_no;
	}
	public void setScar_no(String scar_no) {
		this.scar_no = scar_no;
	}
	public String getSt_no() {
		return st_no;
	}
	public void setSt_no(String st_no) {
		this.st_no = st_no;
	}
	public Timestamp getSr_time() {
		return sr_time;
	}
	public void setSr_time(Timestamp sr_time) {
		this.sr_time = sr_time;
	}
	@Override
	public String toString() {
		return "Scar_ReserveVO [sr_no=" + sr_no + ", meb_no=" + meb_no + ", scar_no=" + scar_no + ", st_no=" + st_no
				+ ", sr_time=" + sr_time + "]";
	}
	public Scar_ReserveVO() {
	
	}
	public Scar_ReserveVO(Integer sr_no, String meb_no, String scar_no, String st_no, Timestamp sr_time) {
		super();
		this.sr_no = sr_no;
		this.meb_no = meb_no;
		this.scar_no = scar_no;
		this.st_no = st_no;
		this.sr_time = sr_time;
	}
	

}
