package cn.obwq.constants;

public enum DATE_FORMAT {
	
	yyyy_mm_dd_SPLIT("yyyy/MM/dd"),
	yyyy_mm_dd("yyyy-MM-dd"),
	ddmmyyyy("ddMMyyyy"),
	mm_dd("MM-dd"),
	mm_dd_hh("MM-dd HH:mm"),
	yyyy_mm_dd_hh_mm_ss("yyyy-MM-dd HH:mm:ss"),
	mm_dd_yyyy_hh_mm_ss("MM/dd/yyyy HH:mm:ss"),
	yyyy_p_mm_p_dd_hh_mm_ss("yyyy:MM:dd HH:mm:ss");
	
	private String type ;


	//02/12/2017 09:20:12
	
	DATE_FORMAT(String type){
		this.type = type ;
	}

	public String getType() {
		return type;
	}
	
	
}