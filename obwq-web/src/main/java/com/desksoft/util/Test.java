package com.desksoft.util;

import cn.obwq.dto.SechCrawDto;
import cn.obwq.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by guoxing.zgx on 2017/1/7.
 */
public class Test {



    public static void main_object(String [] a) throws Exception {

        //String string = HttpClientUtil.getHttpContent("http://up.obwq.cn/obwq/test/index/list.htm");
        String string = HttpClientUtil.getHttpContent("http://up.obwq.cn/obwq/test/index/object.htm");

       // List<Article> listArticle = new JsonUtil<List<Article>>().parseCont(string,Article.class) ;
        //Article aa = new JsonUtil<Article>().parseCont(string,Article.class) ;
        SechCrawDto aa = new JsonUtil<SechCrawDto>().parseCont(string,SechCrawDto.class) ;

        System.out.print(aa);
    }


    public static void main_c(String [] a) throws Exception {

        String string = HttpClientUtil.getHttpContent("http://up.obwq.cn/obwq/ae/get/articles/1/5/1.htm");

        List<Article> aa = new JsonUtil<List<Article>>().parseCont(string,Article.class) ;

        System.out.print(aa);
    }



    public static void main(String[] arr) throws  Exception {
        String content = HttpClientUtil.getHttpContent("https://news.cnblogs.com/n/topiclist/");
        //System.out.print(content);

        Document jsoup =  Jsoup.parse(content);

        Elements els =  jsoup.select("table a[href]");
        new PinyinTool() ;
        for(Element el : els){
            String url = "https://news.cnblogs.com" +  el.attr("href") ; // el.attr("href");
            String title = el.attr("title");

            String pinyin = PinyinTool.toPinYin(title).toUpperCase().replace(" ","");
            Element iel = el.getElementsByTag("img").first();

            String logo = "http:" +  iel.attr("src");

           // System.out.println("url=" + url + ",title=" + title + ",logo=" + logo);

            String sql = "insert into t_agroup(name,descr,url,logo,type,acount,gmt_create,gmt_modify) values('"+ pinyin + "','" + title + "','"+url+"','"+logo+"',1,0,now(),now()) ;";

            System.out.println(sql);



        }

    }



}
