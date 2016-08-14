package com.desksoft.entity.xml;

import java.util.ArrayList;
import java.util.Date;

public class XmlCategory {

	private String categoryid;

    private String categoryname;

    private Integer sequence;

    private String type;

    private String comment;

    private Date creatdate;

    private Date modifydate;

    private Integer isdeleted;

    private ArrayList<XmlWebAddress> webAddress ;
    
	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatdate() {
		return creatdate;
	}

	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	public ArrayList<XmlWebAddress> getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(ArrayList<XmlWebAddress> webAddress) {
		this.webAddress = webAddress;
	}


}