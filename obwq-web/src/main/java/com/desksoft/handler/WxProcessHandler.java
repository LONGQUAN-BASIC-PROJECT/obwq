package com.desksoft.handler;

import java.util.Date;
import java.util.List;

import com.desksoft.entity.ReceiveXmlEntity;
import com.desksoft.entity.dto.WxNewsReplyDto;

public abstract class WxProcessHandler {

	public  abstract String handler(ReceiveXmlEntity xmlEntity) ;
	

	/**文字消息 */
	protected String formartTextResponse(String to, String from, String content){
		StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(content);  
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");  
        return sb.toString();  
	}
	
	/**图文消息 */
	protected String fromartNewResponse(String to, String from,List<WxNewsReplyDto> list){
		StringBuilder sb = new StringBuilder();  
        Date date = new Date();  
		sb.append("<xml>");  
		sb.append("<ToUserName><![CDATA[" + to + "]]></ToUserName>");  
		sb.append("<FromUserName><![CDATA[" + from + "]]></FromUserName>");  
		sb.append("<CreateTime>" + date.getTime() + "</CreateTime>");  
		sb.append("<MsgType><![CDATA[news]]></MsgType>");  
		sb.append("<ArticleCount>" + list.size() + "</ArticleCount>");  
		sb.append("<Articles>");  
		
		for(WxNewsReplyDto dto : list){
			sb.append("<item>");  
			sb.append("<Title><![CDATA[" + dto.getTitle() + "]]></Title> ");  
			sb.append("<Description><![CDATA[" + dto.getDescription() + "]]></Description>");  
			sb.append("<PicUrl><![CDATA[" + dto.getPicUrl() + "]]></PicUrl>");  
			sb.append("<Url><![CDATA[" + dto.getUrl() + "]]></Url>");  
			sb.append("</item>");  
		}
		
		sb.append("</Articles>");  
		sb.append("</xml> ");
		return sb.toString();  
	}
	
	
}
