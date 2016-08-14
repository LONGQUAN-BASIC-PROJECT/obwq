package com.desksoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desksoft.dao.EvaluateDao;
import com.desksoft.dao.PlanDao;
import com.desksoft.dao.PlanDetailDao;
import com.desksoft.entity.Evaluate;
import com.desksoft.entity.Plan;
import com.desksoft.entity.PlanDetail;

/**
 * @author forever
 *
 */
@Service(value="planDetailService")
public class PlanDetailService /*extends BaseServvice*/ {

	
	@Autowired
	private PlanDetailDao planDetailDao ;
	
	@Autowired
	private EvaluateDao evaluateDao ;
	
	@Autowired
	private PlanDao planDao ;
	
	
	public void insert(PlanDetail planDetail){
		planDetailDao.insert(planDetail);
	}
	
	public List<PlanDetail> selectBypId(String pId){
		return planDetailDao.selectBypId(pId);
	}
	
	
	public void insert(Evaluate eval){
		evaluateDao.insert(eval);
		
		//更明细
		PlanDetail planDetail = new PlanDetail() ;
		planDetail.setPdId(eval.getPdId());
		planDetail.setEvaluateCount(1);
		planDetailDao.update(planDetail) ;
		
		//更新总的
		Plan plan = new Plan() ;
		plan.setEvaluateCount(1);
		plan.setpId(eval.getpId());
		planDao.updateByPrimaryKeySelective(plan);
		
	}

	
	public List<Evaluate> querySubmitNote(String pdId) {
		return evaluateDao.queryEevalute(pdId);
	}
	
	public void update (PlanDetail planDetail){
		planDetailDao.update(planDetail);
	}
	
	
	public void delete(String pId){
		planDetailDao.delete(pId);
	}

	public void deleteByPrimaryKey(String pdId){
		planDetailDao.deleteByPrimaryKey(pdId);
	}

	
	
	
	public List<PlanDetail> reloadHotData(String pId) {
		return planDetailDao.reloadHotData(pId) ;
	}

	
}
