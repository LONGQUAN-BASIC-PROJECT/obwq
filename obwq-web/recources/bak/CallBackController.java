package com.desksoft.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author forever
 *
 */
@Controller
public class CallBackController extends BaseController {
	
	@RequestMapping(value="/qq/call_back",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		Map map = request.getParameterMap();
		System.out.println(map);
		return "/frt/qq_back.jsp" ;
	}
}
