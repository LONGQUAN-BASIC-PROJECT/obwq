package com.desksoft.handler.impl;

import cn.obwq.constants.DATE_FORMAT;
import cn.obwq.dto.SechCrawDto;
import cn.obwq.entity.Article;
import cn.obwq.util.DateUtil;
import cn.obwq.util.StringUtils;
import com.desksoft.handler.ParseArticlesHandler;
import com.desksoft.util.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by guoxing.zgx on 2017/1/13.
 */
public class FirstNewsParseImpl implements ParseArticlesHandler {

    public List<Article> parser(String pageContent, SechCrawDto sechCrawDto) {

        List<Article>  artList = new ArrayList<Article>();


        Document jsoup =  Jsoup.parse(pageContent);
        Elements allElement = jsoup.select("#news_List .dl-item");
        for(Element element :allElement){
             Article article = new Article () ;

            Element a = element.select("h3 a").first();
            String title = a.html();
            String url = a.attr("href").replace("www.","m.");

            //http://  m.yicai.com/news/5211731.html
            //http://www.yicai.com/news/5211731.html

            Element timeEla =  element.select("h4 span").first();
            String time = timeEla.html().trim();

            if(StringUtils.isBlank(time)){
                article.setGmtCreate(new Date());
            }else {
                article.setGmtCreate(DateUtil.parseDate(time, DATE_FORMAT.yyyy_mm_dd_hh_mm_ss));
            }

            article.setTitle(title);
            article.setUrl(url);

            article.setGetModify(new Date());
            artList.add(article);
            System.out.println("title="+title + ",url= "+url + " ,time=" + article.getGmtCreate());
        }

        return artList;
    }


    public static void main(String[] s) throws Exception{

        String pageContent = HttpClientUtil.getHttpContent("http://www.yicai.com/news/?charset=UTF-8");

        new FirstNewsParseImpl().parser(pageContent,null  );

    }

}
