package com.desksoft.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.desksoft.entity.Categorydetail;
import com.desksoft.entity.dto.Service;



public class CategoryUtil {

	public static Map<String, List<Service>> converData(List<Categorydetail> cdlist){
		Map<String, List<Service>> buttonData = new HashMap<String, List<Service>>() ;
		if(cdlist == null || cdlist.size() == 0){
			return buttonData ;
		}
		
		for(Categorydetail cdetail : cdlist){
			String key = cdetail.getButtonType() ;
			
			 List<Service> slist = buttonData.get(key);
			 if(slist == null || slist.size() == 0){
				 slist = new ArrayList<Service>();
			 }
			 
			 Service s = new Service(cdetail.getId() ,cdetail.getName(),cdetail.getDoTypeKey(),cdetail.getDoTypeValue(),cdetail.getXseqt(),cdetail.getButtonType());
			 slist.add(s);
			 
			 Collections.sort(slist, new Comparator<Service>() {
					public int compare(Service o1, Service o2) {
						if(o1.getXseqt() > o2.getXseqt()){
							return 1 ;
						}
						return -1 ;
					}
				});
			 
			 buttonData.put(key, slist);
		}
		
		
		List<Service> slist = new ArrayList<Service>();
		slist.add(new Service("shengq","服务号申请","openActive","com.test",1,"btn4"));
		slist.add(new Service("desc", "介\t\t\t\t\t绍","openActive","com.andbase.activity.AboutActivity",2,"btn4"));
		slist.add(new Service("tucao","吐\t\t\t\t\t槽","openActive","com.test",3,"btn4"));
		slist.add(new Service("suportme","支\t\t持\t\t我","openActive","com.andbase.activity.SupportMeActivity",4,"btn4"));
		buttonData.put("btn4", slist);
		
		
		List<Service> slist2 = new ArrayList<Service>();
		slist2.add(new Service("shekngq","开源中国","openUrl","http://m.oschina.net/",1,"btn3"));
		slist2.add(new Service("desc", "百度","openUrl","http://www.baidu.com",2,"btn3"));
		slist2.add(new Service("suportme","糗事百科","openUrl","http://www.qiushibaike.com/",3,"btn3"));
		buttonData.put("btn3", slist2);
		
		
		return buttonData ;
	}
}
