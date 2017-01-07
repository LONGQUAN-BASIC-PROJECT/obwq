package cn.obwq.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseDo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8293452821118070030L;
	
	
	private Date gmtCreate ;
	
	private Date getModify ;

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGetModify() {
		return getModify;
	}

	public void setGetModify(Date getModify) {
		this.getModify = getModify;
	}


	
	
}
