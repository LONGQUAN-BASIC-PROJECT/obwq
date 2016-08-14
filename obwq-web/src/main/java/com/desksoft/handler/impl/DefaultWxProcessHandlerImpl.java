package com.desksoft.handler.impl;

import cn.obwq.constants.ApplicationConstants;

import com.desksoft.entity.ReceiveXmlEntity;
import com.desksoft.handler.WxProcessHandler;

public class DefaultWxProcessHandlerImpl extends WxProcessHandler {

	public String handler(ReceiveXmlEntity xmlEntity) {
		return formartTextResponse(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), ApplicationConstants.WX_DEFAULT_REPLY);
	}

}
