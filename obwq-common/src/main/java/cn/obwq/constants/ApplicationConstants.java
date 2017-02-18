package cn.obwq.constants;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {

	public  static String WX_DEFAULT_REPLY = "";

	public static Map<String,String> keyMap = new HashMap<String, String>();

	public  static String Q_KEY = "qkey";

	public static String GEN_STATIC_TEXT(){
		
		StringBuilder sb = new StringBuilder() ;
		sb.append("您好，我是大龙拳，我可以陪你聊天的哦,输入以下关键字，有惊喜哦").append("\n\n");
		sb.append("1:重复最近一次的话").append("\n");
		sb.append("en:开始学习四级英语单词").append("\n");
		sb.append("?:显示此菜单").append("\n");
		
		
		return sb.toString() ;
	}

	static {
		WX_DEFAULT_REPLY = GEN_STATIC_TEXT();

		keyMap.put("酥胸","7801");
		keyMap.put("少女","7802");
		keyMap.put("私房","7803");
		keyMap.put("美腿","7804");
		keyMap.put("巨乳","7805");
		keyMap.put("清纯","7806");
		keyMap.put("可爱","7807");
		keyMap.put("美臀","7808");
		keyMap.put("全裸","7809");
		keyMap.put("成熟","7810");
		keyMap.put("气质","7811");
		keyMap.put("模特","7812");
		keyMap.put("半裸","7813");
		keyMap.put("女神","7814");
		keyMap.put("DEFAULT","7814");



	}
	
}

