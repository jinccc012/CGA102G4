package com.scar.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class jdbcUtilScar {

	public static String get_aCondition_For_myDB(String columnName, String value) {
		String aCondition = null;
		// st_no
		//
		if ("st_no".equals(columnName) || "scar_brand".equals(columnName) || "scar_status".equals(columnName))
			aCondition = columnName + "= '" + value+"'";//st_no = "Kh" ;
		//date 
		else if("scar_startime".equals(columnName))
			aCondition = columnName + ">" + "'"+ value +"'";  
		else if("scar_endtime".equals(columnName))
			aCondition = columnName + "<" + "'"+ value +"'";   
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

	public static void main(String[] args) {
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("st_no", new String[] { "KH" });
		map.put("scar_brand", new String[] { "MAZDA" });
		map.put("scar_status", new String[] { "0" });
		String finalSQL = "select * from CGA102G4.SCAR " + jdbcUtilScar.get_WhereCondition(map);
			//	+ "order by CGA102G4.SCAR";
		System.out.println("●●finalSQL = " + finalSQL);
	}
}
