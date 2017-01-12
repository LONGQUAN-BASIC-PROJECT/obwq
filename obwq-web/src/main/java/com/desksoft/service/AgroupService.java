package com.desksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.obwq.entity.Agroup;

import com.desksoft.dao.AgroupDao;

import java.util.List;

/**
 * @author forever
 */
@Service(value = "agroupService")
public class AgroupService {

    @Autowired
    private AgroupDao agroupDao;

    public Agroup selectById(Long gid) {
        return agroupDao.selectById(gid);
    }


    public List<Agroup> selectAllByType() {
        return agroupDao.selectAllByType();
    }


    public void updateByPrimaryKeySelective(Agroup agroup){
        agroupDao.updateByPrimaryKeySelective(agroup);
    }


}
