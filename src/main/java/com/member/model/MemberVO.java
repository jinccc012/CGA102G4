package com.member.model;
import java.io.Serializable;
import java.sql.Date;




//員工
public class MemberVO implements Serializable{
	
//	MEB_NO	會員編號	VARCHAR
//	MEB_PASS	會員密碼	VARCHAR
//	MEB_NAME	會員姓名	VARCHAR
//	MEB_GENDER	性別	TINYINT
//	MEB_BIR	生日	DATE
//	MEB_TEL	電話	VARCHAR
//	MEB_ADRS	地址	VARCHAR
//	MEB_MAIL	電子郵件	VARCHAR
//	CREATION	註冊日期	DATE
//	MEB_PROFILE	大頭貼	MEDIUMBLOB
//	MEB_BONUS	會員點數	INT
//	MEB_STATUS	會員狀態	TINYINT
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String meb_no;
	private String meb_pass;
	private String meb_name;
	private Integer meb_gender;
	private Date meb_bir;
	private String meb_tel;
	private String meb_adrs;
	private String meb_mail;
	private Date creation;
	private byte[] meb_profile;
	private Integer meb_bonus;
	private Integer meb_status;
	
	
	
	
	public MemberVO() {
		super();
	}
	public MemberVO(String meb_no, String meb_pass, String meb_name, Integer meb_gender, Date meb_bir, String meb_tel,
			String meb_adrs, String meb_mail, Date creation, byte[] meb_profile, Integer meb_bonus,
			Integer meb_status) {
		super();
		this.meb_no = meb_no;
		this.meb_pass = meb_pass;
		this.meb_name = meb_name;
		this.meb_gender = meb_gender;
		this.meb_bir = meb_bir;
		this.meb_tel = meb_tel;
		this.meb_adrs = meb_adrs;
		this.meb_mail = meb_mail;
		this.creation = creation;
		this.meb_profile = meb_profile;
		this.meb_bonus = meb_bonus;
		this.meb_status = meb_status;
	}
	public String getMeb_no() {
		return meb_no;
	}
	public void setMeb_no(String meb_no) {
		this.meb_no = meb_no;
	}
	public String getMeb_pass() {
		return meb_pass;
	}
	public void setMeb_pass(String meb_pass) {
		this.meb_pass = meb_pass;
	}
	public String getMeb_name() {
		return meb_name;
	}
	public void setMeb_name(String meb_name) {
		this.meb_name = meb_name;
	}
	public Integer getMeb_gender() {
		return meb_gender;
	}
	public void setMeb_gender(Integer meb_gender) {
		this.meb_gender = meb_gender;
	}
	public Date getMeb_bir() {
		return meb_bir;
	}
	public void setMeb_bir(Date meb_bir) {
		this.meb_bir = meb_bir;
	}
	public String getMeb_tel() {
		return meb_tel;
	}
	public void setMeb_tel(String meb_tel) {
		this.meb_tel = meb_tel;
	}
	public String getMeb_adrs() {
		return meb_adrs;
	}
	public void setMeb_adrs(String meb_adrs) {
		this.meb_adrs = meb_adrs;
	}
	public String getMeb_mail() {
		return meb_mail;
	}
	public void setMeb_mail(String meb_mail) {
		this.meb_mail = meb_mail;
	}
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	public byte[] getMeb_profile() {
		return meb_profile;
	}
	public void setMeb_profile(byte[] meb_profile) {
		this.meb_profile = meb_profile;
	}
	public Integer getMeb_bonus() {
		return meb_bonus;
	}
	public void setMeb_bonus(Integer meb_bonus) {
		this.meb_bonus = meb_bonus;
	}
	public Integer getMeb_status() {
		return meb_status;
	}
	public void setMeb_status(Integer meb_status) {
		this.meb_status = meb_status;
	}
	@Override
	public String toString() {
		return "Member [meb_no=" + meb_no + ", meb_pass=" + meb_pass + ", meb_name=" + meb_name + ", meb_gender="
				+ meb_gender + ", meb_bir=" + meb_bir + ", meb_tel=" + meb_tel + ", meb_adrs=" + meb_adrs
				+ ", meb_mail=" + meb_mail + ", creation=" + creation +  ", meb_bonus=" + meb_bonus + ", meb_status=" + meb_status + "]";
	}
	
	
	
	
	
}
