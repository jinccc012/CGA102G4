package com.bidding.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BiddingDAO {
	
	// 出價
	public boolean insert(String scar_no, String meb_no, Integer bid_price, Connection conn);
	
	public List<BiddingVO> getAll();

	public List<BiddingVO> getBidByMEB_NO(String MEB_NO);
	
	public List<BiddingVO> getBidByScar_NoGreaterPrice(String Scar_NO,Integer scar_price);
	
	public List<BiddingVO> getBidByScar_No(String Scar_NO);
	
	public BiddingVO getBidByScar_Nohighest(String Scar_NO);
	
	public  List<BiddingVO> getAllQuery(Map<String, String[]> map);
	
	public List<String> getAllPersonByScar_No(String Scar_NO);
	
	// 取得競拍中的會員編號
	public Set<String> getAllMebBid(String scar_no);
		
	// 取得最高價
//	public BiddingVO getOneMaxPrice(String scar_no);
	
}
