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
			 
			 Service s = new Service(cdetail.getName(),cdetail.getDoTypeKey(),cdetail.getDoTypeValue(),cdetail.getXseqt());
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
		
		
		return buttonData ;
	}
}
