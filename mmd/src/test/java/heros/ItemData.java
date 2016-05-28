package heros;

import com.github.jwxa.dao.IHerosItemDao;
import com.github.jwxa.model.ItemVO;
import com.github.jwxa.model.PageVO;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * Created by Jwxa on 2015/7/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring.xml",
        "classpath:conf/spring-mybatis.xml",
        "classpath:conf/spring-mvc.xml",
        "classpath:conf/spring-activiti.xml"})
@Slf4j
public class ItemData {
    @Resource
    private IHerosItemDao herosItemDao;

    @Test
    public void addItemData(){
        File file = new File("L:\\heroes_text_chinese.txt");
        List<String> lines = null;
        Map<String,String> itemNameMap = null;
        Map<String,String> itemDescMap = null;
        try {
            lines = Files.readLines(file, Charsets.UTF_8);
            itemNameMap =getKeyMap(lines,"HEROES_ITEM_NAME_");
            itemDescMap=getKeyMap(lines,"HEROES_ITEM_DESC_");
            //取key的交集
            Set<String> hasDescItemSet = itemNameMap.keySet();
            List<ItemVO> itemVOList = Lists.newArrayList();
            for(String key:hasDescItemSet){
                String desc =itemDescMap.get(key);
                if(desc==null)
                    desc="无描述";
                ItemVO itemVO = new ItemVO(key,itemNameMap.get(key),desc);
                itemVOList.add(itemVO);
            }
            herosItemDao.addItemInfo(itemVOList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据关键字获取相应Map key 变量名 value 变量描述
     * @param lines 文本行List
     * @param keyword 关键字
     * @return
     */
    public Map<String,String> getKeyMap(List<String> lines,String keyword){
        Map<String,String> keyMap = Maps.newTreeMap();
        for(String line:lines){
            if(line!=null&&line.trim().indexOf(keyword)>-1){
                String[] params = line.split("\"\t\"");
                String key = params[0].substring(params[0].indexOf(keyword) + keyword.length());
                String value = params[1].substring(0,params[1].length()-1);
                if(params.length==2){
                    keyMap.put(key,value);
                }
            }
        }
        return keyMap;
    }


    @Test
    public void queryItemInfoList(){
        PageVO pageVO = new PageVO(0,10);
        List<ItemVO> itemVOList = herosItemDao.queryItemInfoVO(pageVO,null);
//        String keyword = "翅膀";
//        List<ItemVO> itemVOList = herosItemDao.queryItemInfoVO(pageVO,keyword);

        System.out.println(itemVOList);
    }

}
