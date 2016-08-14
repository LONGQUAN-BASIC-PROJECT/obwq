package com.desksoft.entity.sql;

/**
 * 封装Sql对象
 * @author yangcd
 */
public class Sql {
	private String id;

	private String remark;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
