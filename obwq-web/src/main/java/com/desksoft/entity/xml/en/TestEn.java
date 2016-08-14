package com.desksoft.entity.xml.en;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class TestEn {

	public static void main(String[] args) throws Exception {
		List<EnglishWord> list = new ArrayList<EnglishWord>();
		List<String> cc = readFile();

		EnglishWord en = new EnglishWord() ;
		
		List<Example> eamList = new ArrayList<Example>();
		
		Example a1 = new Example();
		Example a2 = new Example();
		Example a3 = new Example();
		
		
		int index = 1 ;
		for(String str : cc){
			if(str.equals(",")){
				index = 1 ;
				if(a1.getCn() != null){
					eamList.add(a1);
				}
				if(a2.getCn() != null){
					eamList.add(a2);
				}
				if(a3.getCn() != null){
					eamList.add(a3);
				}
				
				en.setExamples(eamList);
				
				list.add(en);
				
				
				a1 = new Example();
				a2 = new Example();
				a3 = new Example();
				eamList = new ArrayList<Example>();
				en = new EnglishWord() ;
				continue ;
			}
			
			if(index == 1){
				en.setTname(str);
			}
			if(index == 2){
				en.setComment(str);
			}
			
			if(index == 3){
				a1.setEn(str);
			}
			if(index == 4){
				a1.setCn(str);
			}
			
			if(index == 5){
				a2.setEn(str);
			}
			if(index == 6){
				a2.setCn(str);
			}
			
			if(index == 7){
				a3.setEn(str);
			}
			if(index == 8){
				a3.setCn(str);
			}
			index++;
		}
		
		String str = JSONObject.toJSONString(list);
		System.out.println(str);
	}
	
	
	private static List<String> readFile() throws Exception{
		List<String> list = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		BufferedReader br = null;
		// br = new BufferedReader(new FileReader(file));
		br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("c:/c.txt")), "UTF-8"));
		String lineContent = null;
		while ((lineContent = br.readLine()) != null) {
			if(lineContent.length() ==0 ){
				list.add(",");
			}else{
				list.add(lineContent);
			}
		}
		return list;
	
	}
}
