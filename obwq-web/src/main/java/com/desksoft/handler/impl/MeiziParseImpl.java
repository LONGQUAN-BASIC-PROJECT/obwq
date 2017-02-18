package com.desksoft.handler.impl;

import cn.obwq.constants.ApplicationConstants;
import cn.obwq.constants.DATE_FORMAT;
import cn.obwq.dto.SechCrawDto;
import cn.obwq.entity.Article;
import cn.obwq.util.DateUtil;
import cn.obwq.util.StringUtils;
import com.desksoft.craw.ClounService;
import com.desksoft.handler.ParseArticlesHandler;
import com.desksoft.util.HttpClientUtil;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.common.QiniuException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by guoxing.zgx on 2017/1/13.
 */
public class MeiziParseImpl implements ParseArticlesHandler {

    @Autowired
    private ClounService clounService;


    public List<Article> parser(String pageContent, SechCrawDto sechCrawDto) {
        List<Article>  artList = new ArrayList<Article>();

        Document jsoup =  Jsoup.parse(pageContent);
        Element bootEla = jsoup.getElementById("maincontent");


        Elements titlesEla = bootEla.getElementsByClass("postmeta");
        Elements imagesEla = bootEla.getElementsByClass("postContent");


        for(int i = 0 ; i < titlesEla .size() ;i++){

            Element titleEla  =  titlesEla .get(i);
            Element imageEl =  imagesEla.get(i);

            Element ta = titleEla.select(".metaRight h2 a").first();
            String title = ta.html();
            String url = ta.attr("href");

            Element groupEla = titleEla.select(".metaRight p").first();
            String  currTag = groupEla.html();

            Long groupId = getGroupId(currTag);

            //time start
            String day = titleEla.select(".metaLeft  .day").first().html();
            String mo_y = titleEla.select(".metaLeft .month_Year ").first().html().replace("&nbsp;","");
            String dayString = day + mo_y;
            //time end


            //pre image start
            //完整图片链接,以下规则
            //http://mm.howkuai.com/wp-content/uploads/2016a/07/14/limg.jpg
            //http://mm.howkuai.com/wp-content/uploads/2016a/07/14/01.jpg
            String imageUrl = imageEl.select("#picture p a img").first().attr("src");
            String needRestr = imageUrl.substring(imageUrl.lastIndexOf("/")+1,imageUrl.lastIndexOf("."));
            String logo = imageUrl.replace("/" + needRestr + ".","/limg.");

            //pre image end

            Article article = new Article();
            article.setGroupId(groupId);
            article.setTitle(title);
            article.setUrl(url);
            article.setThumbnail(logo);
            article.setGetModify(new Date());

            article.setGmtCreate(DateUtil.parseDate(dayString, DATE_FORMAT.ddmmyyyy));

            String fileKey = getCloundFile(url) ;

            article.addFeature(ApplicationConstants.Q_KEY,fileKey);

            artList.add(article);
        }
        return  artList ;
    }



    public String getCloundFile(String orgUrl){
        String pageContent = HttpClientUtil.getHttpContent(orgUrl);
        Document jsoup =  Jsoup.parse(pageContent);
        Elements imagesEla = jsoup.select("#maincontent .postContent #picture p img");

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < imagesEla.size() ; i++){
            if(i != 0){
                sb.append(",");
            }

            Element img = imagesEla.get(i);
            sb.append(img.attr("src"));
        }

        return clounService.uploadFile(sb.toString());
    }


    public Long getGroupId(String tagStr){
        tagStr = tagStr.replace("Tags:","").trim();
        String[] tagArr = tagStr.split(",");

        for(int i = 0 ; i < tagArr.length ; i ++){
            String str = tagArr[i].trim();
            String value = ApplicationConstants.keyMap.get(str);
            if(StringUtils.isNotBlank(value)){
                return Long.valueOf(value );
            }
        }

        return Long.valueOf(ApplicationConstants.keyMap.get("DEFAULT"));
    }



    public static void main(String[] s) throws Exception{
        String pageContent = HttpClientUtil.getHttpContent("http://www.meizitu.com/?charset=GBK");
        new MeiziParseImpl ().parser(pageContent,null  );
    }


    public static void main_(String[] s) throws Exception{
        //String pageContent = HttpClientUtil.getHttpContent("http://tech.163.com/special/00097UHL/tech_datalist.js?callback=data_callback&charset=GB2312");
        //new Net163NewsParseImpl().parser(pageContent,null  );


        String url = "http://tech.163.com/17/0212/09/CD2I295700097U7T.html\n" ;

        String rurl = "http://3g.163.com/touch/article.html?docid=" + url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));

        System.out.print(rurl);
    }


    static String accessKey = "wB5ZBzrFY3jI0txcOcZcw2IR1_yGfcENukvnw8zU";
    static String secretKey = "ipYDNUi4QN6IbH_GLEx-lxXrgy3gqf52u5RhwwG0";
    static String bucket = "obwq";


    public static void main______(String[] s) throws Exception{
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
     //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
       // String localFilePath = "/Users/guoxing.zgx/Documents/f.jpg";
        String localFilePath = "/Users/guoxing.zgx/deploy_obwq.sh";
          //默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            //Response response = uploadManager.put(localFilePath, null, upToken);
            Response response = uploadManager.put("sdfasdfasdfasdf".getBytes(), null, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }


    }



    public static void main_download(String[] s) throws Exception{
        String URL = "http://ohz1b1x2j.bkt.clouddn.com/FngzFVVjtE0LPSq6JMWxX9JPUoYs" ;
        Auth auth = Auth.create(accessKey, secretKey);

        String downloadRUL = auth.privateDownloadUrl(URL, 3600);

        System.out.print(downloadRUL);
    }
}
