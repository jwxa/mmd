package api;

import com.github.jwxa.util.TulinApiUtil;
import org.junit.Test;

/**
 * 图灵api调用测试类<br>
 * User: Jwxa<br>
 * Date: 2016/5/26.
 */

public class TulinAPITest {

    @Test
    public void testTulinAPI() throws Exception {
        String str = "#沪太路新闻";
        String text = str.trim().substring(1);
        String result = TulinApiUtil.getMsgFromTulin(text);
        System.out.println(result);
    }

}
