package com.desksoft.entity.mapper;

import java.util.Map;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.UserPlan;

public interface UserPlanMapper  extends SqlMapper  {

	public void insert(UserPlan userPlan) ;
	
	public UserPlan queryUserPlan(Map<String,String> param) ;

	public void deleteUserPlan(Map<String, String> param);
}
