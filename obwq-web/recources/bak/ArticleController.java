package com.desksoft.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author forever
 *
 */
@Controller
public class ArticleController extends BaseController {
	
	@RequestMapping(value="/frt/add_article",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		return "/frt/add_article.jsp" ;
	}
	
}
