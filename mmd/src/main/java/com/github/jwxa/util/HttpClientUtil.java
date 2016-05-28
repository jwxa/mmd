package com.github.jwxa.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * HttpClient的使用<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Slf4j
public class HttpClientUtil {

    /**
     * 带参数Post
     * @param url 地址
     * @param params 参数
     * @return 返回文本
     */
    public static String postUrlWithParams(String url, Map params) {
        DefaultHttpClient httpclient =  new DefaultHttpClient();
        try {
            HttpPost httpost = new HttpPost(url);
            // 添加参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if (params != null && params.keySet().size() > 0) {
                Iterator iterator = params.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    nvps.add(new BasicNameValuePair((String) entry.getKey(),
                            (String) entry.getValue()));
                }
            }

            httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

            HttpResponse response = null;
            String result = null;
            try {
                response = httpclient.execute(httpost);
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity,  Consts.UTF_8);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            log.info("status:{}", response.getStatusLine());
            return result;
//            log.info(new String (EntityUtils.toString(entity).getBytes("UTF-8"),"UTF-8"));//测试编码格式
//            System.out.println("Post logon cookies:");
//            List<Cookie> cookies = httpclient.getCookieStore().getCookies();
//            if (cookies.isEmpty()) {
//                System.out.println("None");
//            } else {
//                for (int i = 0; i < cookies.size(); i++) {
//                    System.out.println("- " + cookies.get(i).toString());
//                }
//            }
        } finally {
            // 关闭请求
            httpclient.getConnectionManager().shutdown();
        }
    }


    /**
     * 带参数Post
     * @param url 地址
     * @param params 参数
     * @return 返回文本
     */
    public static String postUrlWithParamsHeader(String url, Map<String,String> params,Map<String,String>  headers) {
        DefaultHttpClient httpclient =  new DefaultHttpClient();
        try {
            HttpPost httpost = new HttpPost(url);
            //添加Header
            if(headers!=null&&headers.size()>0){
                for(Map.Entry<String,String> entry:headers.entrySet()){
                    String key =entry.getKey();
                    String value = entry.getValue();
                    httpost.addHeader(new BasicHeader(key,value));
                }
            }

            // 添加参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if (params != null && params.keySet().size() > 0) {
                Iterator iterator = params.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    nvps.add(new BasicNameValuePair((String) entry.getKey(),
                            (String) entry.getValue()));
                }
            }

            httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

            HttpResponse response = null;
            String result = null;
            try {
                response = httpclient.execute(httpost);
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            log.info("status:{}", response.getStatusLine());
            return result;
        } finally {
            // 关闭请求
            httpclient.getConnectionManager().shutdown();
        }
    }

    public static String postUrlWithStringEntityParamsAndHeader(String url, String params, Map<String,String> headers) {
        DefaultHttpClient httpclient =  new DefaultHttpClient();
        try {
            HttpPost httpost = new HttpPost(url);
            //添加Header
            if(headers!=null&&headers.size()>0){
                for(Map.Entry<String,String> entry:headers.entrySet()){
                    String key =entry.getKey();
                    String value = entry.getValue();
                    httpost.addHeader(new BasicHeader(key,value));
                }
            }
            // 添加参数
            StringEntity stringEntity = null;
            try {
                stringEntity = new StringEntity(params,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.info("不支持的编码格式");
                log.error(e.getMessage(),e);
                return null;
            }
            httpost.addHeader("content-type", "application/json");
            httpost.setEntity(stringEntity);
            HttpResponse response = null;
            String result = null;
            try {
                response = httpclient.execute(httpost);
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            log.info("status:{}", response.getStatusLine());
            return result;
        } finally {
            // 关闭请求
            httpclient.getConnectionManager().shutdown();
        }
    }

}
