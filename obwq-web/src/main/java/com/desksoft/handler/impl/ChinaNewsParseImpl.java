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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guoxing.zgx on 2017/1/13.
 */
public class ChinaNewsParseImpl implements ParseArticlesHandler {

    public List<Article> parser(String pageContent, SechCrawDto sechCrawDto) {

        List<Article>  artList = new ArrayList<Article>();


        Document jsoup =  Jsoup.parse(pageContent);
        Elements allElement = jsoup.select(".bizlst li");
        for(Element element :allElement){
            Article article = new Article () ;

            Element a = element.select("h3 a").first();
            String title = a.html();
            String url = "http://www.ecns.cn/m" +  a.attr("href");


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

        //String pageContent = HttpClientUtil.getHttpContent("http://www.ecns.cn/news/politics/index.shtml");

        //new ChinaNewsParseImpl().parser(pageContent,null  );


        SimpleDateFormat sdf = new SimpleDateFormat("mon  d yyyy");
        System.out.print(sdf.parse("Jan 13 2017"));

        //http://wdhdd889.iteye.com/blog/406112
    }

}
