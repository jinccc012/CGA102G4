package com.scar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bidding.model.BiddingService;
import com.bidding.model.BiddingVO;
import com.successful_bid.model.Successful_BidVO;
import com.websocket.controller.WebSocket;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import utils.JedisUtil;
import utils.MailService;

public class ScarService {
	ScarDAO dao = null;
	public ScarService() {
		dao = new ScarDAOImpl();
	}
	
	public List<ScarVO> getScarOnAuction(){
		return dao.getAllOn();
	}
	
	public ScarVO getScarAuctiondetail(String scar_no) {
		return dao.getOne(scar_no);
	}
	
	public void insert(ScarVO scarVO) {
		dao.insert(scarVO);
	}

	public ScarVO getScar(String scar_no) {
		return dao.findByPrimaryKey(scar_no);
	}

	public void update(ScarVO scarvo) {
		dao.update(scarvo);
	}

	public List<ScarVO> getAll() {
		return dao.getAll();
	}

	public boolean updateStatus(Integer status, String scar_no) {
		dao.updateStatus(status, scar_no);
		return true;
	}
	
	public List<ScarVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
	
	public List<ScarVO> readyToLunched(){
		return dao.getAllReadytoLunched();
	}
	
	public Map<String, String> getAllToMap() {
		Map<String, String> map = new HashMap<String, String>();
		for (ScarVO s : getAll()) {
			StringBuilder value = new StringBuilder();
			value.append(s.getScar_startime());
			value.append("&");
			value.append(s.getScar_endtime());
			map.put(s.getScar_no(), value.toString());
		}
		return map;
	}
	
	
	
	/*********************************************
	 * 查詢MySQL中狀態0和1的中古車加入Redis  *
	 **********************************************/
	public void saveScarInRedis() {
		System.out.println("ScarService--"+"saveScarInRedis執行");
		/* -- 上架時同時存入redis-- start-- */
		List<ScarVO> scarVOs = dao.getAllByStatus(0,1);
		
		JedisPool pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		for (ScarVO scarVO : scarVOs) {
			// 查到的中古車比對redis，沒有的再加入&0的更新為1
			if(jedis.hgetAll("Scar:"+scarVO.getScar_no()).isEmpty() || "0".equals(jedis.hget("Scar:"+scarVO.getScar_no(), "scar_status"))) {			
				dao.saveOneInRedis(scarVO);
			}
		}
		jedis.close();

	}
	
	
	/********************
	 * 準備上架              *
	 * @param Scar_no  *
	 ********************/
	public void launched(String Scar_no) {
		System.out.println("ScarService--96--"+"launched執行");
		
		ScarVO scarvo = getScar(Scar_no);
		if (scarvo.getScar_status() == 0) {
			scarvo.setScar_status(1);
			updateStatus(1, Scar_no);
			
			System.out.println(Scar_no + "已上架成功!，車輛狀態為:" + scarvo.getScar_status());

			// push
			WebSocket websocket = new WebSocket();
			ArrayList<String> groups = new ArrayList<String>();
			groups.add("members");
			groups.add("visitors");
			String message = "中古車拍賣:"+scarvo.getScar_model()+"上架羅!快來一起參與競拍";
			websocket.sentMessageToGroups(groups, message);
		}
	}

	
	
	/******************************
	 * 取得在redis中的中古車物件 *
	 * @return List<ScarVO>         *
	 *******************************/
	public List<ScarVO> getScarOnAuctionInRedis() {
		return dao.getAllInRedis();
	}
	
	/***********************
	 * 取得目前最高價的人 *
	 * @return meb_no       *
	 ***********************/
	public String getMebNoInBidRecord(String scar_no) {
		return dao.getMebNo(scar_no);
	}
	
	/******************************
	 * 取得單一中古車目前最高價 *
	 * @param scar_no                  *
	 * @return maxprice               *
	 ******************************/
	public Integer getOneScarOnAuctionMaxpriceInRedis(String scar_no) {
		return dao.getOneMaxpriceInRedis(scar_no);
	}
	
	
	/***********************
	 * 取得一台中古車物件 *
	 * @param scar_no       *
	 * @return ScarVO        *
	 ***********************/
	public ScarVO getOneScarOnAuctionInRedis(String scar_no) {
		return dao.getOneInRedis(scar_no);
	}
	
	
	/********************************
	 * 更新價格，並且儲存會員出價 *
	 * @param scar_no                      *
	 * @param bid_price                   *
	 * @param meb_no                     *
	 * @return boolean                     *
	 *********************************/
	public boolean updateOneScarOnAuctionMaxpriceInRedis(String scar_no, Integer bid_price, String meb_no) {
		return dao.updateOneMaxpriceInRedis(scar_no,bid_price,meb_no);
	}
	

	
	/********************
	 * 下架                     *
	 * @param Scar_no *
	 ********************/
	public void discontinued(String scar_no) {
		// 1.修改MySQL中古車的maxprice資料、2.將競拍紀錄存入MySQL
		dao.update(scar_no);
		
		ScarVO scarvo = getScar(scar_no);
		if (scarvo.getScar_status() == 1) {
			System.out.println(scarvo.getScar_no() + "準備下架!，目前車輛狀態為:" + scarvo.getScar_status());
			BiddingService bidService = new BiddingService();
			JedisPool pool =JedisUtil.getJedisPool();
			Jedis jedis = pool.getResource();
			// 查詢竟標紀錄
			// 先查最高價是否大於底價 
			Integer max_price = scarvo.getScar_maxprice();
			if (max_price >= scarvo.getScar_price()) {
				BiddingVO HighestVo = bidService.getOneBidHighest(scar_no);
				// 形成訂單
				Successful_BidVO sbVo = new Successful_BidVO();
				sbVo.setSb_no(HighestVo.getBid_no());
				sbVo.setScar_no(scar_no);
				sbVo.setMeb_no(HighestVo.getMeb_no());
				sbVo.setSb_price(HighestVo.getBid_price());
				sbVo.setSb_win_time(scarvo.getScar_endtime());// 結標時間
				sbVo.setSb_non_paying(0);
				sbVo.setSb_rank(1);
				// System.out.println("中古車改為2 及新增得標訂單");
				dao.updateStatusTransaction(2, scarvo.getScar_no(), sbVo);
				// sendEmail
				MailService mailservice = new MailService(); 
				// HighestVo.getMemberVO().getMeb_mail()取得會員email
				mailservice.sendMail(HighestVo.getMemberVO().getMeb_mail(), "FamilyRent得標通知", 
						HighestVo.getMemberVO().getMeb_name()+"先生/小姐:您好 ，恭喜你得標，請於三天內完成匯款，並通知業務匯款已完成。",
						HighestVo.getMemberVO().getMeb_name());
				System.out.println("已發送email通知");
				// 加入Redis 設置三天後過期時間
				String key = "sborder:"+HighestVo.getBid_no();
				//三天後 //Long expireThreday = (long) (60*60*24*30);
				Long expireThreday = (long)(3 * 60);
				jedis.setex(key,expireThreday , "expire");
				System.out.println();//已加入訂單
				System.out.println("已送入Redis");
			} else {
				// 如果沒有則流標
				updateStatus(3, scarvo.getScar_no());
			}
			jedis.close();
		}
	}
	
}
