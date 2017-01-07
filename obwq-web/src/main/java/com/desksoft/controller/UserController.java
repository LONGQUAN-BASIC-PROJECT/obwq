package com.desksoft.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.obwq.entity.User;
import cn.obwq.result.SingleResult;

import com.desksoft.service.UserService;


/**
 * @author forever
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {
	
	@Autowired
	UserService userService ;
	
	/**
	 * 只返回userId，而不是对应的开放平台的ID，因为考虑到后期会推出自己的帐号体系
	 * @param request
	 * @param userType
	 * @param outUserId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Object registser(HttpServletRequest request,String userType,String outUserId,String nickName,String userPicture){
		SingleResult<String> result = new SingleResult<String>();  
		int type  = super.getUserEume(userType);
		if(type == 0 || StringUtils.isBlank(outUserId)){
			result.setSuccess(Boolean.FALSE);
			return result ;  
		}
		if(StringUtils.isEmpty(nickName)){
			nickName = "空值" ;
		}
		User user = userService.insertFromMobile(type, outUserId,nickName,userPicture);
		result.setResult(user.getUserId());
		return result ;
	}
	
	/**
	 * 只返回userId，而不是对应的开放平台的ID，因为考虑到后期会推出自己的帐号体系
	 * @param request
	 * @param userType
	 * @param outUserId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/register_map",method=RequestMethod.POST)
	public Object registserMap(HttpServletRequest request,String userType,String outUserId,String nickName,String userPicture){
		SingleResult<Map<String,String>> result = new SingleResult<Map<String,String>>();  
		int type  = super.getUserEume(userType);
		if(type == 0 || StringUtils.isBlank(outUserId)){
			result.setSuccess(Boolean.FALSE);
			return result ;  
		}
		if(StringUtils.isEmpty(nickName)){
			nickName = "空值" ;
		}
		
		
		User user = userService.insertFromMobile(type, outUserId,nickName,userPicture);
		//结果
		Map<String,String> userMap = new HashMap<String,String>();
		userMap.put("userId", user.getUserId());
		userMap.put("nickName",user.getNickName());
		result.setResult(userMap);
		return result ;
	}
	
	
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Object login	(HttpServletRequest request,String userMail ,String userPwd) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("login", "true");
		map.put("userMail", userMail);
		map.put("userPwd", userPwd);
		ObjectMapper o = new ObjectMapper();
		System.out.println(o.writeValueAsString(map));
		return 	o.writeValueAsString(map);
	}
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update_user",method=RequestMethod.POST)
	@ResponseBody
	public Object updateUser(HttpServletRequest request,String userId ,String email,String nickName) throws Exception{
		SingleResult<String> result = new SingleResult<String>();
		try {
			if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(email)){
				result.setSuccess(Boolean.FALSE);
				return result ;
			}
			
			User user = new User();
			user.setUserId(userId);
			user.setEmail(email);
			user.setNickName(nickName);
			
			
			userService.updateUser(user);
		}catch (Exception e) {
			loger.error("updateUser@UserController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result;
	}
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/query_user_info",method=RequestMethod.GET)
	@ResponseBody
	public Object queryUserInfo(HttpServletRequest request,String userId) throws Exception{
		SingleResult<User> result = new SingleResult<User>();
		try {
			if(StringUtils.isEmpty(userId)){
				result.setSuccess(Boolean.FALSE);
				return result ;
			}
			
			User user = userService.getUserById(userId);
			result.setResult(user);
		}catch (Exception e) {
			loger.error("updateUser@UserController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result;
	}
}
