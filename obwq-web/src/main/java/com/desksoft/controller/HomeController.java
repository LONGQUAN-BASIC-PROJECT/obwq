package com.desksoft.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.desksoft.common.LoadWebAddressFromXml;
import com.desksoft.entity.xml.XmlBaseData;


/**
 *
 * @author forever
 */
@Controller
public class HomeController {
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
	//	new LoadWebAddressFromXml().init();
		XmlBaseData xmlBase = LoadWebAddressFromXml.getBaseXmlData() ;
		request.setAttribute("baseData", xmlBase);
		return "/index.jsp" ;
	}
}
