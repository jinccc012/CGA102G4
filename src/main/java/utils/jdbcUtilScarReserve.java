package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class jdbcUtilScarReserve {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("sr_no".equals(columnName))
			aCondition = columnName + "= '" + value + "'";
		if ("meb_no".equals(columnName) || "scar_no".equals(columnName) || "st_no".equals(columnName)) // 用於 varchar
			aCondition = columnName + " like '%" + value + "%'";
System.out.println("Test");
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
				
				
			}
		}
		return whereCondition.toString();
	}

	
	
}
