package com.desksoft.controller.dingdong;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.desksoft.entity.Category;
import com.desksoft.entity.Categorydetail;
import com.desksoft.entity.dto.Service;
import com.desksoft.service.CategoryService;
import com.desksoft.util.CategoryUtil;

@Controller
@RequestMapping("/dingdong")
public class DingIndex {
 
	static Map<String,Map<String,List<Service>>> allData = new HashMap<String, Map<String,List<Service>>>();

	@Autowired
	private CategoryService categoryService ;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		return "/ding/index.jsp";
	}

	@ResponseBody
	@RequestMapping(value = "/queryCategory", method = RequestMethod.POST)
	public Object queryCategory(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Category> cateList = categoryService.queryCategory(map);
		map.put("data", cateList);
		map.put("success", true);
		map.put("totalRows", 50);
		map.put("curPage", 1);

		return map ;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryCategoryDetail", method = RequestMethod.POST)
	public Object queryCategoryDetail(HttpServletRequest request,String phone) {
		List<Categorydetail> cdlist =  categoryService.queryCategoryDetail(phone);
		Map<String, List<Service>> resultMap = CategoryUtil.converData(cdlist);
		return resultMap ;
	}
	
	
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public String addCategory(HttpServletRequest request) {
		return "/ding/add_category.jsp";
	}
	
	
	@RequestMapping(value = "/addCategoryDetail", method = RequestMethod.GET)
	public String addCategoryDetail(HttpServletRequest request,String id,String phone) {
		request.setAttribute("id", id);
		request.setAttribute("phone", phone);
		
		List<Categorydetail> cdlist =  categoryService.queryCategoryDetail(phone);
		Map<String, List<Service>> resultMap = CategoryUtil.converData(cdlist);
		request.setAttribute("resultMap", resultMap);

		return "/ding/add_category_detail.jsp";
	}
	 
	 
	@ResponseBody
	@RequestMapping(value = "/saveCategoryDatail", method = RequestMethod.POST)
	public Object saveCategoryDatail(HttpServletRequest request,String pid , String  name,String doTypeKey,String  doTypeValue ,Integer xseqt ,String buttonType) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", false);
		
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(pid) || StringUtils.isEmpty(doTypeKey) 
				|| StringUtils.isEmpty(doTypeValue)   || StringUtils.isEmpty(buttonType)){
			result.put("success", false);
			result.put("desc", "数据不能为空");
			return result ;
		}
		
		try{
			
			categoryService.insertCategoryDetail(pid, name, doTypeKey, doTypeValue, xseqt, buttonType);
			result.put("success", true);

		}catch (DuplicateKeyException e) {
			result.put("success", false);
			result.put("desc", "相同位置，相同的顺序，不有重复的数据");
		}
		catch (Exception e) {
			result.put("success", false);
			result.put("desc", "系统异常");
		}
		return result;
	}
	
	
	
	 
	@ResponseBody
	@RequestMapping(value = "/updateCategoryDatail", method = RequestMethod.POST)
	public Object updateCategoryDatail(HttpServletRequest request,String id , String  name,String doTypeKey,String  doTypeValue ,Integer xseqt ,String buttonType) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", false);
		
		if(StringUtils.isEmpty(name)   || StringUtils.isEmpty(id) || StringUtils.isEmpty(doTypeKey) 
				|| StringUtils.isEmpty(doTypeValue)   || StringUtils.isEmpty(buttonType)){
			result.put("success", false);
			result.put("desc", "数据不能为空");
			return result ;
		}
		
		try{
			
			categoryService.updateCategoryDetail(id, name, doTypeKey, doTypeValue, xseqt, buttonType);
			result.put("success", true);

		}catch (DuplicateKeyException e) {
			result.put("success", false);
			result.put("desc", "相同位置，相同的顺序，不有重复的数据");
		}
		catch (Exception e) {
			result.put("success", false);
			result.put("desc", "系统异常");
		}
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
	public Object saveCategory(HttpServletRequest request,String  telphone,String desc) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", false);
		
		if(StringUtils.isEmpty(telphone) || StringUtils.isEmpty(desc)){
			result.put("success", false);
			result.put("desc", "数据不能为空");
			return result ;
		}
		
		try{
			
			categoryService.insertCategory(telphone, desc);
			result.put("success", true);

		}catch (DuplicateKeyException e) {
			result.put("success", false);
			result.put("desc", "存在的号码("+ telphone + ")");
		}
		catch (Exception e) {
			result.put("success", false);
			result.put("desc", "系统异常");
		}
		return result;
	}
	
	
	
	@RequestMapping(value = "/query_phone_data", method = RequestMethod.GET)
	public void queryPhoneData(HttpServletRequest request,HttpServletResponse response,String telphone) throws Exception {
		Map<String,List<Service>> map = allData.get(telphone);
		String str = JSONObject.toJSONString(map);
		
		response.setCharacterEncoding("utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.println(str);  
	}
	


}
