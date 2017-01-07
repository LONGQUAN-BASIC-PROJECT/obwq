package com.desksoft.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.obwq.entity.Agroup;
import cn.obwq.entity.Article;
import cn.obwq.result.PageResult;
import cn.obwq.result.SingleResult;

import com.desksoft.common.constants.AgroupEnum;
import com.desksoft.craw.CrawService;
import com.desksoft.entity.dto.SechCrawDto;
import com.desksoft.service.AgroupService;
import com.desksoft.service.ArticleService;

/**
 * 
 * @author forever
 */
@Controller
@RequestMapping("/ae")
public class ArticleController {

	private static Logger loger = LoggerFactory.getLogger(ArticleController.class);

	
	@Autowired
	private CrawService crawService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private AgroupService agroupService;

	@RequestMapping(value = "/craw_article", method = RequestMethod.GET)
	@ResponseBody
	public SingleResult<String> craw(HttpServletRequest request) {

		SingleResult<String> result = new SingleResult<String>();
		try {
			SechCrawDto sec = new SechCrawDto();
			sec.setGroupType(AgroupEnum.ZHIHU.name());
			sec.setUrl("http://daily.zhihu.com/");
			sec.setGroupId(1l);
			crawService.crawFromWeb(sec);
			result.setResult("爬取成功");
		}catch (Exception e) {
			result.setResult("爬取失败，重试");
			result.setSuccess(Boolean.FALSE);
		}

		return result;
	}

	@RequestMapping(value = "/get/article/{aid}", method = RequestMethod.GET)
	@ResponseBody
	public Article read(HttpServletRequest request,
			@PathVariable("aid") Long aid) {
		return articleService.getById(aid);
	}

	/**
	 * @param request
	 * @param type
	 *            圈子类型
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	/*@RequestMapping(value = "/get/articles/{type}/{pageSize}/{currentPage}", method = RequestMethod.GET)
	@ResponseBody
	public Article readArticleByType(HttpServletRequest request,
			@PathVariable("type") String type,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("currentPage") Long currentPage) {

		return null;
	}*/

	/**
	 * @param request
	 * @param type
	 *            圈子类型
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	@RequestMapping(value = "/get/articles/{groupId}/{pageSize}/{currentPage}", method = RequestMethod.GET)
	@ResponseBody
	public PageResult<List<Article>> readArticleById(HttpServletRequest request,
			@PathVariable("groupId") Long groupId,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("currentPage") Integer currentPage) {

		PageResult<List<Article>> pageResult = new PageResult<List<Article>>();

		try {
			Agroup agroup = agroupService.selectById(groupId);
			if (agroup == null) {
				pageResult.setSuccess(Boolean.FALSE);
				pageResult.setResultDesc("groupId=" + groupId + ",do not exits");
				return pageResult;
			}
			Integer pageCount = articleService.queryArticleCountByGroupId(groupId);
			if ((currentPage - 1) * pageSize >= pageCount) {
				pageResult.setSuccess(Boolean.FALSE);
				pageResult.setResultDesc("has not more data");
				// 没有更多的数据
				return pageResult;
			}
			List<Article> listArticle = articleService.queryArticleByGroup(groupId, currentPage, pageSize);
			pageResult.setResult(listArticle);
			pageResult.setPageSize(pageSize);
			pageResult.setTotalSize(pageCount);
			pageResult.setTotalPage(Double.valueOf(Math.ceil( pageCount *1.0 / pageSize)).intValue());
		} catch (Exception e) {
			loger.error("updateUser@readArticleById,groupId={},pageSize={},currentPage={},message={}",new Object[]{groupId,pageSize,currentPage,e.getMessage()});
			pageResult.setSuccess(Boolean.FALSE);
			pageResult.setResultDesc(e.getMessage());
		}
		return pageResult;
	}

}
