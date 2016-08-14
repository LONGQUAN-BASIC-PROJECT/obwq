package com.desksoft.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class PlanDto implements Serializable {

	private static final long serialVersionUID = 6197762120025151664L;

	private String userId ;
	
	private String nickName ;
	
	private String pId ;
	
	private String name ;
	
	private Date gmtCreate ;
	
	private String descr ;
	
	private Integer praiseCount ;
	
	private Integer evaluateCount ;
	
	private Integer detailCount ;
	
	private String imageMain ;
	
	private String userPicture ;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
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

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

}
