package com.desksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.obwq.entity.Agroup;

import com.desksoft.dao.AgroupDao;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author forever
 */
@Service(value = "agroupService")
public class AgroupService {

    private ConcurrentHashMap<Long,Agroup> map = new ConcurrentHashMap<Long, Agroup>();

    @Autowired
    private AgroupDao agroupDao;

    public Agroup selectById(Long gid) {
        Agroup agroup = map.get(gid);
        if(agroup != null){
            return agroup;
        }
        agroup =  agroupDao.selectById(gid);
        if(agroup != null){
            map.put(gid,agroup);
            return agroup;
        }
        return null;
    }


    public List<Agroup> selectAllByType() {
        return agroupDao.selectAllByType();
    }


    public void updateByPrimaryKeySelective(Agroup agroup){
        agroupDao.updateByPrimaryKeySelective(agroup);
    }


}
