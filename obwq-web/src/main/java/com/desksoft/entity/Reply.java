package com.desksoft.entity;

import java.util.Date;

public class Reply implements Entity {

	private static final long serialVersionUID = 7162815027455518300L;
	/**
	 * 用户ID
	 */
	private String userId ;
	/**
	 * 回复ID
	 */
	private String replyId ;
	/**
	 * 文章ID
	 */
	private String articleId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 时间
	 */
	private Date gmtDate ;
	/**
	 * 是否删除
	 */
	private Integer isDelete ;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
}
