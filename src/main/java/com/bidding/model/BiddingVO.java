package com.bidding.model;

import java.sql.Timestamp;

public class BiddingVO {

	private Integer bid_no;

	private String scar_no;

	private String meb_no;

	private Integer bid_price;

	private Timestamp bid_time;

	public BiddingVO() {

	}
	public BiddingVO(Integer bid_no, String scar_no, String meb_no, Integer bid_price, Timestamp bid_time) {
		super();
		this.bid_no = bid_no;
		this.scar_no = scar_no;
		this.meb_no = meb_no;
		this.bid_price = bid_price;
		this.bid_time = bid_time;
	}



	public Integer getBid_no() {
		return bid_no;
	}



	public void setBid_no(Integer bid_no) {
		this.bid_no = bid_no;
	}



	public String getScar_no() {
		return scar_no;
	}



	public void setScar_no(String scar_no) {
		this.scar_no = scar_no;
	}



	public String getMeb_no() {
		return meb_no;
	}



	public void setMeb_no(String meb_no) {
		this.meb_no = meb_no;
	}



	public Integer getBid_price() {
		return bid_price;
	}



	public void setBid_price(Integer bid_price) {
		this.bid_price = bid_price;
	}



	public Timestamp getBid_time() {
		return bid_time;
	}



	public void setBid_time(Timestamp bid_time) {
		this.bid_time = bid_time;
	}



	// join scar
	public com.scar.model.ScarVO getScarVO() {
		com.scar.model.ScarService scarSrv = new com.scar.model.ScarService();
		com.scar.model.ScarVO scarVO = scarSrv.getScar(scar_no);
		return scarVO;
	}
//meb_no	
	public com.member.model.MemberVO getMemberVO(){
		com.member.model.MemberService memberSrc = new com.member.model.MemberService();
		com.member.model.MemberVO memberVo = memberSrc.member(meb_no);
		return memberVo;
	}



	@Override
	public String toString() {
		return "BiddingVO [bid_no=" + bid_no + ", scar_no=" + scar_no + ", meb_no=" + meb_no + ", bid_price="
				+ bid_price + ", bid_time=" + bid_time + "]";
	}


}
