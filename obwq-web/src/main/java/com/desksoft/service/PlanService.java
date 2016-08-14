package com.desksoft.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.obwq.util.MD5;
import cn.obwq.util.UUIDGenerator;

import com.desksoft.dao.PlanDao;
import com.desksoft.dao.PlanDetailDao;
import com.desksoft.dao.PraiseDao;
import com.desksoft.dao.UserPlanDao;
import com.desksoft.entity.Plan;
import com.desksoft.entity.PlanDetail;
import com.desksoft.entity.Praise;
import com.desksoft.entity.UserPlan;
import com.desksoft.entity.dto.PlanDto;

/**
 * @author forever
 *
 */
@Service(value="planService")
public class PlanService /*extends BaseServvice*/ implements InitializingBean {

	private static Logger loger = LoggerFactory.getLogger(PlanService.class);
	
	@Autowired
	private PlanDao planDao ;
	
	@Autowired
	private UserPlanDao userPlanDao ;
	
	@Autowired
	private PraiseDao PraiseDao ;
	
	@Autowired
	private PlanDetailDao planDetailDao ;
	
	public List<PlanDto> listDto ;
	
	public Plan insert(String userId ,String name ,String desc,Integer flag){
		//计划
		Plan plan = new Plan() ;
		plan.setpId(UUIDGenerator.get());
		plan.setUserId(userId);
		plan.setDescr(desc);
		plan.setFlag(flag);
		plan.setName(name);
		plan.setGmtCreate(new Date());
		plan.setGmtModify(new Date());
		planDao.insert(plan);
		
		UserPlan userPlan = new UserPlan() ;
		userPlan.setUpId(UUIDGenerator.get());
		userPlan.setuId(userId);
		userPlan.setGmtCreate(new Date());
		userPlan.setGmtModify(new Date());
		userPlan.setpId(plan.getpId());
		this.insertUserPlan(userPlan);
		
		return plan ;
	}
	
	public void insertUserPlan(UserPlan userPlan ){
		userPlanDao.insert(userPlan);
	}
	
	public Plan insertDefault(String userId){
		//计划
		Plan plan = new Plan() ;
		plan.setpId(userId);
		plan.setUserId(userId);
		plan.setDescr("这是为用户创建默认游记");
		plan.setName("默认游记");
		plan.setFlag(-1);
		plan.setGmtCreate(new Date());
		plan.setGmtModify(new Date());
		planDao.insert(plan);
		
		UserPlan userPlan = new UserPlan() ;
		userPlan.setUpId(UUIDGenerator.get());
		userPlan.setuId(userId);
		userPlan.setGmtCreate(new Date());
		userPlan.setGmtModify(new Date());
		userPlan.setpId(plan.getpId());
		userPlanDao.insert(userPlan);
		
		return plan ;
	}
	
	/**
	 * 更新赞的数据
	 * @param userId
	 * @param planId
	 * @param pdId
	 */
	public void updatePlanPraise(String userId,String planId , String pdId){
		//1.插入数据
		MD5 md5 = new MD5();
		//插入数据，如果重复的话，会抛错
		Praise praise = new Praise();
		praise.setPdId(pdId);
		praise.setuId(userId);
		praise.setrId(md5.getMD5ofStr(userId+"_" + pdId));
		PraiseDao.insert(praise);
		//更明细
		PlanDetail planDetail = new PlanDetail() ;
		planDetail.setPdId(pdId);
		planDetail.setPraiseCount(1);
		planDetailDao.update(planDetail) ;

		//更新总的
		Plan plan = new Plan() ;
		plan.setPraiseCount(1);
		plan.setpId(planId);
		planDao.updateByPrimaryKeySelective(plan);
	}
	
	
	
	
	public Boolean deletePlanDetail(String planId,String pdId) {
		
		Plan tplan = this.queryPlanById(planId);
		if(tplan == null){
			return false ;
		}
		//更新订单数据
		Plan plan = new Plan() ;
		plan.setpId(tplan.getpId());
		plan.setGmtModify(new Date());
		plan.setDetailCount(-1);
		this.updateByPrimaryKeySelective(plan);
		
		planDetailDao.deleteByPrimaryKey(pdId);
		
		return true;
	}
	
	
	
	public List<PlanDto> queryPlanByUserId(String userId){
		return planDao.queryPlanByUserId(userId);
	}
	
	
	public List<PlanDto> queryMyselfPlan(String userId){
		return planDao.queryMyselfPlan(userId);
	}
	
	
	public List<PlanDto> queryCollectPlan(String userId){
		return planDao.queryCollectPlan(userId);
	}
	
	
	public List<PlanDto> queryPublicPlan(){
		return planDao.queryPublicPlan();
	}
	
	public List<PlanDto> queryNewPublicPlan(){
		if(listDto == null){
			listDto = planDao.queryNewPublicPlan();
		}
		return listDto ;
	}
	
	
	public Plan queryPlanById(String planId){
		return planDao.queryPlanById(planId);
	}
	
	public PlanDto queryPlanDescById(Map<String,String> param){
		return planDao.queryPlanDescById(param);
	}
	
	
	public UserPlan queryUserPlan(Map<String,String> param) {
		return userPlanDao.queryUserPlan(param);
	}
	
	
	public void deleteUserPlan(Map<String,String> param){
		 userPlanDao.deleteUserPlan(param);
	}
	
	public void updateByPrimaryKeySelective(Plan plan){
		planDao.updateByPrimaryKeySelective(plan);
	}
	
	public List<Plan> queryPlanByNameId(Map<String, String> param){
		return planDao.queryPlanByNameId(param);
	}
	
	
	private final Timer timer = new Timer();
	
	public void afterPropertiesSet() throws Exception {
		Long _one_min =  1 * 60 * 1000l ;
		timer.schedule(new TimerTask() {
			public void run() {
				listDto = null ;
			}
		}, _one_min, _one_min);
	}

}
