package com.tmall.mailNo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.tmall.mailNo.MailReg;
import com.tmall.mailNo.ProcessMailNo;

public class RegProcess implements ProcessMailNo {

	public List<String> process(String mailNo) {
		List<String> tpCodeList = new ArrayList<String>();
		for(Entry<String, String> en : MailReg.getMailMap().entrySet()){
			Pattern   p   =   Pattern.compile(en.getValue());  
			if(p.matcher(mailNo).find()){
				tpCodeList.add(en.getKey());
			}
		}
		
		return tpCodeList;
	}

}
