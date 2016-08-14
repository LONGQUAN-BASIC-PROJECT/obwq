package com.desksoft.entity;

import java.io.Serializable;

/**
 * 点赞
 * @author guoxing.zgx
 *
 */
public class Praise implements  Serializable {

	private static final long serialVersionUID = 8294906075064629239L;

	
	private String rId;
	
	private String uId;
	
	private String pdId ;

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getPdId() {
		return pdId;
	}

	public void setPdId(String pdId) {
		this.pdId = pdId;
	}
	
	
	
}
