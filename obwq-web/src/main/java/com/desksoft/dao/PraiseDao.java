package com.desksoft.dao;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.Praise;
import com.desksoft.entity.mapper.PraiseMapper;


@Repository(value="praiseDao")
public class PraiseDao extends BaseDao {

	@Inject
	private PraiseMapper  praiseMapper ;
	
	public void insert(Praise praise){
		praiseMapper.insert(praise);
	}
	

}
