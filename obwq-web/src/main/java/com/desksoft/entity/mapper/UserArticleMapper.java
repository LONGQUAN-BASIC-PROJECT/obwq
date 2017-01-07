package com.desksoft.entity.mapper;

import cn.obwq.entity.UserArticle;

import com.desksoft.dao.SqlMapper;

public interface UserArticleMapper  extends SqlMapper  {

    UserArticle selectByPrimaryKey(Long id);

	void insert(UserArticle userArticle);

}