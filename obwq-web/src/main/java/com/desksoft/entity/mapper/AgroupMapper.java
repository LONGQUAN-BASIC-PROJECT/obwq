package com.desksoft.entity.mapper;

import cn.obwq.entity.Agroup;

import com.desksoft.dao.SqlMapper;

import java.util.List;

public interface AgroupMapper  extends SqlMapper  {

    Agroup selectByPrimaryKey(Long gid);

    void updateByPrimaryKeySelective(Agroup agroup) ;
    
	void insert(Agroup agroup);


    List<Agroup> selectAllByType() ;

}