package com.desksoft.common.constants;

public enum UserEume {
	
	QZONE(1,"qzone","QQ空间");

	private Integer id ;
	
	private String type ;
	
	private String name ;
	
	UserEume(int id ,String type , String name){
		this.id = id ;
		this.type = type ;
		this.name = name ;
	}

	public Integer getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	
}
