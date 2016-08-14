package com.desksoft.vo;

import com.desksoft.entity.Entity;


public class ArticleVo implements Entity {

	private static final long serialVersionUID = -1474572741541440446L;
	/**
	 * 类型ID
	 */
	private String articleTypeId;
	/**
	 * 文章ID
	 */
	private String articleId;

	/**
	 * 是否显示部分内部
	 */
	private Boolean isCupOf;
	
	
	public String getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(String articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Boolean getIsCupOf() {
		return isCupOf;
	}

	public void setIsCupOf(Boolean isCupOf) {
		this.isCupOf = isCupOf;
	}
}
