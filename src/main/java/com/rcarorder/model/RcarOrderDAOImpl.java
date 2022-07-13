package com.rcarorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.rcar.model.RcarVO;

import utils.MysqlJDBC;
import utils.ReDay;

public class RcarOrderDAOImpl implements RcarOrderDAO {
	//	 使用DataSource
	private static DataSource ds = null;

	private final String INSERT = "INSERT INTO `CGA102G4`.`RCAR_ORDER` (`MEB_NO`, `MODEL_NO`, `RCARO_PPICKTIME`, `RCARO_PPRETTIME`, `RCARO_PICKUPLOC`, `RCARO_RETURNLOC`, `RCARO_PAY`, `CONSUME_POINT`, `EVENT_NO`, `RCARO_NOTE`, `LESSEE_NAME`, `LEVEL_NO`, `EARN_POINT`) "
			+ " VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? , ?, ?);";

	private static final String CANCEL = "UPDATE " + "RCAR_ORDER " + "SET RCARO_STATUS = 4 " + "WHERE RCARO_NO = ?";

	static {
		ds = MysqlJDBC.getInstance().getDataSource();
	}
	private final String CAR_ORDER = "SELECT " //取得門市所有車輛訂單 用於配車表上
			+ "    EXTRACT(DAY FROM RCARO_PPICKTIME) AS start, "
			+ "    EXTRACT(DAY FROM RCARO_PPRETTIME) As end, "
			+ "    (MONTH(RCARO_PPICKTIME) + MONTH(RCARO_PPRETTIME)) as total, " // 開始月份 + 結束月份 來判斷 開始或結束日期不再 你要查詢的月份
			+ "    RCAR_NO,"
			+ "    RCARO_PICKUPLOC, "
			+ "    RCARO_RETURNLOC,"
			+ "    RCARO_RETURNLOC_ACTUAL,"
			+ "    EXTRACT(DAY FROM RCARO_RRETTIME) As rrend "
			+ "FROM "
			+ "    RCAR_ORDER "
			+ "WHERE "
			+ "    RCAR_NO like ? and (MONTH(RCARO_PPICKTIME) + MONTH(RCARO_PPRETTIME)) in (? , ? , ? , ? ) AND YEAR(RCARO_PPRETTIME) = ? "
			+ "    AND RCARO_STATUS != 4 "
			+ "ORDER BY start;";
	// 查看會員訂單(遞增)
	private static final String GETALL = "SELECT "
			+ "RCARO_NO, "
			+ "MEB_NO, "
			+ "MODEL_NO, "
			+ "RCAR_NO, "
			+ "RCARO_PPICKTIME, "
			+ "RCARO_PPRETTIME, "
			+ "RCARO_RPICKTIME, "
			+ "RCARO_RRETTIME, "
			+ "RCARO_PICKUPLOC, "
			+ "RCARO_RETURNLOC, "
			+ "RCARO_RETURNLOC_ACTUAL, "
			+ "RCARO_TIME, "
			+ "RCARO_PAY, "
			+ "RCARO_EXTRA_PAY, "
			+ "RCARO_EXTRA_PAY_STATUS, "
			+ "CONSUME_POINT,EARN_POINT, "
			+ "EVENT_NO, RCARO_STATUS, "
			+ "RCARO_NOTE, "
			+ "LESSEE_NAME "
			+     "FROM RCAR_ORDER "
			+     "WHERE MEB_NO = ? ";
	
	// 查看會員訂單(遞減)
	private static final String GETALLDESC = "SELECT "
			+ "RCARO_NO, "
			+ "MEB_NO, "
			+ "MODEL_NO, "
			+ "RCAR_NO, "
			+ "RCARO_PPICKTIME, "
			+ "RCARO_PPRETTIME, "
			+ "RCARO_RPICKTIME, "
			+ "RCARO_RRETTIME, "
			+ "RCARO_PICKUPLOC, "
			+ "RCARO_RETURNLOC, "
			+ "RCARO_RETURNLOC_ACTUAL, "
			+ "RCARO_TIME, "
			+ "RCARO_PAY, "
			+ "RCARO_EXTRA_PAY, "
			+ "RCARO_EXTRA_PAY_STATUS, "
			+ "CONSUME_POINT,EARN_POINT, "
			+ "EVENT_NO, RCARO_STATUS, "
			+ "RCARO_NOTE, "
			+ "LESSEE_NAME "
			+ "	FROM RCAR_ORDER "
			+ "	WHERE MEB_NO = ? "
			+ "    ORDER BY RCARO_NO DESC ";

	// 查看一筆訂單詳細資訊
	private static final String GETONE = "SELECT "
			+ "RCARO_NO, "
			+ "MEB_NO, "
			+ "LEVEL_NO, "
			+ "MODEL_NO, "
			+ "RCAR_NO, "
			+ "RCARO_PPICKTIME, "
			+ "RCARO_PPRETTIME, "
			+ "RCARO_RPICKTIME, "
			+ "RCARO_RRETTIME, "
			+ "RCARO_PICKUPLOC, "
			+ "RCARO_RETURNLOC, "
			+ "RCARO_RETURNLOC_ACTUAL, "
			+ "RCARO_TIME, "
			+ "RCARO_PAY, "
			+ "RCARO_EXTRA_PAY, "
			+ "RCARO_EXTRA_PAY_STATUS, "
			+ "CONSUME_POINT,EARN_POINT, "
			+ "EVENT_NO, RCARO_STATUS, "
			+ "RCARO_NOTE, LESSEE_NAME "
			+     "FROM RCAR_ORDER "
			+     "WHERE RCARO_NO = ? ";
			
	private final String UPDATE = "UPDATE `CGA102G4`.`RCAR_ORDER` " + "SET `MODEL_NO` = ?, `RCAR_NO` = ?, "
			+ "`RCARO_PPICKTIME` = ?, `RCARO_PPRETTIME` = ?, " + " `RCARO_RPICKTIME` = ?, `RCARO_RRETTIME` = ?, "
			+ " `RCARO_PICKUPLOC` = ?, `RCARO_RETURNLOC` = ?, `RCARO_RETURNLOC_ACTUAL` = ?, "
			+ " `RCARO_PAY` = ?, `RCARO_EXTRA_PAY` = ?, `RCARO_EXTRA_PAY_STATUS` = ?, "
			+ " `CONSUME_POINT` = ?, `EARN_POINT` = ?, `EVENT_NO` = ?, `RCARO_STATUS` = ?, "
			+ " `RCARO_NOTE` = ?, `LESSEE_NAME` = ?, `MEB_NO` = ?, `LEVEL_NO` = ?" + " WHERE (`RCARO_NO` = ?); ";
	


private final String OTHER_CAR_ORDER = "SELECT * "
			+ "FROM "
			+ "    RCAR_ORDER "
			+ "WHERE "
			+ "    (RCARO_RETURNLOC = ? "
			+ "     OR RCARO_PICKUPLOC = ? )"
			+ "        AND (RCARO_PPRETTIME BETWEEN ? AND ? "
			+ "        OR RCARO_PPICKTIME BETWEEN ? AND ? ) "
			+ "        AND RCARO_STATUS != 4 ;";
			private final String GET_CAR_BETWEEN_ORDER = "SELECT  "
			+ "  * "
			+ "FROM "
			+ "    RCAR_ORDER "
			+ "WHERE "
			+ "    RCAR_NO = ? "
			+ "        AND (RCARO_PPRETTIME BETWEEN ? AND ? "
			+ "        OR RCARO_PPICKTIME BETWEEN ? AND ? ) "
			+ "        AND RCARO_STATUS != 4;";
	
	private final String All = "SELECT * FROM RCAR_ORDER";
	
	private final String MONTHALL = "SELECT " //取得特定月份所有訂單
			+ "    RCARO_NO,"
			+ "    MEB_NO,"
			+ "    LEVEL_NO,"
			+ "    MODEL_NO,"
			+ "    RCAR_NO,"
			+ "    RCARO_PPICKTIME,"
			+ "    RCARO_PPRETTIME,"
			+ "    RCARO_RPICKTIME,"
			+ "    RCARO_RRETTIME,"
			+ "    RCARO_PICKUPLOC,"
			+ "    RCARO_RETURNLOC,"
			+ "    RCARO_RETURNLOC_ACTUAL,"
			+ "    RCARO_TIME,"
			+ "    RCARO_PAY,"
			+ "    RCARO_EXTRA_PAY,"
			+ "    RCARO_EXTRA_PAY_STATUS,"
			+ "    CONSUME_POINT,"
			+ "    EARN_POINT,"
			+ "    EVENT_NO,"
			+ "    RCARO_STATUS,"
			+ "    RCARO_NOTE,"
			+ "    LESSEE_NAME "
			+ "FROM "
			+ "    CGA102G4.RCAR_ORDER "
			+ "WHERE "
			+ "    (MONTH(RCARO_PPICKTIME) = ? "
			+ "OR  MONTH(RCARO_PPRETTIME) = ?) "
			+ "AND RCARO_STATUS != 4 ";
	
	private final String GET_BY_STATUS = "SELECT * FROM RCAR_ORDER WHERE RCARO_STATUS = ?";

			@Override
	public List<RcarOrderVO> getAll() { //取得所有訂單
		List<RcarOrderVO> list = new ArrayList<>();
		try (Connection ct = ds.getConnection();
			PreparedStatement ps = ct.prepareStatement(All);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RcarOrderVO orderVO = new RcarOrderVO();
				orderVO.setRcaro_no(rs.getInt("RCARO_NO"));
				orderVO.setMeb_no(rs.getString("MEB_NO"));
				orderVO.setLevel_no(rs.getString("LEVEL_NO"));
				orderVO.setModel_no(rs.getString("MODEL_NO"));
				orderVO.setRcar_no(rs.getString("RCAR_NO"));
				orderVO.setRcaro_ppicktime(rs.getTimestamp("RCARO_PPICKTIME"));
				orderVO.setRcaro_pprettime(rs.getTimestamp("RCARO_PPRETTIME"));
				orderVO.setRcaro_rpicktime(rs.getTimestamp("RCARO_RPICKTIME"));
				orderVO.setRcaro_rrettime(rs.getTimestamp("RCARO_RRETTIME"));
				orderVO.setRcaro_pickuploc(rs.getString("RCARO_PICKUPLOC"));
				orderVO.setRcaro_returnloc(rs.getString("RCARO_RETURNLOC"));
				orderVO.setRcaro_returnloc_actual(rs.getString("RCARO_RETURNLOC_ACTUAL"));
				orderVO.setRcaro_time(rs.getTimestamp("RCARO_TIME"));
				orderVO.setRcaro_pay(rs.getInt("RCARO_PAY"));
				orderVO.setRcaro_extra_pay(rs.getInt("RCARO_EXTRA_PAY"));
				orderVO.setRcaro_extra_pay_status(rs.getInt("RCARO_EXTRA_PAY_STATUS"));
				orderVO.setConsume_point(rs.getInt("CONSUME_POINT"));
				orderVO.setEarn_point(rs.getInt("EARN_POINT"));
				orderVO.setEvent_no(rs.getInt("EVENT_NO"));
				orderVO.setRcaro_status(rs.getInt("RCARO_STATUS"));
				orderVO.setRcaro_note(rs.getString("RCARO_NOTE"));
				orderVO.setLessee_name(rs.getString("LESSEE_NAME"));
				list.add(orderVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	// 查詢全部訂單(遞增)
	@Override
	public List<RcarOrderVO> getAll(String meb_no) {

		List<RcarOrderVO> list = new ArrayList<>();
		RcarOrderVO rcar_OrderVO = null;

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GETALL);){
			
			pstmt.setString(1, meb_no);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				rcar_OrderVO = new RcarOrderVO();
				
				rcar_OrderVO.setRcaro_no(rs.getInt("rcaro_no"));
				rcar_OrderVO.setMeb_no(rs.getString("meb_no"));
				rcar_OrderVO.setModel_no(rs.getString("model_no"));
				rcar_OrderVO.setRcar_no(rs.getString("rcar_no"));
				rcar_OrderVO.setRcaro_ppicktime(rs.getTimestamp("rcaro_ppicktime"));
				rcar_OrderVO.setRcaro_pprettime(rs.getTimestamp("rcaro_pprettime"));
				rcar_OrderVO.setRcaro_rpicktime(rs.getTimestamp("rcaro_rpicktime"));
				rcar_OrderVO.setRcaro_rrettime(rs.getTimestamp("rcaro_rrettime"));
				rcar_OrderVO.setRcaro_pickuploc(rs.getString("rcaro_pickuploc"));
				rcar_OrderVO.setRcaro_returnloc(rs.getString("rcaro_returnloc"));
				rcar_OrderVO.setRcaro_returnloc_actual(rs.getString("rcaro_returnloc_actual"));
				rcar_OrderVO.setRcaro_time(rs.getTimestamp("rcaro_time"));
				rcar_OrderVO.setRcaro_pay(rs.getInt("rcaro_pay"));
				rcar_OrderVO.setRcaro_extra_pay(rs.getInt("rcaro_extra_pay"));
				rcar_OrderVO.setRcaro_extra_pay_status(rs.getInt("rcaro_extra_pay_status"));
				rcar_OrderVO.setConsume_point(rs.getInt("consume_point"));
				rcar_OrderVO.setEarn_point(rs.getInt("earn_point"));
				rcar_OrderVO.setEvent_no(rs.getInt("event_no"));
				rcar_OrderVO.setRcaro_status(rs.getInt("rcaro_status"));
				rcar_OrderVO.setRcaro_note(rs.getString("rcaro_note"));
				rcar_OrderVO.setLessee_name(rs.getString("lessee_name"));

				list.add(rcar_OrderVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
@Override
	public List<RcarOrderVO> getAll(int month) { //取得特定月份所有訂單
		List<RcarOrderVO> list = new ArrayList<>();
		try (Connection ct = ds.getConnection();
			PreparedStatement ps = ct.prepareStatement(MONTHALL);){
			ps.setInt(1, month);
			ps.setInt(2, month);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RcarOrderVO orderVO = new RcarOrderVO();
				orderVO.setRcaro_no(rs.getInt("RCARO_NO"));
				orderVO.setMeb_no(rs.getString("MEB_NO"));
				orderVO.setLevel_no(rs.getString("LEVEL_NO"));
				orderVO.setModel_no(rs.getString("MODEL_NO"));
				orderVO.setRcar_no(rs.getString("RCAR_NO"));
				orderVO.setRcaro_ppicktime(rs.getTimestamp("RCARO_PPICKTIME"));
				orderVO.setRcaro_pprettime(rs.getTimestamp("RCARO_PPRETTIME"));
				orderVO.setRcaro_rpicktime(rs.getTimestamp("RCARO_RPICKTIME"));
				orderVO.setRcaro_rrettime(rs.getTimestamp("RCARO_RRETTIME"));
				orderVO.setRcaro_pickuploc(rs.getString("RCARO_PICKUPLOC"));
				orderVO.setRcaro_returnloc(rs.getString("RCARO_RETURNLOC"));
				orderVO.setRcaro_returnloc_actual(rs.getString("RCARO_RETURNLOC_ACTUAL"));
				orderVO.setRcaro_time(rs.getTimestamp("RCARO_TIME"));
				orderVO.setRcaro_pay(rs.getInt("RCARO_PAY"));
				orderVO.setRcaro_extra_pay(rs.getInt("RCARO_EXTRA_PAY"));
				orderVO.setRcaro_extra_pay_status(rs.getInt("RCARO_EXTRA_PAY_STATUS"));
				orderVO.setConsume_point(rs.getInt("CONSUME_POINT"));
				orderVO.setEarn_point(rs.getInt("EARN_POINT"));
				orderVO.setEvent_no(rs.getInt("EVENT_NO"));
				orderVO.setRcaro_status(rs.getInt("RCARO_STATUS"));
				orderVO.setRcaro_note(rs.getString("RCARO_NOTE"));
				orderVO.setLessee_name(rs.getString("LESSEE_NAME"));
				list.add(orderVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// 查詢全部訂單(遞減)
	@Override
	public List<RcarOrderVO> getAllDesc(String meb_no) {

		List<RcarOrderVO> list = new ArrayList<>();
		RcarOrderVO rcar_OrderVO = null;

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GETALLDESC);){
			
			pstmt.setString(1, meb_no);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				rcar_OrderVO = new RcarOrderVO();
				
				rcar_OrderVO.setRcaro_no(rs.getInt("rcaro_no"));
				rcar_OrderVO.setMeb_no(rs.getString("meb_no"));
				rcar_OrderVO.setModel_no(rs.getString("model_no"));
				rcar_OrderVO.setRcar_no(rs.getString("rcar_no"));
				rcar_OrderVO.setRcaro_ppicktime(rs.getTimestamp("rcaro_ppicktime"));
				rcar_OrderVO.setRcaro_pprettime(rs.getTimestamp("rcaro_pprettime"));
				rcar_OrderVO.setRcaro_rpicktime(rs.getTimestamp("rcaro_rpicktime"));
				rcar_OrderVO.setRcaro_rrettime(rs.getTimestamp("rcaro_rrettime"));
				rcar_OrderVO.setRcaro_pickuploc(rs.getString("rcaro_pickuploc"));
				rcar_OrderVO.setRcaro_returnloc(rs.getString("rcaro_returnloc"));
				rcar_OrderVO.setRcaro_returnloc_actual(rs.getString("rcaro_returnloc_actual"));
				rcar_OrderVO.setRcaro_time(rs.getTimestamp("rcaro_time"));
				rcar_OrderVO.setRcaro_pay(rs.getInt("rcaro_pay"));
				rcar_OrderVO.setRcaro_extra_pay(rs.getInt("rcaro_extra_pay"));
				rcar_OrderVO.setRcaro_extra_pay_status(rs.getInt("rcaro_extra_pay_status"));
				rcar_OrderVO.setConsume_point(rs.getInt("consume_point"));
				rcar_OrderVO.setEarn_point(rs.getInt("earn_point"));
				rcar_OrderVO.setEvent_no(rs.getInt("event_no"));
				rcar_OrderVO.setRcaro_status(rs.getInt("rcaro_status"));
				rcar_OrderVO.setRcaro_note(rs.getString("rcaro_note"));
				rcar_OrderVO.setLessee_name(rs.getString("lessee_name"));

				list.add(rcar_OrderVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 查詢一筆訂單
	@Override
	public RcarOrderVO getOne(int rcaro_no) {
		RcarOrderVO rcarOrderVO = null;

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GETONE);){
			
			pstmt.setInt(1, rcaro_no);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				rcarOrderVO = new RcarOrderVO();
				
				rcarOrderVO.setRcaro_no(rs.getInt("rcaro_no"));
				rcarOrderVO.setMeb_no(rs.getString("meb_no"));
				rcarOrderVO.setLevel_no(rs.getString("level_no"));
				rcarOrderVO.setModel_no(rs.getString("model_no"));
				rcarOrderVO.setRcar_no(rs.getString("rcar_no"));
				rcarOrderVO.setRcaro_ppicktime(rs.getTimestamp("rcaro_ppicktime"));
				rcarOrderVO.setRcaro_pprettime(rs.getTimestamp("rcaro_pprettime"));
				rcarOrderVO.setRcaro_rpicktime(rs.getTimestamp("rcaro_rpicktime"));
				rcarOrderVO.setRcaro_rrettime(rs.getTimestamp("rcaro_rrettime"));
				rcarOrderVO.setRcaro_pickuploc(rs.getString("rcaro_pickuploc"));
				rcarOrderVO.setRcaro_returnloc(rs.getString("rcaro_returnloc"));
				rcarOrderVO.setRcaro_returnloc_actual(rs.getString("rcaro_returnloc_actual"));
				rcarOrderVO.setRcaro_time(rs.getTimestamp("rcaro_time"));
				rcarOrderVO.setRcaro_pay(rs.getInt("rcaro_pay"));
				rcarOrderVO.setRcaro_extra_pay(rs.getInt("rcaro_extra_pay"));
				rcarOrderVO.setRcaro_extra_pay_status(rs.getInt("rcaro_extra_pay_status"));
				rcarOrderVO.setConsume_point(rs.getInt("consume_point"));
				rcarOrderVO.setEarn_point(rs.getInt("earn_point"));
				rcarOrderVO.setEvent_no(rs.getInt("event_no"));
				rcarOrderVO.setRcaro_status(rs.getInt("rcaro_status"));
				rcarOrderVO.setRcaro_note(rs.getString("rcaro_note"));
				rcarOrderVO.setLessee_name(rs.getString("lessee_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rcarOrderVO;
	}

@Override
	public List<RcarOrderVO> getOthercar(String store, LocalDate Date) {//取得還車點 是本門市訂單 區間~+7天車輛
		ArrayList<RcarOrderVO> list = new ArrayList<>();
		try (Connection ct = ds.getConnection(); 
				PreparedStatement ps = ct.prepareStatement(OTHER_CAR_ORDER);) {
			ps.setString(1, store);
			ps.setString(2, store);
			ps.setObject(3, LocalDateTime.of(Date, LocalTime.MIN));
			ps.setObject(4, LocalDateTime.of(Date, LocalTime.MAX).plusDays(7));
			ps.setObject(5, LocalDateTime.of(Date, LocalTime.MIN));
			ps.setObject(6, LocalDateTime.of(Date, LocalTime.MAX).plusDays(7));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RcarOrderVO orderVO = new RcarOrderVO();
				orderVO.setRcaro_no(rs.getInt("RCARO_NO"));
				orderVO.setMeb_no(rs.getString("MEB_NO"));
				orderVO.setLevel_no(rs.getString("LEVEL_NO"));
				orderVO.setModel_no(rs.getString("MODEL_NO"));
				orderVO.setRcar_no(rs.getString("RCAR_NO"));
				orderVO.setRcaro_ppicktime(rs.getTimestamp("RCARO_PPICKTIME"));
				orderVO.setRcaro_pprettime(rs.getTimestamp("RCARO_PPRETTIME"));
				orderVO.setRcaro_rpicktime(rs.getTimestamp("RCARO_RPICKTIME"));
				orderVO.setRcaro_rrettime(rs.getTimestamp("RCARO_RRETTIME"));
				orderVO.setRcaro_pickuploc(rs.getString("RCARO_PICKUPLOC"));
				orderVO.setRcaro_returnloc(rs.getString("RCARO_RETURNLOC"));
				orderVO.setRcaro_returnloc_actual(rs.getString("RCARO_RETURNLOC_ACTUAL"));
				orderVO.setRcaro_time(rs.getTimestamp("RCARO_TIME"));
				orderVO.setRcaro_pay(rs.getInt("RCARO_PAY"));
				orderVO.setRcaro_extra_pay(rs.getInt("RCARO_EXTRA_PAY"));
				orderVO.setRcaro_extra_pay_status(rs.getInt("RCARO_EXTRA_PAY_STATUS"));
				orderVO.setConsume_point(rs.getInt("CONSUME_POINT"));
				orderVO.setEarn_point(rs.getInt("EARN_POINT"));
				orderVO.setEvent_no(rs.getInt("EVENT_NO"));
				orderVO.setRcaro_status(rs.getInt("RCARO_STATUS"));
				orderVO.setRcaro_note(rs.getString("RCARO_NOTE"));
				orderVO.setLessee_name(rs.getString("LESSEE_NAME"));
				list.add(orderVO);
			}
			return list;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<ReDay> getallday(List<RcarVO> st_rcar_no, int month) { // 取得門市當月訂單 用於配車表用

		ArrayList<ReDay> arrayList = new ArrayList<>();
		int summonth = month * 2; // 將月份 * 2
		for (RcarVO no : st_rcar_no) { // 將每台車 new 一個ReDay 存放出租日期
			ReDay re = new ReDay();
			re.setCar_no(no.getRcar_no());
			re.setCar_model(no.getModel_no());
			arrayList.add(re);
		}
		String plate = st_rcar_no.get(0).getRcar_no().substring(0, 3);// 取得車牌 前面3個字做模糊查詢
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(CAR_ORDER);) {
			ps.setString(1, "%" + plate + "%");
			ps.setInt(2, summonth - 2);
			ps.setInt(3, summonth - 1);
			ps.setInt(4, summonth);
			ps.setInt(5, summonth + 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String car_no = rs.getString("RCAR_NO"); // 取得查詢到的車牌
				for (ReDay no : arrayList) { // 將所有車輛進行比對
					if (no.getCar_no().equals(car_no)) {
						no.day(rs.getInt(1), rs.getInt(2), rs.getInt(3), month, rs.getString("RCARO_PICKUPLOC"),
								rs.getString("RCARO_RETURNLOC"),rs.getString("RCARO_RETURNLOC_ACTUAL"),rs.getInt(8));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}

	// 取消訂單
	@Override
	public void cancel(int rcaro_no) {
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(CANCEL);){
			
			pstmt.setInt(1, rcaro_no);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(RcarOrderVO rcarOrderVO) {
		String[] columns = {"RCARO_NO"};
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(INSERT, columns);) {
			ps.setString(1, rcarOrderVO.getMeb_no());
			ps.setString(2, rcarOrderVO.getModel_no());
			ps.setTimestamp(3, rcarOrderVO.getRcaro_ppicktime());
			ps.setTimestamp(4, rcarOrderVO.getRcaro_pprettime());
			ps.setString(5, rcarOrderVO.getRcaro_pickuploc());
			ps.setString(6, rcarOrderVO.getRcaro_returnloc());
//			ps.setTimestamp(7, rcarOrderVO.getRcaro_date()); //新版本自動更新
			ps.setInt(7, rcarOrderVO.getRcaro_pay()); // 無使用給0
			ps.setInt(8, rcarOrderVO.getConsume_point());
			ps.setInt(9, rcarOrderVO.getEvent_no()); // 無活動也要給值
			ps.setString(10, rcarOrderVO.getRcaro_note());
			ps.setString(11, rcarOrderVO.getLessee_name());
			ps.setString(12, rcarOrderVO.getLevel_no());// 0626 新增
			ps.setInt(13, rcarOrderVO.getEarn_point());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			int i = -1;
			
			if(rs.next()) {
				i = rs.getInt(1);// DEBUG "RCARO_NO"
				System.out.println("訂單編號是 " + i);
			}
			return i;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	


	@Override
	public List<RcarOrderVO> getbetween_order(String rcar_no, LocalDate Date) { //取得車輛 區間內訂單
		List<RcarOrderVO> list = new ArrayList<>();
		try (Connection ct = ds.getConnection();
			PreparedStatement ps = ct.prepareStatement(GET_CAR_BETWEEN_ORDER);) {
			ps.setString(1, rcar_no);
			ps.setObject(2, LocalDateTime.of(Date, LocalTime.MIN));
			ps.setObject(3, LocalDateTime.of(Date, LocalTime.MAX).plusDays(7));
			ps.setObject(4, LocalDateTime.of(Date, LocalTime.MIN));
			ps.setObject(5, LocalDateTime.of(Date, LocalTime.MAX).plusDays(7));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RcarOrderVO orderVO = new RcarOrderVO();
				orderVO.setRcaro_no(rs.getInt("RCARO_NO"));
				orderVO.setMeb_no(rs.getString("MEB_NO"));
				orderVO.setLevel_no(rs.getString("LEVEL_NO"));
				orderVO.setModel_no(rs.getString("MODEL_NO"));
				orderVO.setRcar_no(rs.getString("RCAR_NO"));
				orderVO.setRcaro_ppicktime(rs.getTimestamp("RCARO_PPICKTIME"));
				orderVO.setRcaro_pprettime(rs.getTimestamp("RCARO_PPRETTIME"));
				orderVO.setRcaro_rpicktime(rs.getTimestamp("RCARO_RPICKTIME"));
				orderVO.setRcaro_rrettime(rs.getTimestamp("RCARO_RRETTIME"));
				orderVO.setRcaro_pickuploc(rs.getString("RCARO_PICKUPLOC"));
				orderVO.setRcaro_returnloc(rs.getString("RCARO_RETURNLOC"));
				orderVO.setRcaro_returnloc_actual(rs.getString("RCARO_RETURNLOC_ACTUAL"));
				orderVO.setRcaro_time(rs.getTimestamp("RCARO_TIME"));
				orderVO.setRcaro_pay(rs.getInt("RCARO_PAY"));
				orderVO.setRcaro_extra_pay(rs.getInt("RCARO_EXTRA_PAY"));
				orderVO.setRcaro_extra_pay_status(rs.getInt("RCARO_EXTRA_PAY_STATUS"));
				orderVO.setConsume_point(rs.getInt("CONSUME_POINT"));
				orderVO.setEarn_point(rs.getInt("EARN_POINT"));
				orderVO.setEvent_no(rs.getInt("EVENT_NO"));
				orderVO.setRcaro_status(rs.getInt("RCARO_STATUS"));
				orderVO.setRcaro_note(rs.getString("RCARO_NOTE"));
				orderVO.setLessee_name(rs.getString("LESSEE_NAME"));
				list.add(orderVO);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RcarOrderVO> getByCompositeQuery(Map<String, String[]> paramMap) {
		List<RcarOrderVO> list = new ArrayList<RcarOrderVO>();
		int paramCount = 1;
		Set<String> keySet = paramMap.keySet();

		String querySQL = "SELECT * FROM RCAR_ORDER ";
		StringBuffer whereSQL = new StringBuffer();
		for (String key : keySet) {
			String param = paramMap.get(key)[0];
			if ("".equals(param)) {
				continue;
			}
			if (paramCount == 1) {
				whereSQL.append("where " + key + " like '" + "%" + param + "%" + "'");
			} else {
				whereSQL.append(" and " + key + " like '" + "%" + param + "%" + "'");
			}
			paramCount++;
		}

		String QUERY = querySQL + whereSQL.toString();
		System.out.println("[RcarOrderDAOImpl @ getByCompositeQuery()]複合查詢字串為:" + QUERY);
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(QUERY);) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RcarOrderVO orderVO = new RcarOrderVO();
				orderVO.setRcaro_no(rs.getInt("RCARO_NO"));
				orderVO.setMeb_no(rs.getString("MEB_NO"));
				orderVO.setLevel_no(rs.getString("LEVEL_NO"));
				orderVO.setModel_no(rs.getString("MODEL_NO"));
				orderVO.setRcar_no(rs.getString("RCAR_NO"));
				orderVO.setRcaro_ppicktime(rs.getTimestamp("RCARO_PPICKTIME"));
				orderVO.setRcaro_pprettime(rs.getTimestamp("RCARO_PPRETTIME"));
				orderVO.setRcaro_rpicktime(rs.getTimestamp("RCARO_RPICKTIME"));
				orderVO.setRcaro_rrettime(rs.getTimestamp("RCARO_RRETTIME"));
				orderVO.setRcaro_pickuploc(rs.getString("RCARO_PICKUPLOC"));
				orderVO.setRcaro_returnloc(rs.getString("RCARO_RETURNLOC"));
				orderVO.setRcaro_returnloc_actual(rs.getString("RCARO_RETURNLOC_ACTUAL"));
				orderVO.setRcaro_time(rs.getTimestamp("RCARO_TIME"));
				orderVO.setRcaro_pay(rs.getInt("RCARO_PAY"));
				orderVO.setRcaro_extra_pay(rs.getInt("RCARO_EXTRA_PAY"));
				orderVO.setRcaro_extra_pay_status(rs.getInt("RCARO_EXTRA_PAY_STATUS"));
				orderVO.setConsume_point(rs.getInt("CONSUME_POINT"));
				orderVO.setEarn_point(rs.getInt("EARN_POINT"));
				orderVO.setEvent_no(rs.getInt("EVENT_NO"));
				orderVO.setRcaro_status(rs.getInt("RCARO_STATUS"));
				orderVO.setRcaro_note(rs.getString("RCARO_NOTE"));
				orderVO.setLessee_name(rs.getString("LESSEE_NAME"));
				list.add(orderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean update(RcarOrderVO rcarOrderVO) {
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(UPDATE);) {

			ps.setString(1, rcarOrderVO.getModel_no());
			ps.setString(2, rcarOrderVO.getRcar_no());

			ps.setTimestamp(3, rcarOrderVO.getRcaro_ppicktime());
			ps.setTimestamp(4, rcarOrderVO.getRcaro_pprettime());

			ps.setTimestamp(5, rcarOrderVO.getRcaro_rpicktime());
			ps.setTimestamp(6, rcarOrderVO.getRcaro_rrettime());

			ps.setString(7, rcarOrderVO.getRcaro_pickuploc());
			ps.setString(8, rcarOrderVO.getRcaro_returnloc());
			// ps.setTimestamp(7, rcarOrderVO.getRcaro_date()); //新版本自動更新
			ps.setString(9, rcarOrderVO.getRcaro_returnloc_actual());

			ps.setInt(10, rcarOrderVO.getRcaro_pay()); // 無使用給0
			ps.setInt(11, rcarOrderVO.getRcaro_extra_pay());
			ps.setInt(12, rcarOrderVO.getRcaro_extra_pay_status());

			ps.setInt(13, rcarOrderVO.getConsume_point());
			ps.setInt(14, rcarOrderVO.getEarn_point());
			ps.setInt(15, rcarOrderVO.getEvent_no()); // 無活動也要給值
			ps.setInt(16, rcarOrderVO.getRcaro_status());

			ps.setString(17, rcarOrderVO.getRcaro_note());
			ps.setString(18, rcarOrderVO.getLessee_name());
			ps.setString(19, rcarOrderVO.getMeb_no());
			ps.setString(20, rcarOrderVO.getLevel_no());

			ps.setInt(21, rcarOrderVO.getRcaro_no());

			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<ReDay> getallday(List<RcarVO> st_rcar_no, LocalDate date) {
		ArrayList<ReDay> arrayList = new ArrayList<>();
		int month = date.getMonthValue();
		int summonth = date.getMonthValue() * 2; // 將月份 * 2 
		for (RcarVO no : st_rcar_no) { //將每台車 new 一個ReDay 存放出租日期
			ReDay re = new ReDay();
			re.setCar_no(no.getRcar_no());
			re.setCar_model(no.getModel_no());
			re.setStore(no.getSt_no());
			re.setStatus(no.getRcar_status());
			arrayList.add(re);
		}
		String plate = st_rcar_no.get(0).getRcar_no().substring(0, 3);//取得車牌 前面3個字做模糊查詢
		try (Connection ct = ds.getConnection(); 
				PreparedStatement ps = ct.prepareStatement(CAR_ORDER);) {
			ps.setString(1, "%"+plate+"%");
			ps.setInt(2, summonth-2);
			ps.setInt(3, summonth-1);
			ps.setInt(4, summonth);
			ps.setInt(5, summonth+1);
			ps.setInt(6, date.getYear());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String car_no = rs.getString("RCAR_NO"); //取得查詢到的車牌
				for (ReDay no : arrayList) { // 將所有車輛進行比對
					if(car_no.equals(no.getCar_no())) {
						System.out.println("111"+rs.getInt(8));
						
						no.day(rs.getInt(1), rs.getInt(2),rs.getInt(3),month,rs.getString("RCARO_PICKUPLOC"),rs.getString("RCARO_RETURNLOC"),rs.getString("RCARO_RETURNLOC_ACTUAL"),rs.getInt(8));
						break;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}
	
	@Override
	public List<RcarOrderVO> getByOrderStatus(int status) {
		List<RcarOrderVO> list = new ArrayList<>();
		try (Connection ct = ds.getConnection(); PreparedStatement ps = ct.prepareStatement(GET_BY_STATUS);) {
			ps.setInt(1, status);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RcarOrderVO orderVO = new RcarOrderVO();
				orderVO.setRcaro_no(rs.getInt("RCARO_NO"));
				orderVO.setMeb_no(rs.getString("MEB_NO"));
				orderVO.setLevel_no(rs.getString("LEVEL_NO"));
				orderVO.setModel_no(rs.getString("MODEL_NO"));
				orderVO.setRcar_no(rs.getString("RCAR_NO"));
				orderVO.setRcaro_ppicktime(rs.getTimestamp("RCARO_PPICKTIME"));
				orderVO.setRcaro_pprettime(rs.getTimestamp("RCARO_PPRETTIME"));
				orderVO.setRcaro_rpicktime(rs.getTimestamp("RCARO_RPICKTIME"));
				orderVO.setRcaro_rrettime(rs.getTimestamp("RCARO_RRETTIME"));
				orderVO.setRcaro_pickuploc(rs.getString("RCARO_PICKUPLOC"));
				orderVO.setRcaro_returnloc(rs.getString("RCARO_RETURNLOC"));
				orderVO.setRcaro_returnloc_actual(rs.getString("RCARO_RETURNLOC_ACTUAL"));
				orderVO.setRcaro_time(rs.getTimestamp("RCARO_TIME"));
				orderVO.setRcaro_pay(rs.getInt("RCARO_PAY"));
				orderVO.setRcaro_extra_pay(rs.getInt("RCARO_EXTRA_PAY"));
				orderVO.setRcaro_extra_pay_status(rs.getInt("RCARO_EXTRA_PAY_STATUS"));
				orderVO.setConsume_point(rs.getInt("CONSUME_POINT"));
				orderVO.setEarn_point(rs.getInt("EARN_POINT"));
				orderVO.setEvent_no(rs.getInt("EVENT_NO"));
				orderVO.setRcaro_status(rs.getInt("RCARO_STATUS"));
				orderVO.setRcaro_note(rs.getString("RCARO_NOTE"));
				orderVO.setLessee_name(rs.getString("LESSEE_NAME"));
				list.add(orderVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


}
