package com.desksoft.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desksoft.common.BaseService;
import com.desksoft.common.WebContextUtil;

/**
 * 首页
 * @author forever
 *
 */
@Controller
@RequestMapping("/opterate/object")
public class CommonController  extends BaseController{

	@RequestMapping(value="/ajax/delete",method=RequestMethod.POST)
	@ResponseBody
	public Object delete(HttpServletRequest request){
		Map<String, String[]> map = request.getParameterMap();
		BaseService base = WebContextUtil.getBean(map.get("serviceImpl")[0]+"");
		Integer count =  base.delete(map.get("id")[0]+"");
		if(count != 0){
			return this.successMessage();
		}else{
			return this.failMessage();
		}
	}
	
	@RequestMapping(value="/ajax/edit",method=RequestMethod.POST)
	@ResponseBody
	public Object edit(HttpServletRequest request ,Map<String, String> map){
		/*
		BaseService base = WebContextUtil.getBean(map.get("serviceImpl"));
		Integer count =  base.edit(map);
		if(count != 0){
			return this.successMessage();
		}else{
			return this.failMessage();
		}*/
		return null ;
	}
	
	/**
	 * 更新顺序
	 * @param request
	 * @param list
	 * @return
	 */
	@RequestMapping(value="/ajax/updateSequ",method=RequestMethod.POST)
	@ResponseBody
	public Object updateSequ(HttpServletRequest request){
		Map<String, String[]> map = request.getParameterMap();
		String idList = request.getParameter("idlist");
		String[] idArray = idList.split(",");
		List<Map<String, Object>> listSequ = new ArrayList<Map<String,Object>>();
		Integer index = 1 ;
		for(String str : idArray){
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("sequence", index++);
			tempMap.put("id", str);
			tempMap.put("modifydate", new Date());
			listSequ.add(tempMap);
		}
		BaseService base = WebContextUtil.getBean(map.get("serviceImpl")[0]);
		Integer count =  base.updateSequ(listSequ);
		if(count != 0){
			return this.successMessage();
		}else{
			return this.failMessage();
		}
	}
}
