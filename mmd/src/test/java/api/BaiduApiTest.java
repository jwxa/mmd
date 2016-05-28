package api;

import com.github.jwxa.util.BaiduApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 百度api store中的api调用测试类<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring.xml",
        "classpath:conf/spring-mybatis.xml",
        "classpath:conf/spring-mvc.xml",
        "classpath:conf/spring-activiti.xml"})
@Slf4j
public class BaiduApiTest {

    /**
     * 生成短网址测试类
     * @throws Exception
     */
    @Test
    public void testShorturlAPI() throws Exception {
        String sUrl = BaiduApiUtil.transShortUrl("http://logging.apache.org/log4j/1.2/faq.html#noconfig");
        System.out.println(sUrl);
    }

    /**
     * 生成短网址测试类
     * @throws Exception
     */
    @Test
    public void testShorturlDwzAPI() throws Exception {
        String sUrl = BaiduApiUtil.transShortUrlFromDwz("http://logging.apache.org/log4j/1.2/faq.html#noconfig");
        System.out.println(sUrl);
    }

}
