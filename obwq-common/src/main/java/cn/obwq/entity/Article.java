package cn.obwq.entity;


import cn.obwq.util.FeatureUtil;
import cn.obwq.util.StringUtils;

import java.util.Map;

public class Article extends  BaseDo {

	private static final long serialVersionUID = -2945100587470256594L;
	
	private Long id ; 

	private String thumbnail;

	private String title ;
	
	private String url ;

	private Long groupId ;
	
	private Long praiseCount ;
	
	private Long commentCount ;
	
	private Long collectionCount ;

	private String groupName ;

	private String groupUrl ;

	private String groupLogo ;

	/**
	 * 其它属性
	 */
	private String feature;

	/**
	 * 其它属性
	 */
	private Map<String,String> attr;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Long praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public Long getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Long collectionCount) {
		this.collectionCount = collectionCount;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupUrl() {
		return groupUrl;
	}

	public void setGroupUrl(String groupUrl) {
		this.groupUrl = groupUrl;
	}

	public String getGroupLogo() {
		return groupLogo;
	}

	public void setGroupLogo(String groupLogo) {
		this.groupLogo = groupLogo;
	}


	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}


	protected void initattrMap() {
		if (null == attr) {
			attr = FeatureUtil.initAttrMap(feature);
		}
	}

	public void addFeature(String name, String value){

		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
			initattrMap();
			attr.put(name, value);
			resetFeature();
		}
	}

	public String getFeature(String name){
		initattrMap();
		String value = attr.get(name);
		return value==null ? "" : value;
	}

	public boolean removeFeature(String name){
		initattrMap();
		boolean flag = false;
		if(attr.containsKey(name)){
			attr.remove(name);
			resetFeature();
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}

	private void resetFeature(){
		this.feature = FeatureUtil.resetAttrMap(attr);
	}


}
