package com.desksoft.entity;

import java.io.Serializable;
import java.util.Date;


public class Categorydetail implements Serializable {
	
	   
	private static final long serialVersionUID = 5764392065649515076L;

	private String id ;
	
	private String name ;
	
	private String doTypeKey ;
	
	private String doTypeValue ;
	
	private Integer xseqt ;
	
	private String buttonType ;
	
	private String cgId ;
	
	private Date gmtCreate ;
	
	private Date gmtModify ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoTypeKey() {
		return doTypeKey;
	}

	public void setDoTypeKey(String doTypeKey) {
		this.doTypeKey = doTypeKey;
	}

	public String getDoTypeValue() {
		return doTypeValue;
	}

	public void setDoTypeValue(String doTypeValue) {
		this.doTypeValue = doTypeValue;
	}

	public Integer getXseqt() {
		return xseqt;
	}

	public void setXseqt(Integer xseqt) {
		this.xseqt = xseqt;
	}


	public String getCgId() {
		return cgId;
	}

	public void setCgId(String cgId) {
		this.cgId = cgId;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	
}