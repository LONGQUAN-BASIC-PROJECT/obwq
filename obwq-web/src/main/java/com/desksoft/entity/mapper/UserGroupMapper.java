package com.desksoft.entity.mapper;

import cn.obwq.entity.UserGroup;

import com.desksoft.dao.SqlMapper;

public interface UserGroupMapper  extends SqlMapper  {

    UserGroup selectByPrimaryKey(Long id);

	void insert(UserGroup userGroup);

}