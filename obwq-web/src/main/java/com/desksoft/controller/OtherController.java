package com.desksoft.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.desksoft.service.LanguageService;


/**
 *
 * @author forever
 */
@Controller
@RequestMapping("/other")
public class OtherController {
	
	@Autowired
	LanguageService languageService ;
	
	@RequestMapping(value="/load_login",method=RequestMethod.POST)
	public String index(HttpServletRequest request){
		return "/other/a.html" ;
	}
	
	@RequestMapping(value="/e",method=RequestMethod.GET)
	public String e(HttpServletRequest request){
		try {
			languageService.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "/new/e.jsp" ;
	} 
	@RequestMapping(value="/g",method=RequestMethod.GET)
	public String g(HttpServletRequest request){
		return "/g/g.jsp" ;
	} 
	
	@RequestMapping(value="/cal",method=RequestMethod.GET)
	public String c(HttpServletRequest request){
		return "/other/cal.jsp" ;
	}
}
