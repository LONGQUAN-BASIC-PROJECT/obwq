package com.desksoft.handler.impl;

import cn.obwq.constants.DATE_FORMAT;
import cn.obwq.dto.SechCrawDto;
import cn.obwq.entity.Article;
import cn.obwq.util.DateUtil;
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
public class CnBlogParseImpl implements ParseArticlesHandler {

    public List<Article> parser(String pageContent, SechCrawDto sechCrawDto) {

        List<Article>  artList = new ArrayList<Article>();


        Document jsoup =  Jsoup.parse(pageContent);
        Elements allElement = jsoup.getElementsByClass("news_block");
        for(Element element :allElement){
            Article article = new Article () ;

            Element a = element.select(".content  .news_entry a").first();
            String title = a.html();
            String url = "https://news.cnblogs.com" +  a.attr("href");


            Element timeEla =  element.select(".content  .entry_footer  span").last();
            String time = timeEla.html().trim() + ":00";

            article.setTitle(title);
            article.setUrl(url);
            article.setGmtCreate(DateUtil.parseDate(time, DATE_FORMAT.yyyy_mm_dd_hh_mm_ss));
            article.setGetModify(new Date());
            artList.add(article);
            System.out.println("title="+title + ",url= "+url + " ,time=" + time);
        }

        return artList;
    }



    public static void main(String[] s) throws Exception{

        String pageContent = HttpClientUtil.getHttpContent("https://news.cnblogs.com/n/topic_1.htm");

        new CnBlogParseImpl().parser(pageContent,null  );

    }

}
