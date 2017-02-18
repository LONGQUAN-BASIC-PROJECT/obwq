package com.desksoft.handler.impl;

import cn.obwq.constants.DATE_FORMAT;
import cn.obwq.dto.SechCrawDto;
import cn.obwq.entity.Article;
import cn.obwq.util.DateUtil;
import cn.obwq.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.desksoft.handler.ParseArticlesHandler;
import com.desksoft.util.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guoxing.zgx on 2017/1/13.
 */
public class Net163NewsParseImpl implements ParseArticlesHandler {


    public List<Article> parser(String pageContent, SechCrawDto sechCrawDto) {
        List<Article>  artList = new ArrayList<Article>();
        pageContent = pageContent.substring(pageContent.indexOf("["),pageContent.lastIndexOf("]")+1);

        JSONArray jsonArr  = JSONObject.parseArray(pageContent);


        for(int i = 0 ; i < jsonArr.size()-1 ; i++){
            Article article = new Article () ;

            JSONObject object = (JSONObject) jsonArr.get(i);
            String title = object.getString("title");
            String url = object.getString("docurl");
            String time = object.getString("time");


            String rurl = "http://3g.163.com/touch/article.html?docid=" + url.substring(url.lastIndexOf("/") + 1,url.lastIndexOf("."));

            if(StringUtils.isBlank(time)){
                article.setGmtCreate(new Date());
            }else {
                article.setGmtCreate(DateUtil.parseDate(time, DATE_FORMAT.mm_dd_yyyy_hh_mm_ss));
            }
            article.setTitle(title);
            article.setUrl(rurl);

            article.setGetModify(new Date());
            artList.add(article);
            article.setGroupId(sechCrawDto.getId());
            System.out.println("title="+title + ",url= "+url + " ,time=" + article.getGmtCreate());

        }

        return  artList ;
    }



    public static void main(String[] s) throws Exception{
        //String pageContent = HttpClientUtil.getHttpContent("http://tech.163.com/special/00097UHL/tech_datalist.js?callback=data_callback&charset=GB2312");
        //new Net163NewsParseImpl().parser(pageContent,null  );


        String url = "http://tech.163.com/17/0212/09/CD2I295700097U7T.html\n" ;

        String rurl = "http://3g.163.com/touch/article.html?docid=" + url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));

        System.out.print(rurl);
    }

}
