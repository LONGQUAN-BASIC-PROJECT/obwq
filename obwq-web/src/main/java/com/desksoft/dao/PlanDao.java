package com.desksoft.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.Plan;
import com.desksoft.entity.dto.PlanDto;
import com.desksoft.entity.mapper.PlanMapper;


@Repository(value="planDao")
public class PlanDao extends BaseDao {

	@Inject
	private PlanMapper  planMapper ;
	
	public void insert(Plan plan){
		planMapper.insert(plan);
	}
	
	public List<PlanDto> queryPlanByUserId(String userId){
		return planMapper.queryPlanByUserId(userId);
	}
	
	public List<PlanDto> queryMyselfPlan(String userId){
		return planMapper.queryMyselfPlan(userId);
	}
	
	public List<PlanDto> queryCollectPlan(String userId){
		return planMapper.queryCollectPlan(userId);
	}
	
	public List<PlanDto> queryPublicPlan(){
		return planMapper.queryPublicPlan();
	}
	
	public List<PlanDto> queryNewPublicPlan(){
		return planMapper.queryPublicNewPlan();
	}

	public Plan queryPlanById(String planId) {
		return planMapper.queryPlanById(planId);
	}
	
	public PlanDto queryPlanDescById(Map<String,String> param) {
		return planMapper.queryPlanDescById(param);
	}

	public void updateByPrimaryKeySelective(Plan plan){
		planMapper.updateByPrimaryKeySelective(plan);
	}
	
	public List<Plan>  queryPlanByNameId(Map<String, String> param){
		return planMapper.queryPlanByNameId(param);
	}
	
}
