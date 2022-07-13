package com.carrentable.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CarRentableDAOImpl implements CarRentableDAO {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CGA102G4?serverTimezone=Asia/Taipei";
	String userId = "root";
	String password = "password";

	private String INSERT = "INSERT INTO "
			+ "CAR_RENTABLE(ST_NO,MODEL_NO,RESERVE_TIME,TOTAL_CARS,REST_CARS, FOREIGN_CARS,RENTABLE)"
			+ "VALUE(?,?,?,?,?,?,?);";

	private String DELETE = "DELETE FROM CAR_RENTABLE WHERE ST_NO = ? AND MODEL_NO = ? AND RESERVE_TIME = ?;";

	private String UPDATE = "UPDATE CAR_RENTABLE "
			+ "SET ST_NO = ?, MODEL_NO = ?, RESERVE_TIME = ?, TOTAL_CARS = ?,REST_CARS = ?, FOREIGN_CARS = ?, RENTABLE = ?"
			+ " WHERE ST_NO = ? AND MODEL_NO = ? AND RESERVE_TIME = ?;";

	private String GET_BY_PK = "SELECT * FROM CAR_RENTABLE WHERE ST_NO = ? AND MODEL_NO = ? AND RESERVE_TIME = ?;";

	private String GET_ALL = "SELECT * FROM CAR_RENTABLE;";

	private String GET_ALL_BY_STORE = "SELECT * FROM CAR_RENTABLE WHERE ST_NO = ?;";
	
	private String GET_BY_ORDER_DEMAND = "SELECT * FROM CGA102G4.CAR_RENTABLE "
			+ "WHERE ST_NO = ? AND "
			+ " MODEL_NO = ? AND "
			+ " RESERVE_TIME BETWEEN ? AND ?;";
	
	public void insert(CarRentableVO rentableVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userId, password);
			ps = con.prepareStatement(INSERT);

			ps.setString(1, rentableVO.getSt_no());
			ps.setString(2, rentableVO.getModel_no());
			ps.setTimestamp(3, rentableVO.getReserve_time());
			ps.setInt(4, rentableVO.getTotal_cars());
			ps.setInt(5, rentableVO.getRest_cars());
			ps.setInt(6, rentableVO.getForeign_cars());
			ps.setInt(7, rentableVO.getRentable());

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(CarRentableVO rentableVO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userId, password);
			ps = con.prepareStatement(UPDATE);

			// Set update
			ps.setString(1, rentableVO.getSt_no());
			ps.setString(2, rentableVO.getModel_no());
			ps.setTimestamp(3, rentableVO.getReserve_time());
			ps.setInt(4, rentableVO.getTotal_cars());
			ps.setInt(5, rentableVO.getRest_cars());
			ps.setInt(6, rentableVO.getForeign_cars());
			ps.setInt(7, rentableVO.getRentable());

			// set which to update
			ps.setString(8, rentableVO.getSt_no());
			ps.setString(9, rentableVO.getModel_no());
			ps.setTimestamp(10, rentableVO.getReserve_time());

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<CarRentableVO> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CarRentableVO> list = new ArrayList<CarRentableVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userId, password);
			ps = con.prepareStatement(GET_ALL);

			rs = ps.executeQuery();

			while (rs.next()) {
				CarRentableVO obj = new CarRentableVO();
				// System.out.println("rs.next(): " + rs.getString("ST_NO"));
				obj.setSt_no(rs.getString("ST_NO"));
				obj.setModel_no(rs.getString("MODEL_NO"));
				obj.setReserve_time(rs.getTimestamp("RESERVE_TIME"));
				obj.setTotal_cars(rs.getInt("TOTAL_CARS"));
				obj.setRest_cars(rs.getInt("REST_CARS"));
				obj.setForeign_cars(rs.getInt("FOREIGN_CARS"));
				obj.setRentable(rs.getInt("RENTABLE"));

				list.add(obj);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				con.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;
	}

	public List<CarRentableVO> getByPK(String st_no, String model_no, Timestamp reserve_time) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CarRentableVO> list = new ArrayList<CarRentableVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userId, password);
			ps = con.prepareStatement(GET_BY_PK);
			
			ps.setString(1, st_no);
			ps.setString(2, model_no);
			ps.setTimestamp(3, reserve_time);
			
			
			rs = ps.executeQuery();

			// Car_RentableVO obj = new Car_RentableVO(); // �������o�̷|�C�����X�@�˪����?
			while (rs.next()) {
				CarRentableVO obj = new CarRentableVO();
				
				obj.setSt_no(rs.getString("ST_NO"));
				obj.setModel_no(rs.getString("MODEL_NO"));
				obj.setReserve_time(rs.getTimestamp("RESERVE_TIME"));
				obj.setTotal_cars(rs.getInt("TOTAL_CARS"));
				obj.setRest_cars(rs.getInt("REST_CARS"));
				obj.setForeign_cars(rs.getInt("FOREIGN_CARS"));
				obj.setRentable(rs.getInt("RENTABLE"));

				list.add(obj);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				con.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;
	}

	@Override
	public void delete(String st_no, String model_no, Timestamp reserve_time) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userId, password);
			ps = con.prepareStatement(DELETE);
			ps.setString(1, st_no);
			ps.setString(2, model_no);
			ps.setTimestamp(3, reserve_time);
			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public boolean hasCar(String st_no, String model_no, Timestamp orderStartTime, Timestamp orderReturnTime) {
		// 0618 還沒寫 先讓他回傳true 讓使用者可以順利租車
		// 0624 開始加入基本查詢 不考慮鎖定問題(查詢應該不用鎖定)
		return true;
	}

	@Override
	public HashMap<String, TreeMap<Timestamp, CarRentableVO>> getAllByStore(String st_no) {

		HashMap<String, TreeMap<Timestamp, CarRentableVO>> map = new HashMap<String, TreeMap<Timestamp, CarRentableVO>>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CarRentableVO> list = new ArrayList<CarRentableVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userId, password);
			ps = con.prepareStatement(GET_ALL_BY_STORE);
			
			ps.setString(1, st_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				CarRentableVO obj = new CarRentableVO();
				// System.out.println("rs.next(): " + rs.getString("ST_NO"));
				obj.setSt_no(rs.getString("ST_NO"));
				obj.setModel_no(rs.getString("MODEL_NO"));
				obj.setReserve_time(rs.getTimestamp("RESERVE_TIME"));
				obj.setTotal_cars(rs.getInt("TOTAL_CARS"));
				obj.setRest_cars(rs.getInt("REST_CARS"));
				obj.setForeign_cars(rs.getInt("FOREIGN_CARS"));
				obj.setRentable(rs.getInt("RENTABLE"));
				
				
				String carModel = rs.getString("MODEL_NO");
				Timestamp reserveTime = rs.getTimestamp("RESERVE_TIME");

				if (map.get(carModel) == null) {
					TreeMap<Timestamp, CarRentableVO> treeMap = new TreeMap<Timestamp, CarRentableVO>();
					treeMap.put(reserveTime, obj);
					map.put(carModel, treeMap);
				} else {
					TreeMap<Timestamp, CarRentableVO> treeMap = map.get(carModel);
					treeMap.put(reserveTime, obj);
					map.put(carModel, treeMap);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				con.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return map;

	}

	@Override
	public List<CarRentableVO> getByOrderInfo(String st_no, String model_no, Timestamp startTime, Timestamp endTime) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CarRentableVO> list = new ArrayList<CarRentableVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userId, password);
			ps = con.prepareStatement(GET_BY_ORDER_DEMAND);
			
			ps.setString(1, st_no);
			ps.setString(2, model_no);
			ps.setTimestamp(3, startTime);
			ps.setTimestamp(4, endTime);
			
			rs = ps.executeQuery();

			// Car_RentableVO obj = new Car_RentableVO(); // �������o�̷|�C�����X�@�˪����?
			while (rs.next()) {
				CarRentableVO obj = new CarRentableVO();
				
				obj.setSt_no(rs.getString("ST_NO"));
				obj.setModel_no(rs.getString("MODEL_NO"));
				obj.setReserve_time(rs.getTimestamp("RESERVE_TIME"));
				obj.setTotal_cars(rs.getInt("TOTAL_CARS"));
				obj.setRest_cars(rs.getInt("REST_CARS"));
				obj.setForeign_cars(rs.getInt("FOREIGN_CARS"));
				obj.setRentable(rs.getInt("RENTABLE"));

				list.add(obj);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				con.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;
	}

	/***************************************
	 * Main 測試區
	 *********************************************/

//	public static void main(String[] args) {
//		CarRentableDAOImpl carRentableDAOImpl = new CarRentableDAOImpl();
//		HashMap<String, TreeMap<Timestamp, CarRentableVO>> allByStore = carRentableDAOImpl.getAllByStore("TPE");
//
//		for (Entry<Timestamp, CarRentableVO> entry : allByStore.get("ALTIS").entrySet()) {
//			System.out.println("占用時間: " + entry.getKey() + "\t剩餘台數: " + entry.getValue().getRest_cars());
//
//		}
//		System.out.println("Main結束");
//	}

	/***************************************
	 * Main 結束
	 *********************************************/

}
