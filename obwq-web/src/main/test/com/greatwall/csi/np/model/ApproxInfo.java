package com.greatwall.csi.np.model;

import java.util.Map;

public class ApproxInfo {
     
     public int getBirth() {
		return birth;
	}


	public void setBirth(int birth) {
		this.birth = birth;
	}


	public String getIdNo() {
		return idNo;
	}


	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}


	public Map<String, ApproxInfo> getMap() {
		return map;
	}


	public void setMap(Map<String, ApproxInfo> map) {
		this.map = map;
	}


	private int birth ;
     
     private String idNo;
     
     
     private Map<String,ApproxInfo> map ;
}
