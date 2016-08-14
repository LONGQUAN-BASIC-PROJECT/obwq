package com.desksoft.entity.mapper;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.Praise;

public interface PraiseMapper  extends SqlMapper  {
	
	public void insert(Praise praise) ;
}
