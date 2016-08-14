package com.desksoft.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	/**
	 * 成功信息
	 * @return
	 */
	public  Map<String, Object> successMessage(){
		StringBuffer a = new StringBuffer();
		a.append("asdfa");
		return Message(1, "操作成功") ;
	}
	
	/**
	 * 失败信息
	 * @return
	 */
	public  Map<String, Object> failMessage(){
		return Message(0, "操作失败") ;
	}
	
	private  Map<String, Object> Message(Integer status , String message){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("messge", message);
		return map ;
	}
}
