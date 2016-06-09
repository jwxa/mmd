package com.github.jwxa.util;

import com.github.jwxa.dao.IDictionaryCacheDao;
import com.github.jwxa.model.util.DictionaryCacheVO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 将字典表以KV形式缓存<br>
 * User: Jwxa<br>
 * Date: 2016/6/5.
 */
@Component
public class DictionaryCache {

    @Resource
    private IDictionaryCacheDao dictionaryCacheDao;

    @PostConstruct
    public void init(){
        String keyPrefix = null;
        List<DictionaryCacheVO> dictionaryCacheVOList = dictionaryCacheDao.queryDictionaryCacheList(keyPrefix);

    }


}
