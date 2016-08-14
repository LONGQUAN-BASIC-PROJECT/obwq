package com.desksoft.util;

import java.util.Map;

public class SqlUtil {

	/**
	 * 转换SQL
	 * 
	 * SQL语句参数用##包围，SQL的语句参数名与Map的主键相同
	 * 
	 * @param sql
	 * @param map
	 * @return
	 */
	public static String convertSql(String sql, Map<String, Object> map) {
		for (String str : map.keySet()) {
			if(sql.indexOf("#"+str+"#") != -1)
				sql = sql.replaceAll("#" + str + "#", map.get(str)+"");
		}
		return sql;
	}
}
