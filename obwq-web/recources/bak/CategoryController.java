package com.desksoft.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desksoft.common.UUIDGenerator;
import com.desksoft.entity.Category;
import com.desksoft.entity.Language;
import com.desksoft.service.CategoryService;
import com.desksoft.service.LanguageService;

/**
 * @author forever
 *
 */
@Controller
@RequestMapping("/opterate/category")
public class CategoryController extends BaseController {

	@Autowired
	LanguageService  languageService;
	
	@Autowired
	CategoryService categoryService ;
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="add_init",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		List<Language> list = languageService.findAll();
		request.setAttribute("lanagerList", list);
		return "/manager/category/add.jsp";
	}
	
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ajax/add",method=RequestMethod.POST)
	@ResponseBody
	public Object add(HttpServletRequest request , Category category){
		category.setCategoryid(UUIDGenerator.get());
		category.setCreatdate(new Date());
		category.setModifydate(new Date());
		category.setIsdeleted(0);
		Integer count = categoryService.insertSelective(category);
		if(count == 0){
			return this.failMessage();
		}else{
			return this.successMessage();
		}
	}
	
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ajax/findByLanguageId",method=RequestMethod.POST)
	public String findByLanguageId(HttpServletRequest request,String languageId){
		List<Category> list = categoryService.findByLanguageId(languageId);
		request.setAttribute("categoryList", list);
		return "/manager/ajaxreturn/big_categoryList.jsp";
	}
}
