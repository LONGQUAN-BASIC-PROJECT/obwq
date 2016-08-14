package com.desksoft.test;

public class Ts {

	
	public static void main(String[] args) {
		String str = "sdf" ; 
		
		Long tc = 22l;
		
		t(str,tc);
	}
	
	
	public  static void t(Object ...obj){
		System.out.println(obj.length);
		
		System.out.println(obj);
	}
}
