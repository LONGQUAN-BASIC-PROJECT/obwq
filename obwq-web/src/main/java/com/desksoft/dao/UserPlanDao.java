package com.desksoft.dao;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.UserPlan;
import com.desksoft.entity.mapper.UserPlanMapper;


@Repository(value="userPlanDao")
public class UserPlanDao extends BaseDao {

	@Inject
	private UserPlanMapper  userPlanMapper ;
	
	public void insert(UserPlan plan){
		userPlanMapper.insert(plan);
	}

	public UserPlan queryUserPlan(Map<String,String> param) {
		return userPlanMapper.queryUserPlan(param);
	}

	public void deleteUserPlan(Map<String,String> param){
		userPlanMapper.deleteUserPlan(param);
	}
	
}
