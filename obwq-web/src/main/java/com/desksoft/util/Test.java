package com.desksoft.util;

import cn.obwq.dto.SechCrawDto;
import cn.obwq.entity.Article;

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


    public static void main(String [] a) throws Exception {

        String string = HttpClientUtil.getHttpContent("http://up.obwq.cn/obwq/ae/get/articles/1/5/1.htm");

        List<Article> aa = new JsonUtil<List<Article>>().parseCont(string,Article.class) ;

        System.out.print(aa);
    }


}
