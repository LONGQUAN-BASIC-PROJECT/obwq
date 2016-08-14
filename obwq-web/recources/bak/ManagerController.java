package com.desksoft.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 * @author forever
 *
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		return "/manager/main.jsp";
	}
	
	
	@RequestMapping(value="top",method=RequestMethod.GET)
	public String top(HttpServletRequest request){
		return "/manager/top.jsp";
	}
	
	@RequestMapping(value="left",method=RequestMethod.GET)
	public String left(HttpServletRequest request){
		return "/manager/left.jsp";
	}
	
	
	@RequestMapping(value="right",method=RequestMethod.GET)
	public String right(HttpServletRequest request){
		return "/manager/right.jsp";
	}
	
	
}
