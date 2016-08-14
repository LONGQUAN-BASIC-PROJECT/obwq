package com.desksoft.entity;

import java.io.Serializable;
import java.util.Date;


public class Comment  implements Serializable {

	private static final long serialVersionUID = 7158541620276920660L;

	private String cId ; 
	
	private String userId ; 
	
	private String desc ;
	
	private Date gmtCreate ;
	
	private Date gmtModifidy ;

	public String getcId() {
		return cId;
	}

	public String getUserId() {
		return userId;
	}

	public String getDesc() {
		return desc;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public Date getGmtModifidy() {
		return gmtModifidy;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public void setGmtModifidy(Date gmtModifidy) {
		this.gmtModifidy = gmtModifidy;
	}

	
}