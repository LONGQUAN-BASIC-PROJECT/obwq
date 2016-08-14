package cn.obwq.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.obwq.constants.DATE_FORMAT;

/**
 * 时间工具类
 * @author forever
 *
 */
public class DateUtil {

	/**
	 * 得到当前时间。
	 * 考虑到项目部署在美国，可能时间存在不一致性
	 * @return
	 */
	public static Date getCurrentDate(){
		return new Date();
	}
	
	public static String getDateString(Date date ,DATE_FORMAT df){
		SimpleDateFormat sdf = new SimpleDateFormat(df.getType());
		return sdf.format(date);
	}
	
	
	public static Date parseDate(String date ,DATE_FORMAT df){
		SimpleDateFormat sdf = new SimpleDateFormat(df.getType());
		try {
			return sdf.parse(date);
		} catch (Exception e) {
		}
		return null ;
	}
	
	public static String currentTimeString(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	public static void main(String[] args) {
//		String timeStr = DateUtil.getDateString(new Date(), DATE_FORMAT.yyyy_mm_dd);
//		System.out.println(timeStr);
		Date d =	DateUtil.parseDate("2015:09:03 10:49:12",DATE_FORMAT.yyyy_p_mm_p_dd_hh_mm_ss);
		
		String timeStr = DateUtil.getDateString(d, DATE_FORMAT.yyyy_mm_dd_hh_mm_ss);
		System.out.println(timeStr);
	}
	
}


