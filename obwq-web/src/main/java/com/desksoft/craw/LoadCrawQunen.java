package com.desksoft.craw;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import cn.obwq.constants.DATE_FORMAT;
import cn.obwq.entity.Agroup;
import cn.obwq.util.DateUtil;
import cn.obwq.util.StringUtils;
import com.desksoft.service.AgroupService;
import org.apache.commons.beanutils.BeanUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.desksoft.common.constants.AgroupEnum;
import cn.obwq.dto.SechCrawDto;
import com.desksoft.util.CollectionUtil;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class LoadCrawQunen extends QuartzJobBean {

	public static Logger loger = LoggerFactory.getLogger(LoadCrawQunen.class);

	private LinkedBlockingQueue<SechCrawDto> taskQue = new LinkedBlockingQueue<SechCrawDto>(200) ;
	
	@Autowired
	private CrawService crawService;

	@Autowired
	private AgroupService agroupService ;

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		initTask();
	}

	public void initTask(){
		if(taskQue.size() != 0){
			loger.error("wran@task_has_not_finish,taskLength=" + taskQue.size()+",task_has_not_started");
			return ;
		}
		List<Agroup> taskList = fetchTask() ;
		if(CollectionUtil.isEmpty(taskList)){
			loger.error("error@initTask,taskList_is_empty,task_has_not_started");
			return ;
		}
		
		converTaskToQue(taskList);
		consumerTask();
	}
	
	

	private void consumerTask() {
		while(!taskQue.isEmpty()){
			SechCrawDto cdto = 	taskQue.poll();
			if(cdto.getRetryCount() > 3){ // 默认重试3次
				loger.error("error@task_fail_over_3_times,give_up,dto=" + JSONObject.toJSONString(cdto));
				continue ;
			}
			Boolean flag = crawService.crawFromWeb(cdto);
			if(!flag){
				cdto.setRetryCount(cdto.getRetryCount() + 1) ;
				taskQue.add(cdto);
			}else {
				//更新上次爬取时间
				cdto.addFeature("last_craw_time", DateUtil.getDateString(new Date(), DATE_FORMAT.yyyy_mm_dd_hh_mm_ss));
				Agroup result = new Agroup();
				try {
					BeanUtils.copyProperties(result,cdto);
				}catch (Exception e){}

				//agroupService.update(result);
			}
		}
		
	}



	private void resetTask(){
		taskQue.clear();
	}
	
	
	public List<Agroup> fetchTask(){
		return new ArrayList<Agroup>();
	}
	
	private void converTaskToQue(List<Agroup> taskList) {
		if(CollectionUtil.isEmpty(taskList)){
			return ;
		}
		
		for(Agroup task : taskList){
			//判断两次爬取间隔
			//如果太近就不爬了
			String inter_time = task.getFeature("inter_time"); //每次爬取的秒数据
			if(StringUtils.isBlank(inter_time) || !StringUtils.isNumeric(inter_time)){ //没有间隔数据
				loger.error("error@converTaskToQue,task_has_none_inter_time,dto=" + JSONObject.toJSONString(task));
				continue;
			}
			long inter_time_lon = Long.valueOf(inter_time) ;


			String lastTime = task.getFeature("last_craw_time");
			if(StringUtils.isNotBlank(lastTime)){
				Date preTime = DateUtil.parseDate(lastTime, DATE_FORMAT.yyyy_mm_dd_hh_mm_ss);
				long  chaju = (new Date().getTime() - preTime.getTime()) / 1000 ;
				if(inter_time_lon > chaju){
					continue;//少于两者的数据
				}
			}
			SechCrawDto sto = new SechCrawDto();
			AgroupEnum agm = AgroupEnum.getByType(task.getName());
			if(agm == null){
				continue ;
			}
			try {
				BeanUtils.copyProperties(sto,task);
			}catch (Exception e){

			}

			taskQue.add(sto);
		}
	}
	
	
	
}
