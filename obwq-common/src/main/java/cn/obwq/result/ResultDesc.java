package cn.obwq.result;

public enum ResultDesc {

	RESULT_NULL("001" , "结果为空"),
	
	NET_WORK_ERROR("002" , "网络连接失败"),
	
	REQUEST_ERROR("003" , "请求失败")
	
	;
	
	
	private String code ;
	
	private String desc ;
	
	ResultDesc(String code ,String desc ){
		this.code = code ;
		this.desc = desc ;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
