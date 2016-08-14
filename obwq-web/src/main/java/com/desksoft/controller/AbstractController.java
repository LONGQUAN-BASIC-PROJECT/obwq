package com.desksoft.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.desksoft.common.constants.UserEume;

public abstract class AbstractController {
	public static Logger loger = LoggerFactory.getLogger(AbstractController.class);
	protected int getUserEume(String userType){
		if(StringUtils.isBlank(userType)){
			return 0;
		}
		UserEume ue[] =	UserEume.values();
		for(UserEume u : ue){
			if(u.getType().equals(userType)){
				return u.getId();
			}
		}
		return 0 ;
	}
}
