package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class jdbcUtilIssue {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("issue_no".equals(columnName))
			aCondition = columnName + "= '" + value + "'";
		if ("issue_name".equals(columnName) || "issue_tel".equals(columnName) || "issue_mail".equals(columnName)) // 用於
																													// varchar
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
