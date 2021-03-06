package com.desksoft.controller;

import cn.obwq.dto.SechCrawDto;
import cn.obwq.entity.Agroup;
import cn.obwq.entity.Article;
import cn.obwq.result.PageResult;
import cn.obwq.result.SingleResult;
import cn.obwq.util.StringUtils;
import com.desksoft.craw.ClounService;
import com.desksoft.craw.CrawService;
import com.desksoft.service.AgroupService;
import com.desksoft.service.ArticleService;
import com.desksoft.util.CollectionUtil;
import com.desksoft.util.HttpClientUtil;
import com.desksoft.util.UrlHelp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
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
    @Autowired
    private ClounService clounService;

    @RequestMapping(value = "/craw_all", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<String> crawAll(HttpServletRequest request,String url) {

        SingleResult<String> result = new SingleResult<String>();
        try {
            mainInput();
        } catch (Exception e) {
            result.setResult("爬取失败，重试");
            result.setSuccess(Boolean.FALSE);
        }

        return result;
    }

    public  void mainInput() throws Exception {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            String str = "";
            fis = new FileInputStream("/Users/guoxing.zgx/Documents/index.txt");// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            while ((str = br.readLine()) != null) {
                process(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  void process(String url){
        try {
            loger.error("startprocess:" + url);
            String content =  HttpClientUtil.getHttpContent(url);
            Document jsoup =  Jsoup.parse(content);
            Elements els = jsoup.select(".wp-list .wp-item .con .pic a");
            for(Element e : els){
                String itemUrl =  e.attr("href");
                loger.error("mainUrl:" + url+",suburl:" + itemUrl);

                SechCrawDto sec = new SechCrawDto();
                sec.setName("SINGLE_MEI_ZI_TU");
                sec.setUrl(itemUrl +"?charset=GBK");
                crawService.crawFromWeb(sec);
            }
            loger.error("endprocess:" + url);

        }catch (Exception e){
            loger.error("errorprocess:" + url);
        }




    }


    @RequestMapping(value = "/craw_article", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<String> craw(HttpServletRequest request,String url) {

        SingleResult<String> result = new SingleResult<String>();
        try {
            SechCrawDto sec = new SechCrawDto();
            sec.setName("SINGLE_MEI_ZI_TU");
            sec.setUrl(url +"?charset=GBK");
            crawService.crawFromWeb(sec);
            result.setResult("爬取成功");
        } catch (Exception e) {
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
            if (CollectionUtil.isNotEmpty(listArticle)) {
                for (Article ae : listArticle) {
                    ae.setGroupName(agroup.getName());
                    ae.setGroupUrl(agroup.getUrl());
                    ae.setGroupLogo(agroup.getLogo());
                }
            }

            pageResult.setResult(listArticle);
            pageResult.setPageSize(pageSize);
            pageResult.setTotalSize(pageCount);
            pageResult.setTotalPage(Double.valueOf(Math.ceil(pageCount * 1.0 / pageSize)).intValue());
        } catch (Exception e) {
            loger.error("updateUser@readArticleById,groupId={},pageSize={},currentPage={},message={}", new Object[]{groupId, pageSize, currentPage, e.getMessage()});
            pageResult.setSuccess(Boolean.FALSE);
            pageResult.setResultDesc(e.getMessage());
        }
        return pageResult;
    }


    /**
     * http://localhost:8888/obwq/ae/get/articles/groups/7814_7806/20/1.htm
     * @param request
     * @param pageSize
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/get/articles/groups/{groupIds}/{pageSize}/{currentPage}", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<List<Article>> readArticleBygroupIds(HttpServletRequest request,
        @PathVariable("groupIds") String groupIds,
        @PathVariable("pageSize") Integer pageSize,
        @PathVariable("currentPage") Integer currentPage) {

        PageResult<List<Article>> pageResult = new PageResult<List<Article>>();

        try {

            List<String> groupIdList = new ArrayList<String>();
            for(String str : groupIds.split("_")){
                groupIdList.add(str);
            }

            Integer pageCount = articleService.queryArticleCountByGroupIds(groupIdList);
            if ((currentPage - 1) * pageSize >= pageCount) {
                pageResult.setSuccess(Boolean.FALSE);
                pageResult.setResultDesc("has not more data");
                // 没有更多的数据
                return pageResult;
            }
            List<Article> listArticle = articleService.queryArticleByGroup(groupIdList, currentPage, pageSize);
            pageResult.setResult(listArticle);
            pageResult.setPageSize(pageSize);
            pageResult.setTotalSize(pageCount);
            pageResult.setTotalPage(Double.valueOf(Math.ceil(pageCount * 1.0 / pageSize)).intValue());
        } catch (Exception e) {
            loger.error("updateUser@readArticleBygroupIds,groupIds={},pageSize={},currentPage={},message={}", new Object[]{groupIds, pageSize, currentPage, e.getMessage()});
            pageResult.setSuccess(Boolean.FALSE);
            pageResult.setResultDesc(e.getMessage());
        }
        return pageResult;
    }



    /**
     * @param request
     * @param pageSize
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/get/articles/hot/{pageSize}/{currentPage}", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<List<Article>> readHotArticleBy(HttpServletRequest request,
                                                      @PathVariable("pageSize") Integer pageSize,
                                                      @PathVariable("currentPage") Integer currentPage) {

        PageResult<List<Article>> pageResult = new PageResult<List<Article>>();

        try {
            Integer pageCount = articleService.queryCrawArticleCount();
            if ((currentPage - 1) * pageSize >= pageCount) {
                pageResult.setSuccess(Boolean.FALSE);
                pageResult.setResultDesc("has not more data");
                // 没有更多的数据
                return pageResult;
            }
            List<Article> listArticle = articleService.queryHotArticleByGroup(currentPage, pageSize);
            if (CollectionUtil.isNotEmpty(listArticle)) {
                for (Article ae : listArticle) {

                    Agroup agroup = agroupService.selectById(ae.getGroupId());
                    if (agroup != null) {
                        ae.setGroupName(agroup.getName());
                        ae.setGroupUrl(agroup.getUrl());
                        ae.setGroupLogo(agroup.getLogo());
                    }
                }
            }

            pageResult.setResult(listArticle);
            pageResult.setPageSize(pageSize);
            pageResult.setTotalSize(pageCount);
            pageResult.setTotalPage(Double.valueOf(Math.ceil(pageCount * 1.0 / pageSize)).intValue());
        } catch (Exception e) {
            loger.error("updateUser@readHotArticle,pageSize={},currentPage={},message={}", new Object[]{pageSize, currentPage, e.getMessage()});
            pageResult.setSuccess(Boolean.FALSE);
            pageResult.setResultDesc(e.getMessage());
        }
        return pageResult;
    }


    /**
     * @param request
     * @param url
     * @return
     */
    @RequestMapping(value = "/article/add/url", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult<Boolean> addArticleByUrl(HttpServletRequest request,
                                                 String url,
                                                 String groupId) {

        SingleResult<Boolean> pageResult = new SingleResult<Boolean>();

        try {
            if(StringUtils.isBlank(url) || StringUtils.isBlank(groupId) || !StringUtils.isNumeric(groupId)){
                pageResult.setSuccess(Boolean.FALSE);
                pageResult.setResultDesc("链接URL不能为空");
                return pageResult ;
            }

            Article article = new Article();
            article.setGroupId(Long.valueOf(groupId));
            article.setUrl(url);
            Boolean isExits =  articleService.isExits(article);
            if(isExits){
                pageResult.setSuccess(Boolean.FALSE);
                pageResult.setResultDesc("已经存在对应的文章链接了");
                return pageResult ;
            }


            String title = UrlHelp.getUrlTitle(url);

            article.setTitle(title);
            article.setGmtCreate(new Date());
            article.setGetModify(new Date());
            article.setPraiseCount(0l);
            article.setCommentCount(0l);
            article.setCollectionCount(0l);

            articleService.insert(article);

            loger.error("add_article,url={},title={},groupId={}", new Object[]{url,title,groupId});

        } catch (Exception e) {
            loger.error("error@addArticleByUrl", new Object[]{url, e.getMessage()});
            pageResult.setSuccess(Boolean.FALSE);
            pageResult.setResultDesc(e.getMessage());
        }
        return pageResult;
    }



    /**
     * @param request
     * @param key
     * @return
     */
    @RequestMapping(value = "/image/view/{key}", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<String> getFileContent(HttpServletRequest request,
                                                @PathVariable("key") String key) {

        SingleResult<String> pageResult = new SingleResult<String>();

        try {
            if(StringUtils.isBlank(key) ){
                pageResult.setSuccess(Boolean.FALSE);
                pageResult.setResultDesc("key为空，或不存在的key");
                return pageResult ;
            }

            String content = clounService.getFileContent(key) ;
            if(StringUtils.isBlank(content)){
                pageResult.setSuccess(Boolean.FALSE);
                pageResult.setResultDesc("key为空，或不存在的key");
                return pageResult ;
            }

            pageResult.setResult(content);

            loger.error("etFileContent,key={},content={}", new Object[]{key,content});

        } catch (Exception e) {
            loger.error("error@addArticleByUrl", new Object[]{key, e.getMessage()});
            pageResult.setSuccess(Boolean.FALSE);
            pageResult.setResultDesc(e.getMessage());
        }
        return pageResult;
    }







}
