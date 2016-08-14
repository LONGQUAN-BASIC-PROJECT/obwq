package com.desksoft.util;

import javax.servlet.http.HttpServletRequest;

public class JspUtil {

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String basePath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		return basePath;
	}
}
