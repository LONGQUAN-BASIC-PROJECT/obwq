package com.desksoft.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author forever
 */
@Controller
public class WanController {
	@RequestMapping(value="/wan",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		return "/wan/wan.jsp" ;
	} 
	
	
	@RequestMapping(value="/wan/photo_list",method=RequestMethod.GET)
	public String findDetail(HttpServletRequest request){
		return "/wan/photo_list.jsp" ;
	}
	
	@RequestMapping(value="/t1",method=RequestMethod.GET)
	public String t1(HttpServletRequest request){
		return "/wan/test/wan.jsp" ;
	} 
	
}
