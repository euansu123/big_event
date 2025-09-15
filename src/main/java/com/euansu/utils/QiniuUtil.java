package com.euansu.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.io.InputStream;

public class QiniuUtil {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "lRgQ6Weu0CEGZFyFSXw9QDiOAZtKFcGiQW_U9BoK";
    private static final String SECRET_KEY = "z1QtG6S2ex5FJlMp4lk7CM6J73QPxNk72bVyrSGT";
    //要上传的空间
    private static final String BUCKET_NAME = "euansublog";


    public static String uploadFiles(InputStream file, String fileName) {
        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        // 自动识别要上传的区域
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);
        // 创建上传对象
        UploadManager uploadManager = new UploadManager(c);
        // 获取上传的Token
        String uploadToken = auth.uploadToken(BUCKET_NAME);
        try {
            //调用put方法上传
            Response res = uploadManager.put(file.readAllBytes(), fileName, uploadToken);

            //打印返回的信息
            System.out.println(res.bodyString());
            // 构造返回的地址
            String url = "http://img.euansu.cn/" + fileName;
            return url;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}
