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
import com.desksoft.entity.Categorydetail;
import com.desksoft.service.CategoryDetailService;

/**
 * @author forever
 *
 */
@Controller
@RequestMapping("/opterate/categoryDetail")
public class CategoryDetailController extends BaseController {

	
	@Autowired
	CategoryDetailService categoryDetailService ;
	
	/**
	 * 大类ID
	 * @param request
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/ajax/findByParentId",method=RequestMethod.POST)
	public String findByParentId(HttpServletRequest request , String categoryId){
		List<Categorydetail> list =  categoryDetailService.findByParentId(categoryId);
		request.setAttribute("categorydetailList", list);
		return  "/manager/ajaxreturn/small_categoryList.jsp";
	}
	
	/**
	 * 大类ID
	 * @param request
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/ajax/add",method=RequestMethod.POST)
	@ResponseBody
	public Object addCategoryDetail(HttpServletRequest request , Categorydetail categorydetail){
		categorydetail.setCategorydetailid(UUIDGenerator.get());
		categorydetail.setIsdeleted(0);
		categorydetail.setCreatdate(new Date());
		categorydetail.setModifydate(new Date());
		Integer count  =  categoryDetailService.insertSelective(categorydetail);
		if(count == 0 ){
			return this.failMessage();
		}else {
			return this.successMessage();
		}
	}
}
