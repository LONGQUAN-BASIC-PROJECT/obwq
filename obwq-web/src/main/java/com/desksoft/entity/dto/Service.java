package com.desksoft.entity.dto;

import java.io.Serializable;

public class Service implements Serializable {
	
	
	public static final String  DO_TYPE_KEY_MSGREPLY = "msgReply" ;
	
	public static final String  DO_TYPE_KEY_OPENURL= "openUrl" ;

	public static final String  DO_TYPE_KEY_OPENACTIVE= "openActive" ;

	
	
	private static final long serialVersionUID = -3605330930204787884L;

	private String name ;
	
	private String doTypeKey ;
	
	private String doTypeValue ;
	
	private Integer xseqt ;

	
	public Service(){}
	
	public Service(String n, String tk ,String tv){
		this.name = n ;
		this.doTypeKey = tk ;
		this.doTypeValue = tv ; 
	}
	
	public Service(String n, String tk ,String tv,Integer seq){
		this.name = n ;
		this.doTypeKey = tk ;
		this.doTypeValue = tv ; 
		this.xseqt = seq ;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoTypeKey() {
		return doTypeKey;
	}

	public void setDoTypeKey(String doTypeKey) {
		this.doTypeKey = doTypeKey;
	}

	public String getDoTypeValue() {
		return doTypeValue;
	}

	public void setDoTypeValue(String doTypeValue) {
		this.doTypeValue = doTypeValue;
	}

	public Integer getXseqt() {
		return xseqt;
	}

	public void setXseqt(Integer xseqt) {
		this.xseqt = xseqt;
	}
	
	
}
