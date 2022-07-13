package com.bidding.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BiddingService {
	BiddingDAO dao = null;
	
	public BiddingService() {
		dao = new BiddingDAOImpl();
	}
	
//	public boolean insert(String scar_no, String meb_no, Integer bid_price) {
//		return dao.insert(scar_no, meb_no, bid_price);
//	}
	
	public boolean insert(String scar_no, String meb_no, Integer bid_price, Connection conn) {
		return dao.insert(scar_no, meb_no, bid_price, conn);
	}
	
	public List<BiddingVO> getAllBid() {
		return dao.getAll();
	}

	public List<BiddingVO> getOneBid(String scar_no) {
		return dao.getBidByScar_No(scar_no);

	}

	public List<BiddingVO> getOneBidGerterPrice(String scar_no, Integer scar_price) {
		return dao.getBidByScar_NoGreaterPrice(scar_no, scar_price);
	}
	public BiddingVO getOneBidHighest(String scar_no) {
		return dao.getBidByScar_Nohighest(scar_no);
	}
	
	public List<BiddingVO> getAll(Map<String, String[]> map){
		return dao.getAllQuery(map);	
	}
	
	public List<String> getAllpersonOneBid(String scar_no){
		return dao.getAllPersonByScar_No(scar_no);
	}
	
	public Set<String> getAllMebBid(String scar_no){
		return dao.getAllMebBid(scar_no);
	}

	public List<BiddingVO> getBidByMEB_NO(String MEB_NO){
		return dao.getBidByMEB_NO(MEB_NO);
	}
	
	// 查特定車輛的競拍紀錄最高價
//	public BiddingVO getOneScarMaxPrice(String scar_no){
//		return dao.getOneMaxPrice(scar_no);
//	}
}
