package com.desksoft.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.Evaluate;
import com.desksoft.entity.mapper.EvaluateMapper;


@Repository(value="evaluateDao")
public class EvaluateDao extends BaseDao {

	@Inject
	private EvaluateMapper  evaluateMapper ;
	
	public void insert(Evaluate evaluate){
		evaluateMapper.insert(evaluate);
	}

	public List<Evaluate> queryEevalute(String pdId) {
		return evaluateMapper.queryEevalute(pdId);
	}
	

}
