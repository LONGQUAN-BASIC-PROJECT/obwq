package com.desksoft.entity;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 文章
 * @author forever
 *
 */
@XStreamAlias("Article")
public class Article implements Entity {

	private static final long serialVersionUID = -7959191186567798346L;
	/**
	 * 文章ID
	 */
	private String articleId ;
	/**
	 * 文章类型ID
	 */
	private String articleTypeId ;
	/**
	 * 用户ID
	 */
	private String userId ;
	/**
	 * 图片url
	 */
	private String pictureUrl ;
	/**
	 * 标题
	 */
	private String title ;
	/**
	 * 内容
	 */
	private String content ;
	
	private String keyName ;
	
	/**
	 * 时间
	 */
	private Date gmtDate ;
	/**
	 * 是否删除
	 */
	private Integer isDelete ;
	
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(String articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getGmtDate() {
		return gmtDate;
	}
	public void setGmtDate(Date gmtDate) {
		this.gmtDate = gmtDate;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
}
