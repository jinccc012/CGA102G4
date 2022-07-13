package com.successful_bid.model;

import java.sql.Timestamp;

public class Successful_BidVO {
	private Integer sb_no;
	private String scar_no;
	private String meb_no;
	private Integer sb_price;
	private	Timestamp sb_win_time;
	private Integer sb_non_paying;
	private Integer	sb_rank; //

	public Successful_BidVO() {
		
	}
	
	
	
	public Successful_BidVO(Integer sb_no, String scar_no, String meb_no, Integer sb_price, Timestamp sb_win_time,
			Integer sb_non_paying, Integer sb_rank) {
		super();
		this.sb_no = sb_no;
		this.scar_no = scar_no;
		this.meb_no = meb_no;
		this.sb_price = sb_price;
		this.sb_win_time = sb_win_time;
		this.sb_non_paying = sb_non_paying;
		this.sb_rank = sb_rank;
	}



	@Override
	public String toString() {
		return "Successful_BidVO [sb_no=" + sb_no + ", scar_no=" + scar_no + ", meb_no=" + meb_no + ", sb_price="
				+ sb_price + ", sb_win_time=" + sb_win_time + ", sb_non_paying=" + sb_non_paying + ", sb_rank="
				+ sb_rank + "]";
	}



	public Integer getSb_no() {
		return sb_no;
	}



	public void setSb_no(Integer sb_no) {
		this.sb_no = sb_no;
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



	public Integer getSb_price() {
		return sb_price;
	}



	public void setSb_price(Integer sb_price) {
		this.sb_price = sb_price;
	}



	public Timestamp getSb_win_time() {
		return sb_win_time;
	}



	public void setSb_win_time(Timestamp sb_win_time) {
		this.sb_win_time = sb_win_time;
	}



	public Integer getSb_non_paying() {
		return sb_non_paying;
	}



	public void setSb_non_paying(Integer sb_non_paying) {
		this.sb_non_paying = sb_non_paying;
	}



	public Integer getSb_rank() {
		return sb_rank;
	}



	public void setSb_rank(Integer sb_rank) {
		this.sb_rank = sb_rank;
	}



	// join scar
	public com.scar.model.ScarVO getScarVO() {
		com.scar.model.ScarService scarSrv = new com.scar.model.ScarService();
		com.scar.model.ScarVO scarVO = scarSrv.getScar(scar_no);
		return scarVO;
	}
	
	public com.member.model.MemberVO getMemberVO(){
		com.member.model.MemberService memberSrc = new com.member.model.MemberService();
		com.member.model.MemberVO memberVo = memberSrc.member(meb_no);
		return memberVo;
	}
	
	
	
	
}
