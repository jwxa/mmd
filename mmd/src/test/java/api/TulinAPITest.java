package api;

import com.github.jwxa.model.tulin.TulinRespVO;
import com.github.jwxa.util.TulinApiUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 图灵api调用测试类<br>
 * User: Jwxa<br>
 * Date: 2016/5/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring.xml",
        "classpath:conf/spring-mybatis.xml",
        "classpath:conf/spring-mvc.xml",
        "classpath:conf/spring-activiti.xml"})
@Slf4j
public class TulinAPITest {

    @Test
    public void testTulinAPI() throws Exception {
        String str = "#沪太路新闻";
        String text = str.trim().substring(1);
        String result = TulinApiUtil.getMsgFromTulin(text);
        System.out.println(result);
    }


    @Test
    public void testJson(){
        String respStr = "{\n" +
                "\"code\":100000,\n" +
                "\"text\":\"你也好 嘻嘻\"\n" +
                "}";
        double s = System.currentTimeMillis();
        JsonElement element = new JsonParser().parse(respStr);
        String str =  element.getAsJsonObject().get("code").getAsString();
        double e = System.currentTimeMillis();
        System.out.println(e-s);
        JsonElement element1 = new JsonParser().parse(respStr);
        String str1 =  element1.getAsJsonObject().get("code").getAsString();
        double f = System.currentTimeMillis();
        System.out.println(f-e);
    }

    @Test
    public void testGson(){
        String respStr = "{\n" +
                "\"code\":100000,\n" +
                "\"text\":\"你也好 嘻嘻\"\n" +
                "}";
        double s = System.currentTimeMillis();
        Gson gson = new Gson();
        String str =  gson.fromJson(respStr,TulinRespVO.class).getCode();
        double e = System.currentTimeMillis();
        System.out.println(e-s);
        String str1 =  gson.fromJson(respStr,TulinRespVO.class).getCode();
        double f = System.currentTimeMillis();
        System.out.println(f - e);
    }

}
