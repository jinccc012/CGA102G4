package utils;

import java.util.Map;
import java.util.Set;

public class jdbcUtilRcarOrder {

	public static String get_Condition_For_DB(String columnName, String value) {

		String aCondition = null;

		if ("rcaro_no".equals(columnName)) {
			aCondition = columnName + "= '" + value + "'";
		}
		if ("meb_no".equals(columnName) || "level_no".equals(columnName) || "model_no".equals(columnName)
				|| "rcar_no".equals(columnName) || "rcaro_pickuploc".equals(columnName)) {
			aCondition = columnName + " like '%" + value + "%'";
		}
		if ("rcaro_ppicktime".equals(columnName)) {
			aCondition = "rcaro_ppicktime >= '" + value + "' ";
		}
		if ("rcaro_pprettime".equals(columnName)) {
			aCondition = "rcaro_pprettime <= '" + value + "' ";
		}

		return aCondition + " ";
	}

	
	public static String get_WhereCondition(Map<String, String[]> map) {
//		map.forEach(null); // 後續有時間再研究怎麼改
		Set<String> keys = map.keySet();
		System.out.println(keys);
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if(value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_Condition_For_DB(key, value.trim());
				
				
				if (count == 1) {
					whereCondition.append(" where " + aCondition);
					
				}
				
				else 
					whereCondition.append(" and " + aCondition);
				
				
			}
		}
		return whereCondition.toString();
	}
	
}
