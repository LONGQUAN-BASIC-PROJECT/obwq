package com.desksoft.common.constants;

public enum AgroupEnum {

	
	ZHIHU(1l,"zhihu","知乎");

	private Long id ;
	
	private String name ;
	
	private String desc ;
	
	AgroupEnum(Long id ,String name , String desc){
		this.id = id ;
		this.name = name ;
		this.desc = desc ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static AgroupEnum getByType(String type){
		for(AgroupEnum an : AgroupEnum.values()){
			if(an.name.equals(type)){
				return an ;
			}
		}
		return null ;
	}
	
}
