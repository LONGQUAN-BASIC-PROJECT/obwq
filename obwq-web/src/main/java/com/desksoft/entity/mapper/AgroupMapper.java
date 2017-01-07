package com.desksoft.entity.mapper;

import cn.obwq.entity.Agroup;

import com.desksoft.dao.SqlMapper;

public interface AgroupMapper  extends SqlMapper  {

    Agroup selectByPrimaryKey(Long gid);

    void updateByPrimaryKeySelective(Agroup agroup) ;
    
	void insert(Agroup agroup);

}