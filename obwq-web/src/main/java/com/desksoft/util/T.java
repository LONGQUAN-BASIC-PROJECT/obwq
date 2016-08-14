package com.desksoft.util;

import org.apache.commons.lang.StringUtils;

public class T {

	
	public static void main(String[] args) {
		System.out.println(ChineseNameTest("械在厅要要"));
	}
	
	
	static boolean ChineseNameTest(String nickName) {
		 if(StringUtils.isBlank(nickName)){
			 return false;
		 }
		 
		 if(!(nickName.length() > 1 && nickName.length() < 5)){
			 return false;
		 }
		 
		 if(!nickName.matches("[\u4e00-\u9fa5]{2,4}")){
			 return false;
		 }
		 return true ;
	}
}
