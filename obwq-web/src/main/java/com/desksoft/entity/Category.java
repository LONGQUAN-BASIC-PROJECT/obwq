package com.desksoft.entity;

import java.io.Serializable;
import java.util.Date;

import cn.obwq.constants.DATE_FORMAT;
import cn.obwq.util.DateUtil;

public class Category  implements Serializable {
	
	private static final long serialVersionUID = 7730746041130757175L;

	private String id ;
	
	private String phone ;
	
	private String descr ;
	
	private Date gmtCreate ;
	
	private Date gmtModify ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}
	
	public String getGmtCreateStr() {
		return DateUtil.getDateString(gmtCreate, DATE_FORMAT.yyyy_p_mm_p_dd_hh_mm_ss);
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}
	
	public String getGmtModifyStr() {
		return DateUtil.getDateString(gmtModify, DATE_FORMAT.yyyy_p_mm_p_dd_hh_mm_ss);
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}


}