package com.desksoft.entity;

import java.io.Serializable;
import java.util.Date;

import com.desksoft.common.Sort;

/**
 * 评论
 * @author guoxing.zgx
 *
 */
public class Evaluate implements Serializable , Sort {

	private static final long serialVersionUID = -275566021444791900L;

	private String eId;
	
	private String pId ;
	
	private String pdId;
	
	private String uId;
	
	private String  nickName ;
	
	private String figter ;  //QQ头像链接
	
	private String content ;

	private Date gmtCreate ;
	
	private Date gmtModify ;

	public String geteId() {
		return eId;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}

	public String getPdId() {
		return pdId;
	}

	public void setPdId(String pdId) {
		this.pdId = pdId;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFigter() {
		return figter;
	}

	public void setFigter(String figter) {
		this.figter = figter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}
	
}
