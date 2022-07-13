package com.scar.model;

public class Record {
	private String meb_no;
	private String bid_time;
	private Integer bid_price;
	
	public Record() {
		super();
	}
	
	public Record(String meb_no, String bid_time, Integer bid_price) {
		super();
		this.meb_no = meb_no;
		this.bid_time = bid_time;
		this.bid_price = bid_price;
	}

	public String getMeb_no() {
		return meb_no;
	}
	public void setMeb_no(String meb_no) {
		this.meb_no = meb_no;
	}
	public String getBid_time() {
		return bid_time;
	}
	public void setBid_time(String bid_time) {
		this.bid_time = bid_time;
	}

	public Integer getBid_price() {
		return bid_price;
	}

	public void setBid_price(Integer bid_price) {
		this.bid_price = bid_price;
	}
	
	
	
}
