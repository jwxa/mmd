package com.github.jwxa.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.jwxa.model.util.ShorturlReqVO;
import com.github.jwxa.model.util.ShorturlRespVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

/**
 * 百度api store 公共接口<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Slf4j
public class BaiduApiUtil {

    private static final Properties p = new Properties();

    /**
     * 构造方法加载配置文件
     */
    static {
        try {
            InputStream in = new ClassPathResource("conf/api.properties").getInputStream();
            p.load(in);
        } catch (Exception e) {
            log.error("加载api.properties文件失败:{}", e);
        }
    }

    private static final String BAIDU_API_KEY = (String) p.get("baidu.api.key");

    private static final String SHORTURL_API_URL = "http://apis.baidu.com/chazhao/shorturl/shorturl";
    private static final String DWZ_API_URL = "http://www.dwz.cn/create.php";


    /**
     * 转换短网址 单条
     *
     * @param originUrl 原网址
     * @return
     */
    public static String transShortUrl(String originUrl) {
        ShorturlReqVO shorturlReqVO = new ShorturlReqVO();
        shorturlReqVO.setType("1");
        shorturlReqVO.setUrl(Lists.newArrayList(originUrl));
        Gson gson = new Gson();
        String param = gson.toJson(shorturlReqVO);
        Map header = Maps.newTreeMap();
        header.put("apikey", BAIDU_API_KEY);
        String respStr = HttpClientUtil.postUrlWithStringEntityParamsAndHeader(SHORTURL_API_URL, param, header);
        ShorturlRespVO respVO = gson.fromJson(respStr, ShorturlRespVO.class);
        if (respVO.getData() != null && respVO.getData().getUrls() != null
                && !respVO.getData().getUrls().isEmpty()) {
            return respVO.getData().getUrls().get(0).getShortUrl();
        } else {
            log.info("调用api生成短网址失败，返回null");
            return null;
        }
    }

    /**
     * 调用http://dwz.cn/接口
     *
     * @param originUrl
     * @return
     */
    public static String transShortUrlFromDwz(String originUrl){
        Map<String,String> params = Maps.newTreeMap();
        params.put("url",originUrl);
        String respStr = HttpClientUtil.postUrlWithParams(DWZ_API_URL, params);
        JSONObject object = JSON.parseObject(respStr);
        if("-1".equals(object.getString("status"))){
            log.info(object.getString("err_msg"));
            return null;
        }else{
            return object.getString("tinyurl");
        }
    }




}
