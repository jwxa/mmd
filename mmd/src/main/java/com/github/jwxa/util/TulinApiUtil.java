package com.github.jwxa.util;

import com.github.jwxa.model.tulin.ITextShow;
import com.github.jwxa.model.tulin.RespCode;
import com.github.jwxa.model.tulin.TulinRespVO;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * 调用第三方图灵机器人api<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Slf4j
public class TulinApiUtil {

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

    private static final String TULIN_KEY = (String) p.get("tulin.api.key");
    private static final String TULIN_URL = (String) p.get("tulin.api.url");

    /**
     * 调用图灵接口获取返回信息
     *
     * @param text 向图灵机器人发送的内容
     * @return 图灵机器人返回的结果
     */
    public static String getMsgFromTulin(String text) {
        Map params = Maps.newHashMap();
        params.put("key", TULIN_KEY);
        params.put("info", text);
        params.put("loc", "");
        params.put("userid", "10000");
        String respStr = HttpClientUtil.postUrlWithParams(TULIN_URL, params);
        //转换为父类，需要知道他的实际类型
        ITextShow tulinRespVO = convert2Class(respStr);
        log.info("解析响应报文后的实体类类型为{}", tulinRespVO.getClass().getSimpleName());
        String msg = tulinRespVO.showText();
        //1.接收到信息后先对换行符进行处理
        msg = msg.replace("<br>", "\n").replace("\\r", "\n").replace("\\n", "\n");
        return msg;
    }

    private static ITextShow convert2Class(String respStr) {
        Gson gson = new Gson();
        TulinRespVO tulinRespVO = gson.fromJson(respStr, TulinRespVO.class);
        if (tulinRespVO != null && tulinRespVO.getCode() != null) {
            Class<? extends TulinRespVO> clazz = RespCode.getClass(tulinRespVO.getCode());
            ITextShow respVO = gson.fromJson(respStr, clazz);
            return respVO;
        }
        return null;
    }

}
