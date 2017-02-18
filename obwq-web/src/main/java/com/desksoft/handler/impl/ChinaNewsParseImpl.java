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

import java.text.SimpleDateFormat;
import java.util.*;

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


            Element timeEla =  element.select("b").first();
            String time = timeEla.html().replace(",","");

            if(StringUtils.isBlank(time)){
                article.setGmtCreate(new Date());
            }else {

                String arr[] = time.split(" ") ;
                String timeString = arr[2] + "-" + moth.get(arr[0]) + "-" + arr[1] + " 00:00:00" ;

                article.setGmtCreate(DateUtil.parseDate(timeString, DATE_FORMAT.yyyy_mm_dd_hh_mm_ss));
            }

            article.setTitle(title);
            article.setUrl(url);
            article.setGroupId(sechCrawDto.getId());
            article.setGetModify(new Date());
            artList.add(article);
            System.out.println("title="+title + ",url= "+url + " ,time=" + article.getGmtCreate());
        }

        return artList;
    }

    private static  Map<String,String> moth = new HashMap<String, String>();

    static {
        moth.put("Jan","01");
        moth.put("Feb","02");
        moth.put("Mar","03");
        moth.put("Apr","04");
        moth.put("May","05");
        moth.put("June","06");
        moth.put("July","07");
        moth.put("Aug","08");
        moth.put("Sept","09");
        moth.put("Oct","10");
        moth.put("Oct","11");
        moth.put("Dec","12");

    }


    public static void main(String[] s) throws Exception{

        String pageContent = HttpClientUtil.getHttpContent("http://www.ecns.cn/news/politics/index.shtml");

        new ChinaNewsParseImpl().parser(pageContent,null  );


        //SimpleDateFormat sdf = new SimpleDateFormat("mon  d yyyy");
        //System.out.print(sdf.parse("Jan 13 2017"));

        //http://wdhdd889.iteye.com/blog/406112
    }

}
