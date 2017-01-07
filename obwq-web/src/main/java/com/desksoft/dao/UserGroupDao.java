package com.desksoft.dao;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import cn.obwq.entity.UserGroup;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.mapper.UserGroupMapper;


@Repository(value="userGroupDao")
public class UserGroupDao extends BaseDao {

	@Inject
	private UserGroupMapper  userGroupMapper ;
	

	public UserGroup getUserById(Long id){
		return userGroupMapper.selectByPrimaryKey(id);
	}
	

	public void insert(UserGroup userGroup) {
		userGroupMapper.insert(userGroup);
	}
	
	
}
