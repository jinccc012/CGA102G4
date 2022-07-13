package com.successful_bid.model;

import java.sql.Connection;
import java.util.List;

public interface Successful_BidDAO {

	public void insert(Successful_BidVO successful_bidvo);
	public void insertTransaction(Successful_BidVO successful_bidvo,Connection con);

//	public void update(Successful_BidVO successful_bidvo);

//	public void delete(Integer sb_no);

	// 更改收款狀況
	public void updateBySb_non_paying(Integer sb_no, String meb_no, Integer payState);

	// 查詢紀錄得標的
	public Successful_BidVO getOneSbNo(Integer sb_no);

	// 查詢一場競拍 誰準備付款
	public List<Successful_BidVO> getWin2BySCar(String scar_no);

	// 查詢全部得標紀錄
	public List<Successful_BidVO> getAll();

}
