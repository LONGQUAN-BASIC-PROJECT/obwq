package com.desksoft.entity;

import cn.obwq.entity.BaseDo;

public class Task extends BaseDo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 513918887726520623L;

	private String type ;
	
	private String url ;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
