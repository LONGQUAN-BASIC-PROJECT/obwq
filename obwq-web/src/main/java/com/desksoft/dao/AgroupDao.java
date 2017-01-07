package com.desksoft.dao;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import cn.obwq.entity.Agroup;

import com.desksoft.common.BaseDao;
import com.desksoft.entity.mapper.AgroupMapper;


@Repository(value="agroupDao")
public class AgroupDao extends BaseDao {

	@Inject
	private AgroupMapper  agroupMapper ;
	

    public Agroup selectById(Long gid){
    	return agroupMapper.selectByPrimaryKey(gid);
    }

}
