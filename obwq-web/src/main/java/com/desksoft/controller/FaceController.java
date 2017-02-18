package com.desksoft.controller;

import com.desksoft.common.LoadWebAddressFromXml;
import com.desksoft.entity.xml.XmlBaseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author forever
 */
@Controller
@RequestMapping("/face")
public class FaceController {

	private static Logger loger = LoggerFactory.getLogger(ArticleController.class);

	@RequestMapping(value="/add_article",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
	//	new LoadWebAddressFromXml().init();
		XmlBaseData xmlBase = LoadWebAddressFromXml.getBaseXmlData() ;
		request.setAttribute("baseData", xmlBase);
		return "/face/add_article.jsp" ;
	}


}
