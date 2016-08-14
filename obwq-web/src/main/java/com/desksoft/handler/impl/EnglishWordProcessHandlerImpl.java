package com.desksoft.handler.impl;

import java.util.List;

import com.desksoft.common.LoadEnFromJson;
import com.desksoft.entity.ReceiveXmlEntity;
import com.desksoft.entity.xml.en.EnglishWord;
import com.desksoft.entity.xml.en.Example;
import com.desksoft.handler.WxProcessHandler;

public class EnglishWordProcessHandlerImpl extends WxProcessHandler {

	public String handler(ReceiveXmlEntity xmlEntity) {
		
		EnglishWord en = LoadEnFromJson.getRandEn();
		if(en == null){
			return formartTextResponse(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), "词库还没有准备好");
		}
		
		StringBuilder sb = new StringBuilder() ;
		sb.append("单词:").append(en.getTname()).append("(共").append(LoadEnFromJson.getEnCout()).append("个)").append("\n");
		sb.append("词义:").append(en.getComment()).append("\n\n");
//		sb.append("音标:").append(en.getYinbiao() == null ?"暂无":en.getYinbiao()).append("\n\n");
		
		List<Example> listEx = en.getExamples();
		if(listEx != null && listEx.size() > 0){
			for(Example ex : listEx){
				sb.append(ex.getEn()).append("\n");
				sb.append(ex.getCn()).append("\n\n");;
			}
		}
		
		return formartTextResponse(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), sb.toString());
	}

}
