package com.tmall.mailNo;

import java.util.List;

import com.tmall.mailNo.impl.RegProcess;

public class MainApp {

	public static void main(String[] args) {
		ProcessMailNo pm = new RegProcess();
		
		String mailNo = "718927796207" ; //中通快递 
		List<String> resultList = pm.process(mailNo);
		System.out.println(mailNo + ":" + resultList);
		
		
		String mailNo01 = "666075258172" ; //天天快递
		List<String> resultList01 = pm.process(mailNo01);
		
		System.out.println(mailNo01 + ":" + resultList01);
	}
}
