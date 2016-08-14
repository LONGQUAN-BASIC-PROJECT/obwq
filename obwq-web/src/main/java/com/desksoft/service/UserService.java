package com.desksoft.service;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.obwq.util.MD5;

import com.desksoft.dao.UserDao;
import com.desksoft.entity.User;

/**
 * @author forever
 *
 */
@Service(value="userService")
public class UserService /*extends BaseServvice*/ {

	@Autowired
	private UserDao  userDao;
	
	public User getUserById(String userId){
		return userDao.getUserById(userId);
	}
	
	public User insertFromMobile(Integer userType , String ourUserId,String nickName,String userPicture){
		User user = userDao.getUserByOutId(userType,ourUserId);
		if(user != null){
			//TODO这里应该更新一下modify时间
			User u = new User() ;
			u.setUserId(user.getUserId());
			u.setGmtModifidy(new Date());
			if(StringUtils.isNotEmpty(userPicture)){
				u.setUserPicture(userPicture);
			}
			this.updateUser(u);//更新登陆时间 
			return user ;//返回数据
		}
		//登录
		
		MD5 m = new MD5();
		String md5String =  m.getMD5ofStr(ourUserId + "_" +userType );
		
		user = new User();
		user.setNickName(nickName);
		user.setUserId(md5String);
		user.setGmtCreate(new Date());
		user.setGmtModifidy(new Date());
		user.setUserType(userType);
		user.setOutUserId(ourUserId);
		user.setUserPicture(userPicture);
		userDao.insert(user);
		return user ;
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
}
