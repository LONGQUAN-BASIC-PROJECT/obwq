package com.tmall.mailNo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.desksoft.util.HttpClientUtil;
import com.tmall.mailNo.ProcessMailNo;
import com.tmall.mailNo.impl.inner.T;
import com.tmall.mailNo.impl.inner.T1;

public class KD001Process implements ProcessMailNo {

	private static String URL = "http://www.kuaidi100.com/autonumber/autoComNum?text=" ;

	private static Map<String, String> doRelation = new HashMap<String, String>();
	
	static {
		doRelation.put("zhongtong", "ZTO");
		doRelation.put("yuantong", "YTO");
		doRelation.put("shunfeng", "SF");
		doRelation.put("zengyisudi", "QRT");
		doRelation.put("quanfengkuaidi", "QFKD");
		doRelation.put("annengwuliu", "安能物流");
		doRelation.put("huitongkuaidi", "HTKY");
	
	}
	
	public List<String> process(String mailNo) {
		String url = URL + mailNo +"&rn=" + Math.random() ;
		String content = HttpClientUtil.getHttpContent(url) ;
		
		System.out.println(content);
		ObjectMapper object = new ObjectMapper();
		try {
			T t = object.readValue(content, T.class);
			if(t == null ||  t.getAuto() == null ||  t.getAuto().size() == 0){
				return null ;
			}
			
			List<String> resultList = new ArrayList<String>();
			
			List<T1> list = t.getAuto() ;
			for(T1 a : list){
				resultList.add(doRelation.get(a.getComCode()));
			}
			return resultList ;
			
		} catch (Exception e) {
		}
		return  null;
	}

	public static void main(String[] args) {
		List<String> list = new KD001Process().process("210736895302");
		System.out.println(list);
	}
	
}
