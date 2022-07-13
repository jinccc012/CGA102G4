package com.successful_bid.model;

import java.util.List;

public class Successful_BidService {

	private Successful_BidDAO_Implement dao = new Successful_BidDAO_Implement();


	public void insert(Successful_BidVO vo) {
		dao.insert(vo);
	}

	public List<Successful_BidVO> getAll() {
		return dao.getAll();
	}

	public Boolean updateBySbpayState(Integer sb_no, String meb_no, Integer payState) {
		dao.updateBySb_non_paying(sb_no, meb_no, payState);
		return true;
	}


	public Successful_BidVO getOneSbNo(Integer sb_no) {

		return dao.getOneSbNo(sb_no);

	}

}
