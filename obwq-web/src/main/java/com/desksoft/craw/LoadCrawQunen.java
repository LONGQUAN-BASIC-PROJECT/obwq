package com.desksoft.craw;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import cn.obwq.constants.DATE_FORMAT;
import cn.obwq.entity.Agroup;
import cn.obwq.util.DateUtil;
import cn.obwq.util.StringUtils;
import com.desksoft.service.AgroupService;
import com.desksoft.util.WebContextUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.desksoft.common.constants.AgroupEnum;
import cn.obwq.dto.SechCrawDto;
import com.desksoft.util.CollectionUtil;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Service(value="loadCrawQunen")
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
		if(crawService == null){
			crawService = (CrawService)WebContextUtil.getApplicationContext().getBean("crawService");
		}

		if(agroupService == null){
			agroupService = (AgroupService)WebContextUtil.getApplicationContext().getBean("agroupService");
		}

		if(taskQue.size() != 0){
			loger.error("wran@task_has_not_finish,taskLength=" + taskQue.size()+",task_has_not_started");
			return ;
		}

		loger.error("start_to_craw_something");

		List<Agroup> taskList = fetchTask() ;
		if(CollectionUtil.isEmpty(taskList)){
			loger.error("error@initTask,taskList_is_empty,task_has_not_started");
			return ;
		}

		loger.error("info@has_task_to_od,task_size=" + taskList.size() );


		converTaskToQue(taskList);
		consumerTask();

		loger.error("end_to_craw_something");

	}
	
	

	private void consumerTask() {
		while(!taskQue.isEmpty()){
			SechCrawDto cdto = 	taskQue.poll();
			if(cdto.getRetryCount() > 3){ // 默认重试3次
				loger.error("error@task_fail_over_3_times,give_up,dto=" + JSONObject.toJSONString(cdto));
				continue ;
			}

			String name = cdto.getName();
			if(!name.equals("")){

			}

			loger.error("consumer_task,task=" + JSONObject.toJSONString(cdto));

			Boolean flag = crawService.crawFromWeb(cdto);
			if(flag == null){
				continue;
			}
			if(!flag){
				cdto.setRetryCount(cdto.getRetryCount() + 1) ;
				taskQue.add(cdto);
			}else  {
				//更新上次爬取时间
				cdto.addFeature("last_craw_time", DateUtil.getDateString(new Date(), DATE_FORMAT.yyyy_mm_dd_hh_mm_ss));
				Agroup result = new Agroup();
				try {
					BeanUtils.copyProperties(result,cdto);
				}catch (Exception e){}
				result.setGetModify(new Date());
				agroupService.updateByPrimaryKeySelective(result);
			}
		}
		
	}



	private void resetTask(){
		taskQue.clear();
	}
	
	
	public List<Agroup> fetchTask(){
		List<Agroup> list =  agroupService.selectAllByType();
		if (CollectionUtil.isEmpty(list)){
			return new ArrayList<Agroup>();
		}

		Iterator<Agroup> it =  list.iterator();

		while(it.hasNext()){
			Agroup ag = it.next();
			String featureName = ag.getFeature("inter_time");
			if(StringUtils.isBlank(featureName)){
				ag.addFeature("inter_time","1200"); //20分钟
			}

			//Test
			//if(!ag.getName().equals("MEI_ZI_TU")){
			//		it.remove();
			//}
			//test

		}


		return list ;

	}
	
	private void converTaskToQue(List<Agroup> taskList) {
		if(CollectionUtil.isEmpty(taskList)){
			return ;
		}

		for(Agroup task : taskList){
			//判断两次爬取间隔consumer_task
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

			String url = task.getUrl() ;
			if(url.indexOf(",") == -1){
				SechCrawDto sto = new SechCrawDto();
				sto.setId(task.getId());
				sto.setName(task.getName());
				sto.setDescr(task.getDescr());
				sto.setLogo(task.getLogo());
				sto.setAcount(task.getAcount());
				sto.setFeature(task.getFeature());
				sto.setType(task.getType());
				sto.setGmtCreate(task.getGmtCreate());
				sto.setGetModify(task.getGetModify());
				sto.setUrl(task.getUrl());
				taskQue.add(sto);
			}else {
				String[] uarr = url.split(",");
				for(String u : uarr){
					SechCrawDto sto = new SechCrawDto();
					sto.setId(task.getId());
					sto.setName(task.getName());
					sto.setDescr(task.getDescr());
					sto.setLogo(task.getLogo());
					sto.setAcount(task.getAcount());
					sto.setFeature(task.getFeature());
					sto.setType(task.getType());
					sto.setGmtCreate(task.getGmtCreate());
					sto.setGetModify(task.getGetModify());
					sto.setUrl(u);
					taskQue.add(sto);
				}
			}

		}
	}


}
