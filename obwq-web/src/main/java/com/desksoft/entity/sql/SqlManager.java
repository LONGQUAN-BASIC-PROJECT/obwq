package com.desksoft.entity.sql;

import java.util.ArrayList;
import java.util.List;
 
/**
 * sql对象列表
 */
public class SqlManager {
	/** sql对象列表 */
	private List<Sql> sqls = new ArrayList<Sql>();

	public List<Sql> getSqls() {
		return sqls;
	}

	public void setSqls(List<Sql> sqls) {
		this.sqls = sqls;
	}
}
