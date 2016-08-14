package com.desksoft.entity;

import java.io.Serializable;
import java.util.Date;

import com.desksoft.common.Sort;

public class PlanDetail implements Sort , Serializable {

	private static final long serialVersionUID = -8422466478069713730L;

	private String pdId ;
	
	private String pId ;
	
	private String xx ;
	
	private String yy ;
	
	private Integer praiseCount ;
	
	private Integer evaluateCount ;
	
	private String content ;
	
	private String pictureUrl ;
	
	private Date gmtCreate ;
	
	private Date gmtModify ;

	public String getPdId() {
		return pdId;
	}

	public String getpId() {
		return pId;
	}

	public String getXx() {
		return xx;
	}

	public String getYy() {
		return yy;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setPdId(String pdId) {
		this.pdId = pdId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public void setYy(String yy) {
		this.yy = yy;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
