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
import com.desksoft.entity.WebAddress;
import com.desksoft.service.WebAddressService;

/**
 * 首页
 * @author forever
 *
 */
@Controller
@RequestMapping("/opterate/webAddress")
public class WebAddressController extends BaseController {

	@Autowired
	WebAddressService  webAddressService ;
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add_init",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		return "/manager/webAddress/add.jsp";
	}
	
	/**
	 * 添加网站
	 * @param request
	 * @param webAddress
	 * @return
	 */
	@RequestMapping(value="/ajax/add",method=RequestMethod.POST)
	@ResponseBody
	public Object add(HttpServletRequest request, WebAddress webAddress){
		webAddress.setEntryId(UUIDGenerator.get());
		webAddress.setCreatDate(new Date());
		webAddress.setModifyDate(new Date());
		webAddress.setIsDeleted(0);
		Integer count = webAddressService.insertWebAddress(webAddress);
		if(count != 0)
			return this.successMessage();
		else {
			return this.failMessage();
		}
	}
	
	
	/**
	 * 大类ID
	 * @param request
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/ajax/findByParentId",method=RequestMethod.POST)
	public String findByParentId(HttpServletRequest request , String categoryId){
		List<WebAddress> list =  webAddressService.findByParentId(categoryId);
		request.setAttribute("webAddressList", list);
		return  "/manager/ajaxreturn/webaddress_list.jsp";
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ajax/findCommonWebAddress",method=RequestMethod.POST)
	public String findCommonWebAddress(HttpServletRequest request){
		List<WebAddress> list = webAddressService.findCommonWebAddress();
		request.setAttribute("commonWebAddress", list);
		return  "/manager/ajaxreturn/comm_web_address_list.jsp";
	}
}
