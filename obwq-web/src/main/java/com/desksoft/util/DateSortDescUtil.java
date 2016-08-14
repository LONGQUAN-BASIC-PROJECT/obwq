package com.desksoft.util;

import java.util.Comparator;

import com.desksoft.common.Sort;

public class DateSortDescUtil implements Comparator<Sort> {


	public int compare(Sort o1, Sort o2) {
		try {
			if(o1.getGmtModify().getTime() < o2.getGmtModify().getTime()){
				return 1 ;
			}
			return -1;
		}catch (Exception e) {

		}
		return -1;
	}
}
