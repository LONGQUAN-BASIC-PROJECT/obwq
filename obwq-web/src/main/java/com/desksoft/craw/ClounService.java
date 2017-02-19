package com.desksoft.craw;

import cn.obwq.util.StringUtils;
import com.desksoft.util.HttpClientUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guoxing.zgx on 2017/2/18.
 */
public class ClounService {

    private static Logger LOGER = LoggerFactory.getLogger(ClounService.class);

    private String accessKey;
    private String secretKey;
    private String bucket = "obwq";

    static Configuration cfg = null;
    static UploadManager uploadManager = null;

    static {
        //构造一个带指定Zone对象的配置类
        cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        uploadManager = new UploadManager(cfg);
    }

    public String uploadFile(String content) {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(content.getBytes(), null, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String key = putRet.key;
            return key ;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }


    public String getFileContent(String key){
        try {
            String URL = "http://uc.obwq.cn/" + key ;
            //String URL = "http://ohz1b1x2j.bkt.clouddn.com/" + key ;
            Auth auth = Auth.create(accessKey, secretKey);
            String downloadRUL = auth.privateDownloadUrl(URL, 3600);

            if(StringUtils.isBlank(downloadRUL)){
                LOGER.error("error@getFileCont,down_url_is_null,key={}",new Object[]{key});
                return null ;
            }

            return HttpClientUtil.getHttpContent(downloadRUL);

        }catch (Exception e){
            LOGER.error("error@getFileCont_error,key={}",new Object[]{key});
        }

        return null ;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
