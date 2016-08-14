package com.desksoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.desksoft.entity.sql.Sql;
import com.desksoft.entity.sql.SqlManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LoadSql {
	
	private String folder = "sql";
	
	public static Map<String, String> map = new HashMap<String, String>();
	
	public  void loadSqlContent() throws Exception {
		map = new HashMap<String, String>();
		XStream xstream = new XStream(new DomDriver());
		// xml元素 与类 对应关系
		xstream.alias("sql-manager",SqlManager.class);
		xstream.alias("sql", Sql.class);
		String u = LoadSql.class.getResource("/"+folder).getFile();
		
		File f = new File(u);
		File[] list = f.listFiles();
		for(File l : list){
				InputStream in = new FileInputStream(l);
				SqlManager sqlManager = (SqlManager) xstream.fromXML(in);
				// sql ID 与内容map
				if (sqlManager != null && sqlManager.getSqls() != null && sqlManager.getSqls().size() > 0) {
					for (int i = 0; i < sqlManager.getSqls().size(); i++) {
						Sql sql = sqlManager.getSqls().get(i);
						String key = l.getName().substring(0,l.getName().indexOf(".")) + "_" + sql.getId().trim();
						if(map.get(key) != null){
							throw new RuntimeException("sql.getId()");
						}
						map.put(key, sql.getContent().trim());
					}
				}
		}
	}	
	
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	/**
	 * SQL的标识为：文件名_SQL的ID
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static String getSqlContent(String id) throws Exception {
		if(map.isEmpty()){
			new LoadSql().loadSqlContent();
		}
		
		if(map.get(id) == null){
			throw new RuntimeException("不存在SQL，SQL标识：文件名+SQL的ID");
		}
		return map.get(id) ;
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentId", 1l);
		String str = getSqlContent("mood_getMood");
		//String slq = SqlUtil.convertSql(str, map);
		System.out.println(str);
	}
}
