package com.desksoft.controller.dingdong;

import java.io.PrintWriter;
import java.util.ArrayList;
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
	public String addCategoryDetail(HttpServletRequest request,String id) {
		request.setAttribute("id", id);
		return "/ding/add_category_detail.jsp";
	}
	 
	 
	@ResponseBody
	@RequestMapping(value = "/saveCategoryDatail", method = RequestMethod.POST)
	public Object saveCategoryDatail(HttpServletRequest request,String pid , String  name,String doTypeKey,String  doTypeValue ,Integer xseqt ,String buttonType) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", false);
		
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(doTypeKey) 
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
	

	
	static Map<String, List<Service>> buttonData_95555 = new HashMap<String, List<Service>>() {
		private static final long serialVersionUID = 1L;
		{
			put("btn_1", new ArrayList<Service>() {
				private static final long serialVersionUID = 1L;

				{
					add(new Service("查看账单", Service.DO_TYPE_KEY_MSGREPLY, "#ZD"));
					add(new Service("查看余额", Service.DO_TYPE_KEY_MSGREPLY, "#1212"));
				}
			});

			put("btn_2", new ArrayList<Service>() {
				private static final long serialVersionUID = 1L;
				{
					add(new Service("糗百", "openUrl",
							"http://www.qiushibaike.com/"));
					add(new Service("新浪", "openUrl", "http://www.sina.com.cn/"));
				}
			});
			put("btn_3", new ArrayList<Service>() {
				private static final long serialVersionUID = 1L;
				{
					add(new Service("关于", "openActive",
							"com.andbase.login.AboutActivity"));
					add(new Service("支持我", "openActive",
							"com.andbase.activity.SupportMeActivity"));
				}
			});
			put("btn_4", new ArrayList<Service>() {
				private static final long serialVersionUID = 1L;
				{
					add(new Service("反馈", "openActive",
							"com.andbase.activity.SupportMeActivity"));
				}
			});
		}
	};

	static Map<String, List<Service>> buttonData_10010 = new HashMap<String, List<Service>>() {
		private static final long serialVersionUID = 1L;

		{
			put("btn_1", new ArrayList<Service>() {
				private static final long serialVersionUID = 1L;

				{
					add(new Service("当月话费", Service.DO_TYPE_KEY_MSGREPLY, "101"));
					add(new Service("话费明细", Service.DO_TYPE_KEY_MSGREPLY,
							"HFMX"));
					add(new Service("帐户余额", Service.DO_TYPE_KEY_MSGREPLY, "102"));

				}
			});

			put("btn_2", new ArrayList<Service>() {
				private static final long serialVersionUID = 1L;
				{
					add(new Service("糗百", "openUrl",
							"http://www.qiushibaike.com/"));
					add(new Service("新浪", "openUrl", "http://www.sina.com.cn/"));
				}
			});
			put("btn_3", new ArrayList<Service>() {
				private static final long serialVersionUID = 1L;
				{
					add(new Service("关于", "openActive",
							"com.andbase.login.AboutActivity"));
					add(new Service("支持我", "openActive",
							"com.andbase.activity.SupportMeActivity"));
				}
			});
			put("btn_4", new ArrayList<Service>() {
				private static final long serialVersionUID = 1L;
				{
					add(new Service("反馈", "openActive",
							"com.andbase.activity.SupportMeActivity"));
				}
			});
		}
	};

	static {
		allData.put("10010", buttonData_10010);
		allData.put("95555", buttonData_95555);

	}

}
