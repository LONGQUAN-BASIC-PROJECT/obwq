package com.desksoft.vo;

import com.desksoft.entity.Entity;

public class WebAddressVo implements Entity {

	private static final long serialVersionUID = -3025042132464255180L;

	private String id ;
	
	private String name;
	
	private String fatherId; 
	
	private Integer sequence;
	
	private String uri ;
	
	private Integer depth ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}
}
