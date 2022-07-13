package utils;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class jdbcUtilScar {

	public static String get_aCondition_For_myDB(String columnName, String value) {
		String aCondition = null;
		// st_no
		//
		if ("st_no".equals(columnName) || "scar_brand".equals(columnName) || "scar_status".equals(columnName))
			aCondition = columnName + "= '" + value+"'";
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

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		return whereCondition.toString();
	}

}
