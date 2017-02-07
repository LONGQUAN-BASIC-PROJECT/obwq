package com.desksoft.util;

import cn.obwq.dto.SechCrawDto;
import cn.obwq.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guoxing.zgx on 17/1/23.
 */
public class UrlHelp {

    private static Map<String,String> getUrlParamMap(String url){
        if(url.indexOf("?") == -1){
            return null ;
        }

        String paramsString = url.substring(url.indexOf("?")+1);
        if(StringUtils.isBlank(paramsString)){
            return null ;
        }

        String[] pmArr = paramsString.split("&");
        if(pmArr.length == 0){
            return null;
        }


        Map<String,String> map = new HashMap<String, String>();
        for(String pm : pmArr){
            String[] pam = pm.split("=");
            map.put(pam[0],pam[1]);
        }

        return map;
    }


    public static String getCrawType(SechCrawDto sechCrawDto){
        if(sechCrawDto == null || StringUtils.isBlank(sechCrawDto.getUrl())){
            return null ;
        }

        String url = sechCrawDto.getUrl() ;
        Map<String,String> urlMap = getUrlParamMap(url);
        if(CollectionUtil.isEmpty(urlMap)){
            return sechCrawDto.getName();
        }

        String crawType = urlMap.get("type");
        if(StringUtils.isNotBlank(crawType)){
            return crawType ;
        }

        return sechCrawDto.getName();
    }



    public static String getCharset(String url){
        if(StringUtils.isBlank(url)){
            return null;
        }

        Map<String,String> urlMap = getUrlParamMap(url);
        if(CollectionUtil.isEmpty(urlMap)){
            return null;
        }

        String charset = urlMap.get("charset");
        if(StringUtils.isBlank(charset)){
            return null ;
        }

        return charset ;

    }

    public static void main(String[] a){

        //System.out.print(getCharset("sdfsdfsdfsdf?a=b&c=d&charset=UTF-8"));

        //String[] ar = "http://www.baidu.com,http://www.qq.com".split(",");
        //System.out.print(ar[1]);


        String[] ar = "http://www.baidu.com".split(",");
        System.out.print(ar.length);

    }
}
