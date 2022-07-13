package com.scar.model;

import java.util.List;
import java.util.Map;

import com.successful_bid.model.Successful_BidVO;

public interface ScarDAO {

	// 取得全部在拍賣中的車
	List<ScarVO> getAllOn();

	// 取得特定車輛資訊
	ScarVO getOne(String scar_no);

	// 結拍時將最高價格存回MySQL，並且將競標記錄存回MySQL
	public boolean update(String scar_no);

	public void insert(ScarVO scarvo);

	public void update(ScarVO scarvo);

	public ScarVO findByPrimaryKey(String scar_no);

	public List<ScarVO> getAll();

	// update status
	public void updateStatus(Integer status, String scar_no);

	public void updateStatusTransaction(Integer status, String scar_no, Successful_BidVO sbVo);

	public List<ScarVO> getAllByStatus(Integer scar_status0, Integer scar_status1);

	public List<ScarVO> getAllReadytoLunched();

	public List<ScarVO> getAll(Map<String, String[]> map);
	
	// 取得在redis中的中古車物件
	public List<ScarVO> getAllInRedis();
	
	// 取得redis中的單一中古車物件的最高價格
	public Integer getOneMaxpriceInRedis(String scar_no);
	
	// 取得單一中古車競拍紀錄最高價的人是誰
	public String getMebNo(String scar_no);
	
	// 取得redis中的單一中古車物件
	public ScarVO getOneInRedis(String scar_no);
	
	// 更新價錢
	public boolean updateOneMaxpriceInRedis(String scar_no, Integer bid_price, String meb_no);
	
	// 中古車上架時同時存入redis
	public void saveOneInRedis(ScarVO scarVOs);
}
