package com.scar.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ScarVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String scar_no;
	private String st_no;
	private String scar_brand;
	private String scar_model;
	private String scar_color;
	private String scar_year;
	private String scar_cc;
	private String scar_trans;
	private String scar_fuel;
	private Integer scar_carrying;
	private String scar_carringpkg;
	private Integer scar_startprice; // 新增的欄位
	private Integer scar_price;
	private Integer scar_maxprice; // 新增的欄位
	private byte[] scar_photo;
	private Timestamp scar_startime;
	private Timestamp scar_endtime;
	private Integer scar_status;
	private Integer scar_miles;
	
	public ScarVO() {
		
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

	public String getScar_brand() {
		return scar_brand;
	}

	public void setScar_brand(String scar_brand) {
		this.scar_brand = scar_brand;
	}

	public String getScar_model() {
		return scar_model;
	}

	public void setScar_model(String scar_model) {
		this.scar_model = scar_model;
	}

	public String getScar_color() {
		return scar_color;
	}

	public void setScar_color(String scar_color) {
		this.scar_color = scar_color;
	}

	public String getScar_year() {
		return scar_year;
	}

	public void setScar_year(String scar_year) {
		this.scar_year = scar_year;
	}

	public String getScar_cc() {
		return scar_cc;
	}

	public void setScar_cc(String scar_cc) {
		this.scar_cc = scar_cc;
	}

	public String getScar_trans() {
		return scar_trans;
	}

	public void setScar_trans(String scar_trans) {
		this.scar_trans = scar_trans;
	}

	public String getScar_fuel() {
		return scar_fuel;
	}

	public void setScar_fuel(String scar_fuel) {
		this.scar_fuel = scar_fuel;
	}

	public Integer getScar_carrying() {
		return scar_carrying;
	}

	public void setScar_carrying(Integer scar_carrying) {
		this.scar_carrying = scar_carrying;
	}

	public String getScar_carringpkg() {
		return scar_carringpkg;
	}

	public void setScar_carringpkg(String scar_carringpkg) {
		this.scar_carringpkg = scar_carringpkg;
	}

	public Integer getScar_price() {
		return scar_price;
	}

	public void setScar_price(Integer scar_price) {
		this.scar_price = scar_price;
	}

	public byte[] getScar_photo() {
		return scar_photo;
	}

	public void setScar_photo(byte[] scar_photo) {
		this.scar_photo = scar_photo;
	}

	public Timestamp getScar_startime() {
		return scar_startime;
	}

	public void setScar_startime(Timestamp scar_startime) {
		this.scar_startime = scar_startime;
	}

	public Timestamp getScar_endtime() {
		return scar_endtime;
	}

	public void setScar_endtime(Timestamp scar_endtime) {
		this.scar_endtime = scar_endtime;
	}

	public Integer getScar_status() {
		return scar_status;
	}

	public void setScar_status(Integer scar_status) {
		this.scar_status = scar_status;
	}

	public Integer getScar_miles() {
		return scar_miles;
	}

	public void setScar_miles(Integer scar_miles) {
		this.scar_miles = scar_miles;
	}

	public Integer getScar_startprice() {
		return scar_startprice;
	}

	public void setScar_startprice(Integer scar_startprice) {
		this.scar_startprice = scar_startprice;
	}

	public Integer getScar_maxprice() {
		return scar_maxprice;
	}

	public void setScar_maxprice(Integer scar_maxprice) {
		this.scar_maxprice = scar_maxprice;
	}
	
}
