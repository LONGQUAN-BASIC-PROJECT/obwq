package com.desksoft.entity.dto;

import java.io.Serializable;

/**
 * 爬取的对象
 * @author guoxing.zgx
 *
 */
public class SechCrawDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4603847842751475734L;

	private Long groupId ;
	
	private String groupType ;
	
	private String desc ;
	
	private String url ;

	private Integer retryCount = 0 ;
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	
	
	
}
