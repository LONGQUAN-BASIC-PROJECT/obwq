package com.desksoft.test;

import java.util.HashMap;
import java.util.Map;

import com.desksoft.util.LoadSql;
import com.desksoft.util.SqlUtil;

public class T {
	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentId", 1l);
		String str = LoadSql.getSqlContent("mood_getMood");
		String slq = SqlUtil.convertSql(str, map);
		System.out.println(str);
	}
}
