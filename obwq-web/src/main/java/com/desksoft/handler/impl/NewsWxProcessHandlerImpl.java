package com.desksoft.handler.impl;

import java.util.ArrayList;
import java.util.List;

import com.desksoft.entity.ReceiveXmlEntity;
import com.desksoft.entity.dto.WxNewsReplyDto;
import com.desksoft.handler.WxProcessHandler;

@Deprecated
public class NewsWxProcessHandlerImpl extends WxProcessHandler {

	public String handler(ReceiveXmlEntity xmlEntity) {
		List<WxNewsReplyDto> list = new ArrayList<WxNewsReplyDto>();
		WxNewsReplyDto dto = new WxNewsReplyDto();
		dto.setTitle("开源中国");
		dto.setDescription("开源中国，集合这方面开源信息。");
		dto.setPicUrl("http://static.oschina.net/uploads/space/2015/1208/181536_uGxW_2377895.png");
		dto.setUrl("http://m.oschina.net/news/70294/sui-mobile-0-6-2");
		list.add(dto);
		
		
		return  fromartNewResponse(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), list);
	}

}
