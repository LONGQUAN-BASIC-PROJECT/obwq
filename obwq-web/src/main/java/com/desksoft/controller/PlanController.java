package com.desksoft.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.obwq.constants.DATE_FORMAT;
import cn.obwq.result.SingleResult;
import cn.obwq.util.DateUtil;
import cn.obwq.util.UUIDGenerator;

import com.desksoft.entity.Evaluate;
import com.desksoft.entity.Plan;
import com.desksoft.entity.PlanDetail;
import com.desksoft.entity.UserPlan;
import com.desksoft.entity.dto.PlanDto;
import com.desksoft.service.PlanDetailService;
import com.desksoft.service.PlanService;
import com.desksoft.util.CheckMobileUtil;
import com.desksoft.util.DateSortDescUtil;
import com.desksoft.util.DateSortUtil;


/**
 * @author forever
 */
@Controller
@RequestMapping("/plan")
public class PlanController extends AbstractController {
	
	private static Logger loger = LoggerFactory.getLogger(PlanController.class);
	
	@Autowired
	private PlanService planService ;
	
	@Autowired
	private PlanDetailService planDetailService ;
	
	
	/**
	 * @param userId 这是userId也是默认计划的id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/get_plan_detail_desc",method=RequestMethod.POST)
	public Object getPlanDetail(HttpServletRequest request,String planId){
		SingleResult<Object> result = new SingleResult<Object>();
		if(StringUtils.isBlank(planId)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			Map<String,String> param = new HashMap<String,String>();
			param.put("planId", planId);
			PlanDto pdto = planService.queryPlanDescById(param);
			result.setResult(pdto);
		}catch (Exception e) {
			loger.error("getPlanDetail@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	
	/**
	 * @param userId 这是userId也是默认计划的id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add_and_get",method=RequestMethod.POST)
	public Object addDefaultPlan(HttpServletRequest request,String userId){
		SingleResult<Object> result = new SingleResult<Object>();
		if(StringUtils.isBlank(userId)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			Plan plan = planService.queryPlanById(userId);
			if(plan != null){
				result.setResult(plan);
				return result ;
			}
			plan = planService.insertDefault(userId);
			result.setResult(plan);
		}catch (Exception e) {
			loger.error("addPlan@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	/**
	 * 
	 * @param request
	 * @param userId
	 * @param name
	 * @param desc
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object addPlan(HttpServletRequest request,String userId,String name ,String desc,Integer flag){
		SingleResult<Object> result = new SingleResult<Object>();
		if(StringUtils.isBlank(userId) || StringUtils.isBlank(name) || StringUtils.isBlank(desc)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			//如果才接口，为-1
			if(flag == null){
				flag = -1 ;
			}
			
			Map<String,String> param = new HashMap<String,String>();
			param.put("userId", userId);
			param.put("name", name);
			List<Plan> planList = planService.queryPlanByNameId(param);
			if(planList != null && planList.size() > 0){
				result.setResult(planList.get(0).getpId());
				return result ;
			}
			
			Plan plan = planService.insert(userId, name, desc,flag);
			result.setResult(plan.getpId());
		}catch (Exception e) {
			loger.error("addPlan@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/update_plan",method=RequestMethod.POST)
	public Object updatePlan(HttpServletRequest request,String planId,Integer flag){
		SingleResult<Object> result = new SingleResult<Object>();
		if(StringUtils.isBlank(planId)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			
			Plan plan = new Plan();
			plan.setpId(planId);
			plan.setFlag(flag);
			planService.updateByPrimaryKeySelective(plan);
		}catch (Exception e) {
			loger.error("updatePlan@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/add_detail",method=RequestMethod.POST)
	public Object addPlanDetail(HttpServletRequest request,String xx , String yy ,String planId ,String pictureUrl,String gmtTime){
		SingleResult<Object> result = new SingleResult<Object>();
		if(StringUtils.isBlank(xx) || StringUtils.isBlank(yy) || StringUtils.isBlank(planId) || StringUtils.isBlank(pictureUrl)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			PlanDetail planDetail = new PlanDetail();
			planDetail.setPdId(UUIDGenerator.get());
			planDetail.setpId(planId);
			planDetail.setPictureUrl(pictureUrl);
			planDetail.setXx(xx);
			planDetail.setYy(yy);
			
			Date createDate = DateUtil.parseDate(gmtTime, DATE_FORMAT.yyyy_mm_dd_hh_mm_ss);
			if(createDate != null){
				planDetail.setGmtCreate(createDate);
				planDetail.setGmtModify(createDate);
			}else{
				planDetail.setGmtCreate(new Date());
				planDetail.setGmtModify(new Date());
			}
			
			planDetailService.insert(planDetail);
			
			
			Plan tplan = planService.queryPlanById(planId);
			
			//更新订单数据
			Plan plan = new Plan() ;
			plan.setpId(planId);
			plan.setGmtModify(new Date());
			plan.setDetailCount(1);
			if(StringUtils.isEmpty(tplan.getImageMain())){
				plan.setImageMain(pictureUrl);
			}
			planService.updateByPrimaryKeySelective(plan);
		}catch (Exception e) {
			loger.error("addPlanDetail@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/query_detail",method=RequestMethod.POST)
	public Object queryDetailBypId(HttpServletRequest request,String pId,String orderBy){
		SingleResult<List<Map<String,String>>> result = new SingleResult<List<Map<String,String>>>();
		if(StringUtils.isBlank(pId) ){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		
		if(StringUtils.isEmpty(orderBy)){
			orderBy = "asc" ;
		}
		
		try {
			List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
			List<PlanDetail>  detailList = planDetailService.selectBypId(pId);
			if("asc".equals(orderBy)){
				Collections.sort(detailList, new DateSortUtil());
			}else {
				Collections.sort(detailList, new DateSortDescUtil());
			}
			if(detailList != null && detailList.size() > 0){
				
				for(PlanDetail detail :  detailList){
					String timeYY = DateUtil.getDateString(detail.getGmtCreate(), DATE_FORMAT.yyyy_mm_dd_hh_mm_ss);
					Map<String,String> map = new HashMap<String, String>();
					map.put("xx", detail.getXx());
					map.put("yy", detail.getYy());
					map.put("pictureUrl", detail.getPictureUrl());
					map.put("pdId", detail.getPdId());
					map.put("pId", detail.getpId());
					map.put("pCount", detail.getPraiseCount()+"");
					map.put("eCount", detail.getEvaluateCount()+"");
					map.put("content", detail.getContent());
					map.put("time", timeYY);
					resultList.add(map);
				}
				result.setResult(resultList);
			}
			
		}catch (Exception e) {
			loger.error("queryDetailBypId@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	
	/**
	 * @param request
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/query_user_plan",method=RequestMethod.POST)
	public Object queryPlanList(HttpServletRequest request,String userId){
		SingleResult<List<Map<String,String>>> result = new SingleResult<List<Map<String,String>>>();
		if(StringUtils.isBlank(userId) ){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
			List<PlanDto>  planList = planService.queryPlanByUserId(userId);
			if(planList != null && planList.size() > 0){
				for(PlanDto detail :  planList){
					String timeStr = DateUtil.getDateString(detail.getGmtCreate(), DATE_FORMAT.mm_dd);
					String timeYY = DateUtil.getDateString(detail.getGmtCreate(), DATE_FORMAT.yyyy_mm_dd);
					Map<String,String> map = new HashMap<String, String>();
					map.put("planId", detail.getpId());
					map.put("planName", detail.getName());
					map.put("timeString", timeStr);
					map.put("timeYy", timeYY);
					map.put("userId", detail.getUserId());
					map.put("nickName", detail.getNickName());
					resultList.add(map);
				}
				result.setResult(resultList);
			}
		}catch (Exception e) {
			loger.error("queryPlanList@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	
	/**
	 * @param request
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/query_user_plan_tab",method=RequestMethod.POST)
	public Object queryTabPlan(HttpServletRequest request,String userId ,String tabType){
		SingleResult<List<Map<String,String>>> result = new SingleResult<List<Map<String,String>>>();
		if(StringUtils.isBlank(tabType) ){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
			List<PlanDto>  planList = new ArrayList<PlanDto>()  ; //planService.queryPlanByUserId(userId);
			if("myself".equals(tabType)){
				if(StringUtils.isBlank(userId) ){
					result.setSuccess(Boolean.FALSE);
					return result ;
				}
				planList = planService.queryMyselfPlan(userId);
			}else if("collect".equals(tabType)){
				if(StringUtils.isBlank(userId)){
					result.setSuccess(Boolean.FALSE);
					return result ;
				}
				planList = planService.queryCollectPlan(userId);
			}else if("public".equals(tabType)){
				planList = planService.queryPublicPlan();
			}else {
				planList = planService.queryNewPublicPlan();
			}
			
			if(planList != null && planList.size() > 0){
				for(PlanDto detail :  planList){
					String timeStr = DateUtil.getDateString(detail.getGmtCreate(), DATE_FORMAT.mm_dd);
					String timeYY = DateUtil.getDateString(detail.getGmtCreate(), DATE_FORMAT.yyyy_mm_dd);
					Map<String,String> map = new HashMap<String, String>();
					map.put("planId", detail.getpId());
					map.put("planName", detail.getName());
					map.put("timeString", timeStr);
					map.put("timeYy", timeYY);
					map.put("userId", detail.getUserId());
					map.put("nickName", detail.getNickName());
					map.put("userPicture", detail.getUserPicture());
					map.put("praiseCount", detail.getPraiseCount()+"");
					map.put("evaluateCount", detail.getEvaluateCount()+"");
					map.put("detailCount", detail.getDetailCount()+"");
					map.put("imageMain", detail.getImageMain());
					resultList.add(map);
				}
				result.setResult(resultList);
			}
		}catch (Exception e) {
			loger.error("queryPlanList@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	
	/**
	 * 加入计划
	 * @param request
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/join_plan",method=RequestMethod.POST)
	public Object joinPlan(HttpServletRequest request,String userId,String planId){
		SingleResult<Map<String,String>> result = new SingleResult<Map<String,String>>();
		if(StringUtils.isBlank(userId) || StringUtils.isBlank(planId)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			Plan plan = planService.queryPlanById(planId);
			if(plan == null || StringUtils.isBlank(plan.getUserId())){
				result.setSuccess(Boolean.FALSE);
				return result ;
			}
			
			if(userId.equalsIgnoreCase(plan.getUserId())){
				result.setSuccess(Boolean.FALSE);
				return result ;
			}
			
			//查询参数
			Map<String,String> param = new HashMap<String,String>();
			param.put("userId", userId);
			param.put("planId", planId);
			UserPlan up = planService.queryUserPlan(param);
			if(up != null){
				result.setSuccess(Boolean.FALSE);
				return result ;
			}
			
			
			//加入计划中
			UserPlan userPlan = new UserPlan() ;
			userPlan.setUpId(UUIDGenerator.get());
			userPlan.setuId(userId);
			userPlan.setGmtCreate(new Date());
			userPlan.setGmtModify(new Date());
			userPlan.setpId(planId);
			planService.insertUserPlan(userPlan);
			
			//返回的数据
			Map<String,String> map = new HashMap<String,String>();
			map.put("planName", plan.getName());
			result.setResult(map);
		}catch (Exception e) {
			loger.error("joinPlan@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	
	
	/**
	 * 删除计划 
	 * @param request
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete_user_plan",method=RequestMethod.POST)
	public Object deleteUserPlan(HttpServletRequest request,String userId,String planId){
		SingleResult<Map<String,String>> result = new SingleResult<Map<String,String>>();
		if(StringUtils.isBlank(userId) || StringUtils.isBlank(planId)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			Map<String,String> param = new HashMap<String,String>() ;
			param.put("userId", userId);
			param.put("planId", planId);
			planService.deleteUserPlan(param);
			
		}catch (Exception e) {
			loger.error("joinPlan@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	
	/**
	 * 更新赞
	 * @param request
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update_plan_praise",method=RequestMethod.POST)
	public Object updatePlanPraise(HttpServletRequest request,String userId,String planId,String pdId){
		SingleResult<Map<String,String>> result = new SingleResult<Map<String,String>>();
		if(StringUtils.isBlank(userId) || StringUtils.isBlank(planId) || StringUtils.isBlank(pdId)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			planService.updatePlanPraise(userId, planId, pdId);
		}catch(DuplicateKeyException ex){
			result.setSuccess(Boolean.FALSE);
			result.setResultCode("is_exists");
			return result ;
		}
		catch (Exception e) {
			loger.error("updatePlanPraise@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insert_plan_evaluate",method=RequestMethod.POST)
	public Object insertEvaluate(HttpServletRequest request,String planId,String pdId,String userId,String nickName,String figter,String content){
		SingleResult<Map<String,String>> result = new SingleResult<Map<String,String>>();
		if(StringUtils.isBlank(userId) || StringUtils.isBlank(planId) || StringUtils.isBlank(pdId)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			Evaluate evaluate = new Evaluate();
			evaluate.seteId(UUIDGenerator.get());
			evaluate.setPdId(pdId);
			evaluate.setpId(planId);
			evaluate.setuId(userId);
			evaluate.setNickName(nickName);
			evaluate.setFigter(figter);
			evaluate.setContent(content);
			evaluate.setGmtCreate(new Date());
			evaluate.setGmtModify(new Date());
			
			planDetailService.insert(evaluate);
		}catch (Exception e) {
			loger.error("insertEvaluate@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/query_plan_submit_note",method=RequestMethod.POST)
	public Object querySubmitNote(HttpServletRequest request,String pdId){
		SingleResult<List<Map<String,String>>> result = new SingleResult<List<Map<String,String>>>();
		if(StringUtils.isBlank(pdId)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
			List<Evaluate> list = planDetailService.querySubmitNote(pdId);
			if(list != null && list.size() > 0){
				for(Evaluate detail :  list){
					String timeStr = DateUtil.getDateString(detail.getGmtCreate(), DATE_FORMAT.mm_dd_hh);
					Map<String,String> map = new HashMap<String, String>();
					map.put("nickName", detail.getNickName());
					map.put("timeString", timeStr);
					map.put("content", detail.getContent());
					resultList.add(map);
				}
				result.setResult(resultList);
			}
		}catch (Exception e) {
			loger.error("querySubmitNote@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	/**
	 * 
	 * @param request
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update_plan_desc",method=RequestMethod.POST)
	public Object updateDesc(HttpServletRequest request,String pdId,String content){
		SingleResult<List<Map<String,String>>> result = new SingleResult<List<Map<String,String>>>();
		if(StringUtils.isBlank(pdId) || StringUtils.isEmpty(content)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			PlanDetail planDetail = new PlanDetail();
			planDetail.setPdId(pdId);
			planDetail.setContent(content);
			planDetail.setGmtModify(new Date());
			planDetailService.update(planDetail);
		}catch (Exception e) {
			loger.error("querySubmitNote@PlanController,message={}",new Object[]{e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete_plan_detail",method=RequestMethod.POST)
	public Object deletePlanDetail(HttpServletRequest request,String pdId,String pId){
		SingleResult<List<Map<String,String>>> result = new SingleResult<List<Map<String,String>>>();
		if(StringUtils.isBlank(pdId) || StringUtils.isEmpty(pId)){
			result.setSuccess(Boolean.FALSE);
			return result ;
		}
		try {
			Boolean t = planService.deletePlanDetail(pId,pdId);
			if(!t){
				result.setSuccess(Boolean.FALSE);
			}
		}catch (Exception e) {
			loger.error("deletePlanDetail@PlanController,pId={},pdId={},message={}",new Object[]{pId,pdId,e.getMessage()});
			result.setSuccess(Boolean.FALSE);
			result.setResultDesc(e.getMessage());
		}
		return result ;
	}
	
	/***************************************start***********************************************************/
	
	/**
	 * 
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/preview/{id}",method=RequestMethod.GET)
	public String preview(HttpServletRequest request,@PathVariable("id")String pdId){
		if(StringUtils.isBlank(pdId)){
			request.setAttribute("errordesc", "参数错误，请重新输入");
			return "/other/error.jsp" ;
		}
		
		try {
			List<PlanDetail>  planDetailList = 	planDetailService.selectBypId(pdId);
			Plan plan = planService.queryPlanById(pdId);
			
			if(plan == null || planDetailList == null || planDetailList.size() == 0){
				request.setAttribute("errordesc", "您的游记不存在，请重新输入");
				return "/other/error.jsp" ; 
			}
			
			request.setAttribute("planDetailList",planDetailList);
			request.setAttribute("plan", plan);
			request.setAttribute("planDetail", planDetailList.get(0));
			
		}catch (Exception e) {
			request.setAttribute("errordesc", "系统异常，请重试");
			return "/other/error.jsp" ;
		}
		
		//用户头
		String userAgent = request.getHeader( "USER-AGENT" ).toLowerCase();    
        if(null == userAgent){    
            userAgent = "";    
        }  
        Boolean isFromMobile =  CheckMobileUtil.check(userAgent);  
        //判断是否为移动端访问  
        if(isFromMobile){
			return "/plan/preview.jsp" ;
        }
		return "/plan/preview_pc.jsp" ;
	}
	
	
	
	
	
	public static void main(String[] args) {
		String a = "asdfasdf?b=aaaaaaaaa";
		
		System.out.println(a.substring(a.indexOf("=")));
	}
}
