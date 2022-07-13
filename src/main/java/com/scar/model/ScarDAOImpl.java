package com.scar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.bidding.model.BiddingVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.successful_bid.model.Successful_BidDAO_Implement;
import com.successful_bid.model.Successful_BidVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisDataException;
import utils.JedisUtil;
import utils.MysqlJDBC;

public class ScarDAOImpl implements ScarDAO{
//	 使用DataSource
	private static DataSource ds;
	
	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}
	
	private final String GETALL = "SELECT "
			+ "SCAR_NO, "
			+ "ST_NO, SCAR_BRAND, "
			+ "SCAR_MODEL, "
			+ "SCAR_COLOR, "
			+ "SCAR_YEAR, "
			+ "SCAR_CC, "
			+ "SCAR_TRANS, "
			+ "SCAR_FUEL, "
			+ "SCAR_CARRYING, "
			+ "SCAR_CARRINGPKG, "
			+ "SCAR_STARTPRICE, "
			+ "SCAR_PRICE, "
			+ "SCAR_MAXPRICE, "
			+ "SCAR_PHOTO, "
			+ "SCAR_STARTIME, "
			+ "SCAR_ENDTIME, "
			+ "SCAR_STATUS, "
			+ "SCAR_MILES "
			+ "FROM SCAR "
			+ "WHERE SCAR_STATUS = 1 "; // 狀態1為拍賣中
	
	private final String GETONE = "SELECT "
			+ "SCAR_NO, "
			+ "ST_NO, SCAR_BRAND, "
			+ "SCAR_MODEL, "
			+ "SCAR_COLOR, "
			+ "SCAR_YEAR, "
			+ "SCAR_CC, "
			+ "SCAR_TRANS, "
			+ "SCAR_FUEL, "
			+ "SCAR_CARRYING, "
			+ "SCAR_CARRINGPKG, "
			+ "SCAR_STARTPRICE, "
			+ "SCAR_PRICE, "
			+ "SCAR_MAXPRICE, "
			+ "SCAR_PHOTO, "
			+ "SCAR_STARTIME, "
			+ "SCAR_ENDTIME, "
			+ "SCAR_STATUS, "
			+ "SCAR_MILES "
			+ "FROM SCAR "
			+ "WHERE SCAR_NO = ? "; // 依照傳過來的編號去搜尋車輛
	
	private final String UPDATEMAXPRICE = "UPDATE "
			+ "`SCAR` "
			+ "SET "
			+ "`SCAR_MAXPRICE` = ? "
			+ "WHERE (`SCAR_NO` = ? ) ";
	
	// 存入競拍紀錄
	private final String SAVEBIDRECORD = "INSERT INTO `BIDDING` "
			+ "(`SCAR_NO`, `MEB_NO`, `BID_PRICE`, `BID_TIME`) "
			+ "VALUES (?,?,?,?) ";
	
	public List<ScarVO> getAllOn() {
		List<ScarVO> list = new ArrayList<ScarVO>();

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GETALL)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ScarVO scarVO = new ScarVO();
				
				scarVO.setScar_no(rs.getString("scar_no"));
				scarVO.setSt_no(rs.getString("st_no"));
				scarVO.setScar_brand(rs.getString("scar_brand"));
				scarVO.setScar_model(rs.getString("scar_model"));
				scarVO.setScar_color(rs.getString("scar_color"));
				scarVO.setScar_year(rs.getString("scar_year"));
				scarVO.setScar_cc(rs.getString("scar_cc"));
				scarVO.setScar_trans(rs.getString("scar_trans"));
				scarVO.setScar_fuel(rs.getString("scar_fuel"));
				scarVO.setScar_carrying(rs.getInt("scar_carrying"));
				scarVO.setScar_carringpkg(rs.getString("scar_carringpkg"));
				scarVO.setScar_startprice(rs.getInt("scar_startprice"));
				scarVO.setScar_price(rs.getInt("scar_price"));
				scarVO.setScar_maxprice(rs.getInt("scar_maxprice"));
				scarVO.setScar_photo(rs.getBytes("scar_photo"));
				scarVO.setScar_startime(rs.getTimestamp("scar_startime"));
				scarVO.setScar_endtime(rs.getTimestamp("scar_endtime"));
				scarVO.setScar_status(rs.getInt("scar_status"));
				scarVO.setScar_miles(rs.getInt("scar_miles"));
				
				list.add(scarVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	public ScarVO getOne(String scar_no) {
		ScarVO scarVO = new ScarVO();
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GETONE)) {
			
			pstmt.setString(1, scar_no);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				scarVO.setScar_no(rs.getString("scar_no"));
				scarVO.setSt_no(rs.getString("st_no"));
				scarVO.setScar_brand(rs.getString("scar_brand"));
				scarVO.setScar_model(rs.getString("scar_model"));
				scarVO.setScar_color(rs.getString("scar_color"));
				scarVO.setScar_year(rs.getString("scar_year"));
				scarVO.setScar_cc(rs.getString("scar_cc"));
				scarVO.setScar_trans(rs.getString("scar_trans"));
				scarVO.setScar_fuel(rs.getString("scar_fuel"));
				scarVO.setScar_carrying(rs.getInt("scar_carrying"));
				scarVO.setScar_carringpkg(rs.getString("scar_carringpkg"));
				scarVO.setScar_startprice(rs.getInt("scar_startprice"));
				scarVO.setScar_price(rs.getInt("scar_price"));
				scarVO.setScar_maxprice(rs.getInt("scar_maxprice"));
				scarVO.setScar_photo(rs.getBytes("scar_photo"));
				scarVO.setScar_startime(rs.getTimestamp("scar_startime"));
				scarVO.setScar_endtime(rs.getTimestamp("scar_endtime"));
				scarVO.setScar_status(rs.getInt("scar_status"));
				scarVO.setScar_miles(rs.getInt("scar_miles"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return scarVO;
	}
	
	/********************
	 * Redis結拍時處理  *
	 ********************/
	@Override
	public boolean update(String scar_no) {
		JedisPool pool =JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		// 1. 取得redis中下架中古車的最高價格
		int maxprice = Integer.valueOf(jedis.hget("Scar:"+scar_no, "scar_maxprice"));
		// 2. 更新MySQL中的最高價
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(UPDATEMAXPRICE)) {
			pstmt.setInt(1, maxprice);
			pstmt.setString(2, scar_no);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		// 3. 將redis中"競標記錄"存入MySQL中
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SAVEBIDRECORD)) {
			// 先取得會員ID、時間和價錢
			List<String> list = jedis.zrevrangeByScore(scar_no, maxprice, 0);
			// 以上取得的值[{"meb_no":"xxx","bid_time":"xxx"},{"meb_no":"xxx","bid_time":"xxx"},...]
			Iterator<String> it = list.iterator();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			List<BiddingVO> list2 = new ArrayList<BiddingVO>();
			while(it.hasNext()) {
				String str = it.next(); // {"meb_no":"O109384756","bid_time":"2022-07-04 18:08:17"}
				Integer bid_price = (int)Math.round(jedis.zscore(scar_no, str)); // 右邊取出來為double，先四捨五入，再轉成int
				BiddingVO re = gson.fromJson(str, BiddingVO.class);
				re.setBid_price(bid_price);
				list2.add(re);
			}
			// 4. 取出每一個會員編號、價錢和出價時間，存入MySQL的競標紀錄
			for (BiddingVO r : list2) {
				pstmt.setString(1, scar_no);
				pstmt.setString(2, r.getMeb_no());
				pstmt.setInt(3, r.getBid_price());
				pstmt.setTimestamp(4, r.getBid_time());
				pstmt.executeUpdate();
			}
			// 5. 刪除redis中的紀錄
			jedis.del(scar_no);
			jedis.del("Scar:"+scar_no);
			System.out.println("ScarDAOImpl--刪除"+scar_no);
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			jedis.close();
		}
		return true;
	}
	
	
	/**
	 * 取得單一中古車競標紀錄最高價的人
	 * @param scar_no
	 * @return meb_no
	 */
	@Override
	public String getMebNo(String scar_no) {
		JedisPool pool =JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		List<String> list = jedis.zrevrangeByScore(scar_no, "+inf", "0", 0, 1);
		Iterator<String> it = list.iterator();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String meb_no = null;
		while(it.hasNext()) {
			BiddingVO biddingVO = gson.fromJson(it.next(),BiddingVO.class);
			meb_no = biddingVO.getMeb_no();
		}
		jedis.close();
		return meb_no;
	}
	
	private static final String INSERT_STMT = "INSERT INTO `CGA102G4`.`SCAR` (`SCAR_NO`, `ST_NO`, `SCAR_BRAND`, `SCAR_MODEL`, `SCAR_COLOR`, `SCAR_YEAR`, `SCAR_CC`, `SCAR_TRANS`, `SCAR_FUEL`, `SCAR_CARRYING`, `SCAR_CARRINGPKG`, `SCAR_PRICE`, `SCAR_STARTPRICE`, `SCAR_PHOTO`, `SCAR_STARTIME`, `SCAR_ENDTIME`, `SCAR_STATUS`, `SCAR_MILES`) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?,?, ?, ?, ?, ?, ?, ?,?,?)";
	private static final String UPDATE = "UPDATE `CGA102G4`.`SCAR` SET  `ST_NO` = ?, `SCAR_BRAND` = ?, `SCAR_MODEL` = ?, `SCAR_COLOR` = ?, `SCAR_YEAR` = ?, `SCAR_CC` = ?, `SCAR_TRANS` = ?, `SCAR_FUEL` = ?, `SCAR_CARRYING` = ?,`SCAR_CARRINGPKG` = ?, `SCAR_PRICE` = ?, `SCAR_STARTPRICE` = ?,`SCAR_MAXPRICE` = ?,`SCAR_PHOTO`=?, `SCAR_STARTIME` = ?, `SCAR_ENDTIME` = ?, `SCAR_STATUS` = ?, `SCAR_MILES` =? WHERE `SCAR_NO` = ?";
	private static final String GET_ALL_STMT = "Select `SCAR_NO`, `ST_NO`, `SCAR_BRAND`, `SCAR_MODEL`, `SCAR_COLOR`, `SCAR_YEAR`, `SCAR_CC`, `SCAR_TRANS`, `SCAR_FUEL`, `SCAR_CARRYING`, `SCAR_CARRINGPKG`, `SCAR_PRICE`,`SCAR_STARTPRICE`,`SCAR_MAXPRICE`, `SCAR_PHOTO`, `SCAR_STARTIME`, `SCAR_ENDTIME`, `SCAR_STATUS`, `SCAR_MILES` from `CGA102G4`.`SCAR` order by SCAR_STARTIME desc";
	private static final String GET_ALL_ByStatus = "Select `SCAR_NO`, `ST_NO`, `SCAR_BRAND`, `SCAR_MODEL`, `SCAR_COLOR`, `SCAR_YEAR`, `SCAR_CC`, `SCAR_TRANS`, `SCAR_FUEL`, `SCAR_CARRYING`, `SCAR_CARRINGPKG`, `SCAR_PRICE`,`SCAR_STARTPRICE`,`SCAR_MAXPRICE`, `SCAR_PHOTO`, `SCAR_STARTIME`, `SCAR_ENDTIME`, `SCAR_STATUS`, `SCAR_MILES` from `CGA102G4`.`SCAR` where `SCAR_STATUS` = ? or `SCAR_STATUS` = ? ";
	private static final String GET_ONE_STMT = "select `SCAR_NO`, `ST_NO`, `SCAR_BRAND`, `SCAR_MODEL`, `SCAR_COLOR`, `SCAR_YEAR`, `SCAR_CC`, `SCAR_TRANS`, `SCAR_FUEL`, `SCAR_CARRYING`, `SCAR_CARRINGPKG`, `SCAR_PRICE`,`SCAR_STARTPRICE`,`SCAR_MAXPRICE`, `SCAR_PHOTO`, `SCAR_STARTIME`, `SCAR_ENDTIME`, `SCAR_STATUS`, `SCAR_MILES` from `CGA102G4`.`SCAR` where `SCAR_NO`=?";
	private static final String UPDATEStatus = "UPDATE `CGA102G4`.`SCAR` SET `SCAR_STATUS` = ? WHERE (`SCAR_NO` = ?)";
	private static final String GET_ALL_ReadyToLunched = "Select `SCAR_NO`, `ST_NO`, `SCAR_BRAND`, `SCAR_MODEL`, `SCAR_COLOR`, `SCAR_YEAR`, `SCAR_CC`, `SCAR_TRANS`, `SCAR_FUEL`, `SCAR_CARRYING`, `SCAR_CARRINGPKG`, `SCAR_PRICE`,`SCAR_STARTPRICE`,`SCAR_MAXPRICE`, `SCAR_PHOTO`, `SCAR_STARTIME`, `SCAR_ENDTIME`, `SCAR_STATUS`, `SCAR_MILES` from `CGA102G4`.`SCAR` where `SCAR_STATUS`= 0 or `SCAR_STATUS` =1";

	@Override
	public void insert(ScarVO scarvo) {
	
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);) {
			pstmt.setString(1, scarvo.getScar_no());
			pstmt.setString(2, scarvo.getSt_no());
			pstmt.setString(3, scarvo.getScar_brand());
			pstmt.setString(4, scarvo.getScar_model());
			pstmt.setString(5, scarvo.getScar_color());
			pstmt.setString(6, scarvo.getScar_year());
			pstmt.setString(7, scarvo.getScar_cc());
			pstmt.setString(8, scarvo.getScar_trans());
			pstmt.setString(9, scarvo.getScar_fuel());
			pstmt.setInt(10, scarvo.getScar_carrying());
			pstmt.setString(11, scarvo.getScar_carringpkg());
			pstmt.setInt(12, scarvo.getScar_price());
			pstmt.setInt(13, scarvo.getScar_startprice());
			pstmt.setBytes(14, scarvo.getScar_photo());
			pstmt.setTimestamp(15, scarvo.getScar_startime());
			pstmt.setTimestamp(16, scarvo.getScar_endtime());
			pstmt.setInt(17, 0);
			pstmt.setInt(18, scarvo.getScar_miles());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void update(ScarVO scarvo) {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(UPDATE);) {
			pstmt.setString(1, scarvo.getSt_no());
			pstmt.setString(2, scarvo.getScar_brand());
			pstmt.setString(3, scarvo.getScar_model());
			pstmt.setString(4, scarvo.getScar_color());
			pstmt.setString(5, scarvo.getScar_year());
			pstmt.setString(6, scarvo.getScar_cc());
			pstmt.setString(7, scarvo.getScar_trans());
			pstmt.setString(8, scarvo.getScar_fuel());
			pstmt.setInt(9,scarvo.getScar_carrying());
			pstmt.setString(10, scarvo.getScar_carringpkg());
			pstmt.setInt(11, scarvo.getScar_price());
			pstmt.setInt(12, scarvo.getScar_startprice());
			pstmt.setInt(13, 0);
			pstmt.setBytes(14, scarvo.getScar_photo());
			pstmt.setTimestamp(15, scarvo.getScar_startime());
			pstmt.setTimestamp(16, scarvo.getScar_endtime());
			pstmt.setInt(17, 0);// scarvo.getScar_status() default 0
			pstmt.setInt(18, scarvo.getScar_miles());
			pstmt.setString(19, scarvo.getScar_no());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public ScarVO findByPrimaryKey(String scar_no) {
		ScarVO scarvo = null;
		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);) {
			pstmt.setString(1, scar_no);
			ResultSet rs = pstmt.executeQuery();
			scarvo = new ScarVO();
			while (rs.next()) {
				scarvo.setScar_no(rs.getString("scar_no"));
				scarvo.setSt_no(rs.getString("st_no"));
				scarvo.setScar_brand(rs.getString("scar_brand"));
				scarvo.setScar_model(rs.getString("scar_model"));
				scarvo.setScar_color(rs.getString("scar_color"));
				scarvo.setScar_year(rs.getString("scar_year"));
				scarvo.setScar_cc(rs.getString("scar_cc"));
				scarvo.setScar_trans(rs.getString("scar_trans"));
				scarvo.setScar_fuel(rs.getString("scar_fuel"));
				scarvo.setScar_carrying(rs.getInt("scar_carrying"));
				scarvo.setScar_carringpkg(rs.getString("scar_carringpkg"));
				scarvo.setScar_price(rs.getInt("scar_price"));
				scarvo.setScar_photo(rs.getBytes("scar_photo"));
				scarvo.setScar_startime(rs.getTimestamp("scar_startime"));
				scarvo.setScar_endtime(rs.getTimestamp("scar_endtime"));
				scarvo.setScar_status(rs.getInt("scar_status"));
				scarvo.setScar_miles(rs.getInt("scar_miles"));
				scarvo.setScar_startprice(rs.getInt("scar_startprice"));
				scarvo.setScar_maxprice(rs.getInt("scar_maxprice"));

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return scarvo;
	}

	@Override
	public List<ScarVO> getAll() {
		List<ScarVO> list = new ArrayList<ScarVO>();

		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				ScarVO scarvo = new ScarVO();
				scarvo.setScar_no(rs.getString("scar_no"));
				scarvo.setSt_no(rs.getString("st_no"));
				scarvo.setScar_brand(rs.getString("scar_brand"));
				scarvo.setScar_model(rs.getString("scar_model"));
				scarvo.setScar_color(rs.getString("scar_color"));
				scarvo.setScar_year(rs.getString("scar_year"));
				scarvo.setScar_cc(rs.getString("scar_cc"));
				scarvo.setScar_trans(rs.getString("scar_trans"));
				scarvo.setScar_fuel(rs.getString("scar_fuel"));
				scarvo.setScar_carrying(rs.getInt("scar_carrying"));
				scarvo.setScar_carringpkg(rs.getString("scar_carringpkg"));
				scarvo.setScar_price(rs.getInt("scar_price"));
				scarvo.setScar_photo(rs.getBytes("scar_photo"));
				scarvo.setScar_startime(rs.getTimestamp("scar_startime"));
				scarvo.setScar_endtime(rs.getTimestamp("scar_endtime"));
				scarvo.setScar_status(rs.getInt("scar_status"));
				scarvo.setScar_miles(rs.getInt("scar_miles"));
				scarvo.setScar_startprice(rs.getInt("scar_startprice"));
				scarvo.setScar_maxprice(rs.getInt("scar_maxprice"));
				list.add(scarvo);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;
	}

	@Override
	public void updateStatus(Integer status, String scar_no) {
	
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(UPDATEStatus);) {
			pstmt.setInt(1, status);
			pstmt.setString(2, scar_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<ScarVO> getAllByStatus(Integer scar_status0, Integer scar_status1) {
		List<ScarVO> listByStatus = new ArrayList<ScarVO>();
		

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_ByStatus);) {
			pstmt.setInt(1, scar_status0);
			pstmt.setInt(2, scar_status1);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ScarVO scarvo = new ScarVO();
				scarvo.setScar_no(rs.getString("scar_no"));
				scarvo.setSt_no(rs.getString("st_no"));
				scarvo.setScar_brand(rs.getString("scar_brand"));
				scarvo.setScar_model(rs.getString("scar_model"));
				scarvo.setScar_color(rs.getString("scar_color"));
				scarvo.setScar_year(rs.getString("scar_year"));
				scarvo.setScar_cc(rs.getString("scar_cc"));
				scarvo.setScar_trans(rs.getString("scar_trans"));
				scarvo.setScar_fuel(rs.getString("scar_fuel"));
				scarvo.setScar_carrying(rs.getInt("scar_carrying"));
				scarvo.setScar_carringpkg(rs.getString("scar_carringpkg"));
				scarvo.setScar_price(rs.getInt("scar_price"));
				scarvo.setScar_photo(rs.getBytes("scar_photo"));
				scarvo.setScar_startime(rs.getTimestamp("scar_startime"));
				scarvo.setScar_endtime(rs.getTimestamp("scar_endtime"));
				scarvo.setScar_status(rs.getInt("scar_status"));
				scarvo.setScar_miles(rs.getInt("scar_miles"));
				scarvo.setScar_startprice(rs.getInt("scar_startprice"));
				scarvo.setScar_maxprice(rs.getInt("scar_maxprice"));
				listByStatus.add(scarvo);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return listByStatus;

	}

	@Override
	public List<ScarVO> getAll(Map<String, String[]> map) {
		List<ScarVO> list = new ArrayList<ScarVO>();
		ScarVO scarvo = null;
	
		String finalSQL = "select * from CGA102G4.SCAR " + jdbcUtilScar.get_WhereCondition(map);
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(finalSQL);) {
			// pstmt.setInt(1, scar_status);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				scarvo = new ScarVO();
				scarvo.setScar_no(rs.getString("scar_no"));
				scarvo.setSt_no(rs.getString("st_no"));
				scarvo.setScar_brand(rs.getString("scar_brand"));
				scarvo.setScar_model(rs.getString("scar_model"));
				scarvo.setScar_color(rs.getString("scar_color"));
				scarvo.setScar_year(rs.getString("scar_year"));
				scarvo.setScar_cc(rs.getString("scar_cc"));
				scarvo.setScar_trans(rs.getString("scar_trans"));
				scarvo.setScar_fuel(rs.getString("scar_fuel"));
				scarvo.setScar_carrying(rs.getInt("scar_carrying"));
				scarvo.setScar_carringpkg(rs.getString("scar_carringpkg"));
				scarvo.setScar_price(rs.getInt("scar_price"));
				scarvo.setScar_photo(rs.getBytes("scar_photo"));
				scarvo.setScar_startime(rs.getTimestamp("scar_startime"));
				scarvo.setScar_endtime(rs.getTimestamp("scar_endtime"));
				scarvo.setScar_status(rs.getInt("scar_status"));
				scarvo.setScar_miles(rs.getInt("scar_miles"));
				scarvo.setScar_startprice(rs.getInt("scar_startprice"));
				scarvo.setScar_maxprice(rs.getInt("scar_maxprice"));
				list.add(scarvo);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;
	}

	@Override
	public List<ScarVO> getAllReadytoLunched() {
		List<ScarVO> list = new ArrayList<ScarVO>();
		ScarVO scarvo = null;
		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_ReadyToLunched);) {
			// pstmt.setInt(1, scar_status);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				scarvo = new ScarVO();
				scarvo.setScar_no(rs.getString("scar_no"));
				scarvo.setSt_no(rs.getString("st_no"));
				scarvo.setScar_brand(rs.getString("scar_brand"));
				scarvo.setScar_model(rs.getString("scar_model"));
				scarvo.setScar_color(rs.getString("scar_color"));
				scarvo.setScar_year(rs.getString("scar_year"));
				scarvo.setScar_cc(rs.getString("scar_cc"));
				scarvo.setScar_trans(rs.getString("scar_trans"));
				scarvo.setScar_fuel(rs.getString("scar_fuel"));
				scarvo.setScar_carrying(rs.getInt("scar_carrying"));
				scarvo.setScar_carringpkg(rs.getString("scar_carringpkg"));
				scarvo.setScar_price(rs.getInt("scar_price"));
				scarvo.setScar_photo(rs.getBytes("scar_photo"));
				scarvo.setScar_startime(rs.getTimestamp("scar_startime"));
				scarvo.setScar_endtime(rs.getTimestamp("scar_endtime"));
				scarvo.setScar_status(rs.getInt("scar_status"));
				scarvo.setScar_miles(rs.getInt("scar_miles"));
				scarvo.setScar_startprice(rs.getInt("scar_startprice"));
				scarvo.setScar_maxprice(rs.getInt("scar_maxprice"));
				list.add(scarvo);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;
	}

	@Override
	public void updateStatusTransaction(Integer status, String scar_no, Successful_BidVO sbVo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(UPDATEStatus);
			System.out.print("開始交易");
			con.setAutoCommit(false);
			pstmt.setInt(1, status);
			pstmt.setString(2, scar_no);
			pstmt.executeUpdate();
			System.out.print("開始新增競標");
			// 在同時新增競標
			Successful_BidDAO_Implement sdao = new Successful_BidDAO_Implement();
			sdao.insertTransaction(sbVo, con);

			con.commit();
			con.setAutoCommit(true);
			System.out.println("同時更改中古車編號，及新增Sbid成功");
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					con.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	/**
	 * 取得所有在redis中的中古車物件
	 * @return List<ScarVO>
	 */
	@Override
	public List<ScarVO> getAllInRedis(){
		JedisPool pool = JedisUtil.getJedisPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			// 讀取資料存入scarVO集合
			List<ScarVO> list = new ArrayList<ScarVO>();
			// 取得所有中古車物件的庫名稱
			Set<String> scarKeys = jedis.keys("Scar*");
			for (String scarKey : scarKeys) {
				ScarVO scarVO = new ScarVO();
				scarVO.setScar_no(jedis.hget(scarKey, "scar_no"));
				scarVO.setSt_no(jedis.hget(scarKey, "st_no"));
				scarVO.setScar_brand(jedis.hget(scarKey, "scar_brand"));
				scarVO.setScar_model(jedis.hget(scarKey, "scar_model"));
				scarVO.setScar_color(jedis.hget(scarKey, "scar_color"));
				scarVO.setScar_year(jedis.hget(scarKey, "scar_year"));
				scarVO.setScar_cc(jedis.hget(scarKey, "scar_cc"));
				scarVO.setScar_trans(jedis.hget(scarKey, "scar_trans"));
				scarVO.setScar_fuel(jedis.hget(scarKey, "scar_fuel"));
				scarVO.setScar_carrying(Integer.valueOf(jedis.hget(scarKey, "scar_carrying")));
				scarVO.setScar_carringpkg(jedis.hget(scarKey, "scar_carringpkg"));
				scarVO.setScar_startprice(Integer.valueOf(jedis.hget(scarKey, "scar_startprice")));
				scarVO.setScar_price(Integer.valueOf(jedis.hget(scarKey, "scar_price")));
				scarVO.setScar_maxprice(Integer.valueOf(jedis.hget(scarKey, "scar_maxprice")));
				scarVO.setScar_photo(Base64.getDecoder().decode(jedis.hget(scarKey, "scar_photo")));
				scarVO.setScar_startime(Timestamp.valueOf(jedis.hget(scarKey, "scar_startime")));
				scarVO.setScar_endtime(Timestamp.valueOf(jedis.hget(scarKey, "scar_endtime")));
				scarVO.setScar_status(Integer.valueOf(jedis.hget(scarKey, "scar_status")));
				scarVO.setScar_miles(Integer.valueOf(jedis.hget(scarKey, "scar_miles")));
				list.add(scarVO);
			}
			return list;
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * 取得最高價回傳
	 * @return maxprice
	 */
	@Override
	public Integer getOneMaxpriceInRedis(String scar_no) {
		JedisPool pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		System.out.println(scar_no);
		System.out.println(jedis.hget("Scar:"+scar_no, "scar_maxprice"));
		int scar_maxprice = Integer.valueOf(jedis.hget("Scar:"+scar_no, "scar_maxprice"));
		if (scar_maxprice == 0) {
			int i = Integer.valueOf(jedis.hget("Scar:"+scar_no, "scar_startprice"));
			jedis.close();
			return i;
		}else {
			jedis.close();
			return scar_maxprice;
		}
	}
	
	
	/**
	 * 取得單一中古車物件
	 * @return ScarVO
	 */
	public ScarVO getOneInRedis(String scar_no) {
		Jedis jedis = JedisUtil.getJedisPool().getResource();
		ScarVO scarVO = new ScarVO();
		scarVO.setScar_no(jedis.hget("Scar:"+scar_no, "scar_no"));
		scarVO.setSt_no(jedis.hget("Scar:"+scar_no, "st_no"));
		scarVO.setScar_brand(jedis.hget("Scar:"+scar_no, "scar_brand"));
		scarVO.setScar_model(jedis.hget("Scar:"+scar_no, "scar_model"));
		scarVO.setScar_color(jedis.hget("Scar:"+scar_no, "scar_color"));
		scarVO.setScar_year(jedis.hget("Scar:"+scar_no, "scar_year"));
		scarVO.setScar_cc(jedis.hget("Scar:"+scar_no, "scar_cc"));
		scarVO.setScar_trans(jedis.hget("Scar:"+scar_no, "scar_trans"));
		scarVO.setScar_fuel(jedis.hget("Scar:"+scar_no, "scar_fuel"));
		scarVO.setScar_carrying(Integer.valueOf(jedis.hget("Scar:"+scar_no, "scar_carrying")));
		scarVO.setScar_carringpkg(jedis.hget("Scar:"+scar_no, "scar_carringpkg"));
		scarVO.setScar_startprice(Integer.valueOf(jedis.hget("Scar:"+scar_no, "scar_startprice")));
		scarVO.setScar_price(Integer.valueOf(jedis.hget("Scar:"+scar_no, "scar_price")));
		scarVO.setScar_maxprice(Integer.valueOf(jedis.hget("Scar:"+scar_no, "scar_maxprice")));
		scarVO.setScar_photo(Base64.getDecoder().decode(jedis.hget("Scar:"+scar_no, "scar_photo")));
		scarVO.setScar_startime(Timestamp.valueOf(jedis.hget("Scar:"+scar_no, "scar_startime")));
		scarVO.setScar_endtime(Timestamp.valueOf(jedis.hget("Scar:"+scar_no, "scar_endtime")));
		scarVO.setScar_status(Integer.valueOf(jedis.hget("Scar:"+scar_no, "scar_status")));
		scarVO.setScar_miles(Integer.valueOf(jedis.hget("Scar:"+scar_no, "scar_miles")));
		jedis.close();
		return scarVO;
	}
	

	/**
	 * 上架時存將中古車物件入redis
	 * Hash型別
	 */
	@Override
	public void saveOneInRedis(ScarVO scarVO) {
		JedisPool pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		// 將物件存入redis
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_no", scarVO.getScar_no());
			jedis.hset("Scar:"+scarVO.getScar_no(), "st_no", scarVO.getSt_no());
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_brand", scarVO.getScar_brand());
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_model", scarVO.getScar_model());
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_color", scarVO.getScar_color());
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_year", scarVO.getScar_year());
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_cc", scarVO.getScar_cc());
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_trans", scarVO.getScar_trans());
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_fuel", scarVO.getScar_fuel());
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_carrying", String.valueOf(scarVO.getScar_carrying()));
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_carringpkg", scarVO.getScar_carringpkg());
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_startprice", String.valueOf(scarVO.getScar_startprice()));
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_price", String.valueOf(scarVO.getScar_price()));
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_maxprice", String.valueOf(scarVO.getScar_maxprice()));
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_photo", Base64.getEncoder().encodeToString(scarVO.getScar_photo()));
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_startime", String.valueOf(scarVO.getScar_startime()));
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_endtime", String.valueOf(scarVO.getScar_endtime()));
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_status", String.valueOf(scarVO.getScar_status()));
			jedis.hset("Scar:"+scarVO.getScar_no(), "scar_miles", String.valueOf(scarVO.getScar_miles()));
			jedis.close();
	}


	@Override
	public boolean updateOneMaxpriceInRedis(String scar_no, Integer bid_price, String meb_no) {
		JedisPool pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		Transaction transaction = jedis.multi();
		try {
			// 1. 修改價格
			transaction.hset("Scar:"+scar_no, "scar_maxprice", String.valueOf(bid_price));
			// 2. 儲存競標紀錄
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String bid_time = sdf.format(Calendar.getInstance().getTime());
			String str = new StringBuilder().append("{\"meb_no\":\"").append(meb_no).append("\",\"bid_time\":\"").append(bid_time).append("\"}").toString();
//			transaction.zadd(scar_no,bid_price,"{"+"\""+"meb_no"+"\":"+"\""+meb_no+"\""+",\""+"bid_time"+"\":"+"\""+bid_time+"\""+"}");
			transaction.zadd(scar_no,bid_price,str);
			transaction.exec();
			return true;
		}catch(JedisDataException e) {
			transaction.close();
			e.printStackTrace();
			return false;
		}finally {
			jedis.close();
		}
		
	}



}
