package com.desksoft.entity;

import java.io.Serializable;
import java.util.Date;

import com.desksoft.common.Sort;

public class Plan implements Sort , Serializable {

	private static final long serialVersionUID = -8257417763043289908L;
	
	private String pId ;
	
	private String userId ;
	
	private String name ;
	
	private String descr ;
	
	private Integer flag ;
	
	private Integer praiseCount ;
	
	private Integer evaluateCount ;
	
	private Integer detailCount ;
	
	private String imageMain ;
	
	private Date gmtCreate ;
	
	private Date gmtModify ;

	public String getpId() {
		return pId;
	}

	public String getName() {
		return name;
	}

	public String getDescr() {
		return descr;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getUserId() {
		return userId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Integer getEvaluateCount() {
		return evaluateCount;
	}

	public void setEvaluateCount(Integer evaluateCount) {
		this.evaluateCount = evaluateCount;
	}

	public Integer getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Integer detailCount) {
		this.detailCount = detailCount;
	}

	public String getImageMain() {
		return imageMain;
	}

	public void setImageMain(String imageMain) {
		this.imageMain = imageMain;
	}

}
