package com.desksoft.entity;

import java.util.Date;

/**
 * 文章类型
 * @author forever
 *
 */
public class ArticleType implements Entity {

	private static final long serialVersionUID = -4376253178182300814L;
	/**
	 * 类型ID
	 */
	private String articleTypeId ;
	/**
	 * 类型名称
	 */
	private String articleTypeName ;
	/**
	 * 时间
	 */
	private Date gmtDate ;
	/**
	 * 是否删除
	 */
	private Integer isDelete ;
	public String getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(String articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	public String getArticleTypeName() {
		return articleTypeName;
	}
	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
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
}
