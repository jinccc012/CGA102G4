package com.rcarorder.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.rcar.model.RcarVO;

import utils.ReDay;

public interface RcarOrderDAO {

	// 查看會員訂單(遞增)
	List<RcarOrderVO> getAll(String meb_no);
	// 查看會員訂單(遞減)
	List<RcarOrderVO> getAllDesc(String meb_no);
	// 查看一筆訂單詳細資訊
	RcarOrderVO getOne(int rcaro_no);
	// 取消訂單
	void cancel(int rcaro_no);
	// 租車下訂
	int insert(RcarOrderVO rcarOrderVO);
	// 修改
	boolean update(RcarOrderVO rcarOrderVO);
	// 預覽全部訂單
	List<RcarOrderVO> getAll();
	// 可選月份
	List<RcarOrderVO> getAll(int month);
	// 複合查詢
	List<RcarOrderVO> getByCompositeQuery(Map<String,String[]> paramMap);
	// 取得指定門市當月所有訂單 用於配車表上
	public List<ReDay> getallday(List<RcarVO> rcar_no, int month);
	public List<ReDay> getallday(List<RcarVO> st_rcar_no, LocalDate date);
	// 取得非本站所有車輛 用於配車表 xxxx
	public List<RcarOrderVO> getOthercar(String store, LocalDate Date);
	// 取得 特定車輛 現在~7天 訂單
	public List<RcarOrderVO> getbetween_order(String rcar_no, LocalDate Date);
	// 用訂單狀態查詢
	List<RcarOrderVO> getByOrderStatus(int status);

}