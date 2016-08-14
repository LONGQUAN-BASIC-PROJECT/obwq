package com.desksoft.entity;

import java.io.Serializable;
import java.util.Date;

public class UserPlan implements Serializable {

	private static final long serialVersionUID = -8781691886184549232L;
	
	private String upId ;
	
	private String uId ;
	
	private String pId ;
	
	private Date gmtCreate ;
	
	private Date gmtModify ;

	public String getUpId() {
		return upId;
	}

	public String getuId() {
		return uId;
	}

	public String getpId() {
		return pId;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setUpId(String upId) {
		this.upId = upId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}
}
