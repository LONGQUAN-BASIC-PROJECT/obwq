package com.desksoft.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import cn.obwq.entity.User;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.mapper.UserMapper;


@Repository(value="userDao")
public class UserDao extends BaseDao {

	@Inject
	private UserMapper  userMapper ;
	

	public User getUserById(String userId){
		return userMapper.selectByPrimaryKey(userId);
	}
	
	public User getUserByOutId(Integer userType , String userId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userType", userType);
		param.put("outUserId", userId);
		return userMapper.getUserByOutId(param);
	}

	public void insert(User user) {
		userMapper.insert(user);
	}

	public void updateUser(Map<String, String> param) {
		userMapper.updateUser(param);
	}

	public void updateUser(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}
	
}
