package com.desksoft.common;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.InitializingBean;

import cn.obwq.util.MD5;

import com.desksoft.entity.PlanDetail;
import com.desksoft.service.PlanDetailService;


public class ReloadHotData implements InitializingBean {
	
	
	String pId = "36f09946820d4886ae2c1670932f4cf7" ;
	
	private final Timer timer = new Timer();
	
	private Long _ten_min =  10 * 60 *1000l ;
	
//	private Long _ten_min =   60 *1000l ;
	
	private PlanDetailService planDetailService ;
	
	public void init(){
		startSchedule() ;
	}
	
	private void startSchedule(){
		timer.schedule(new TimerTask() {
			public void run() {
				planDetailService.delete(pId);
     			List<PlanDetail>  list = planDetailService.reloadHotData(pId);
     			
     			for(PlanDetail pd :list){
     				try{
     					pd.setPdId(new MD5().getMD5ofStr( pd.getPdId()) +  "_" + pd.getPdId());
     					planDetailService.insert(pd);
     				}catch(Exception e){}
     			}
			}
		}, 1000l, _ten_min);
	}
	

	public void afterPropertiesSet() throws Exception {
		init();
	}

	public PlanDetailService getPlanDetailService() {
		return planDetailService;
	}

	public void setPlanDetailService(PlanDetailService planDetailService) {
		this.planDetailService = planDetailService;
	}
	
}
