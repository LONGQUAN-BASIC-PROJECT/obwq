package cn.obwq.constants;

public class ApplicationConstants {

	public  static String WX_DEFAULT_REPLY = "";

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
	}
	
}

