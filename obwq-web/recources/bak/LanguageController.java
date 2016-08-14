package com.desksoft.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desksoft.common.UUIDGenerator;
import com.desksoft.entity.Language;
import com.desksoft.service.LanguageService;

/**
 * @author forever
 *
 */
@Controller
@RequestMapping("/opterate/language")
public class LanguageController extends BaseController {

	
	@Autowired
	LanguageService  languageService;
	
	/**
	 * 保存
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ajax/add",method=RequestMethod.POST)
	@ResponseBody
	public Object add(HttpServletRequest request,Language language){
		language.setLanguageid(UUIDGenerator.get());
		language.setCreatdate(new Date());
		language.setModifydate(new Date());
		language.setIsdeleted(0);
		Integer count = languageService.insertSelective(language);
		if(count == 0){
			return this.failMessage();
		}
		return this.successMessage();
	}
}
