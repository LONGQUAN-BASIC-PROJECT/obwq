package com.desksoft.craw;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.desksoft.common.constants.AgroupEnum;
import com.desksoft.entity.Task;
import cn.obwq.dto.SechCrawDto;
import com.desksoft.util.CollectionUtil;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class LoadCrawQunen extends QuartzJobBean {

	public static Logger loger = LoggerFactory.getLogger(LoadCrawQunen.class);

	private LinkedBlockingQueue<SechCrawDto> taskQue = new LinkedBlockingQueue<SechCrawDto>(200) ; 
	
	@Autowired
	private CrawService crawService;


	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		initTask();
	}

	public void initTask(){
		if(taskQue.size() != 0){
			loger.error("wran@task_has_not_finish,taskLength=" + taskQue.size()+",task_has_not_started");
			return ;
		}
		//resetTask();
		List<Task> taskList = fetchTask() ;
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
			}
		}
		
	}



	private void resetTask(){
		taskQue.clear();
	}
	
	
	public List<Task> fetchTask(){
		return new ArrayList<Task>();
	}
	
	private void converTaskToQue(List<Task> taskList) {
		if(CollectionUtil.isEmpty(taskList)){
			return ;
		}
		
		for(Task task : taskList){
			SechCrawDto sto = new SechCrawDto();
			AgroupEnum agm = AgroupEnum.getByType(task.getType());
			if(agm == null){
				continue ;
			}
			sto.setGroupType(task.getType());
			sto.setGroupId(agm.getId());
			sto.setDesc(agm.getDesc());
			sto.setUrl(task.getUrl());
			
			taskQue.add(sto);
		}
	}
	
	
	
}
