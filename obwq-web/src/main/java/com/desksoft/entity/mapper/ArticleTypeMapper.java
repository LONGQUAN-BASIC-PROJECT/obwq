package com.desksoft.entity.mapper;

import java.util.List;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.ArticleType;


public interface ArticleTypeMapper extends SqlMapper {
	
	public List<ArticleType> selectAllItem();
	
}
