package com.desksoft.entity.mapper;

import java.util.List;
import java.util.Map;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.Plan;
import com.desksoft.entity.dto.PlanDto;

public interface PlanMapper  extends SqlMapper  {

	public void insert(Plan plan) ;
	
	public List<PlanDto> queryPlanByUserId(String userId) ;
	
	public List<PlanDto> queryMyselfPlan(String userId) ;
	
	public List<PlanDto> queryCollectPlan(String userId) ;
	
	public List<PlanDto> queryPublicPlan() ;

	public Plan queryPlanById(String planId);
	
	public PlanDto queryPlanDescById(Map<String,String> param) ;
	
	public void updateByPrimaryKeySelective(Plan plan);

	public List<Plan>  queryPlanByNameId(Map<String, String> param);

	public List<PlanDto> queryPublicNewPlan();
}
