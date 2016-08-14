package com.desksoft.entity.mapper;

import java.util.List;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.Evaluate;

public interface EvaluateMapper  extends SqlMapper  {
	
	public void insert(Evaluate evaluate) ;

	public List<Evaluate> queryEevalute(String pdId);
}
