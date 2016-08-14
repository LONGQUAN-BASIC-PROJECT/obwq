package com.desksoft.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.PlanDetail;
import com.desksoft.entity.mapper.PlanDetailMapper;


@Repository(value="planDetailDao")
public class PlanDetailDao extends BaseDao {

	@Inject
	private PlanDetailMapper  planDetailMapper ;
	
	public void insert(PlanDetail planDetail){
		planDetailMapper.insert(planDetail);
	}
	
	public void update (PlanDetail planDetail){
		planDetailMapper.updateByPrimaryKeySelective(planDetail);
	}
	
	public List<PlanDetail> selectBypId(String pId){
		return planDetailMapper.selectBypId(pId);
	}

	public void delete(String pId) {
		planDetailMapper.delete(pId);
	}

	
	public void deleteByPrimaryKey(String pId) {
		planDetailMapper.deleteByPrimaryKey(pId);
	}

	
	public  List<PlanDetail>  reloadHotData(String pId) {
		return planDetailMapper.reloadHotData(pId);
	}
	
	
	
}
