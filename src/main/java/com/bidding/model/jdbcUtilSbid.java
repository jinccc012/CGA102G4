package com.bidding.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class jdbcUtilSbid {
	public static String get_aCondition_For_myDB(String columnName, String value) {
		String aCondition = null;
		//
		if ("scar_no".equals(columnName) || "meb_no".equals(columnName))
			aCondition = columnName + " like '%" + value + "%'";
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
			}
		}
		return whereCondition.toString();
	}

//	public static void main(String[] args) {
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("scar_no", new String[] { "AAAAAAAAAAAAAAAAA" });
//		map.put("meb_no", new String[] { "B12" });
//		String finalSQL = "select * from CGA102G4.BIDDING " + jdbcUtilSbid.get_WhereCondition(map);
//		// + "order by CGA102G4.SCAR";
//		System.out.println("●●finalSQL = " + finalSQL);
//	}

}
