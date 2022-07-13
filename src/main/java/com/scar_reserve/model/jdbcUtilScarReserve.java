package com.scar_reserve.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class jdbcUtilScarReserve {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("sr_no".equals(columnName))
			aCondition = columnName + "= '" + value + "'";
		if ("meb_no".equals(columnName) || "scar_no".equals(columnName) || "st_no".equals(columnName)) // 用於 varchar
			aCondition = columnName + " like '%" + value + "%'";

		// 時間判斷
		if("sr_start_time".equals(columnName)) {
			aCondition = "sr_time >= '" + value + "' ";
		}
		
		if("sr_end_time".equals(columnName)) {
			aCondition = "sr_time <= '" + value + "' ";
		}
		
		return aCondition + " ";

	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		System.out.println(keys);
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if(value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());
				
				
				if (count == 1) {
					whereCondition.append(" where " + aCondition);}
				
				
				else 
					whereCondition.append(" and " + aCondition);
				
//				System.out.println("有送出查詢資料的欄位數count = " + count);
				
			}
		}
		System.out.println("test 48 is whereCondition.toString() :" + whereCondition.toString());
		return whereCondition.toString();
	}

	
//	public static void main(String argv[]) {
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("sr_no", new String[] { "50" });
//		map.put("meb_no", new String[] { "B221587044" });
//		map.put("sr_time", new String[] { "2022-07-07" });
//		
//		String finalSQL = "select * from CGA102G4.SCAR_RESERVE " + jdbcUtilScarReserve.get_WhereCondition(map);
//		System.out.println("finalSQL = " + finalSQL);
//	}
	
	
}
