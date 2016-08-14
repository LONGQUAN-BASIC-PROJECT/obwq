package com.desksoft.entity.mapper;

import java.util.Map;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.User;

public interface UserMapper  extends SqlMapper  {

    User selectByPrimaryKey(String userId);

    void updateByPrimaryKeySelective(User user) ;
    
	User getUserByOutId(Map<String, Object> param);

	void insert(User user);

	void updateUser(Map<String, String> param);
}