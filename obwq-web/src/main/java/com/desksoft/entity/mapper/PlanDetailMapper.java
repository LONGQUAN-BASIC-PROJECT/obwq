package com.desksoft.entity.mapper;

import java.util.List;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.PlanDetail;

public interface PlanDetailMapper extends SqlMapper {

	public void insert(PlanDetail plan) ;
	
	public List<PlanDetail> selectBypId(String pId) ;

	public void updateByPrimaryKeySelective(PlanDetail planDetail);

	public void delete(String pId);

	public  List<PlanDetail>  reloadHotData(String pId);

	public void deleteByPrimaryKey(String pdId);
	
}
