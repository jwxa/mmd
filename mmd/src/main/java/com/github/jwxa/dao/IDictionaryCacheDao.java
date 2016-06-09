package com.github.jwxa.dao;

import com.github.jwxa.model.util.DictionaryCacheVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典表缓存Dao<br>
 * User: Jwxa<br>
 * Date: 2016/6/5.
 */
@Repository
public interface IDictionaryCacheDao {
    /**
     * 通过code前缀比较获取value值对应list
     * @param keyPrefix code前缀
     * @return
     */
    List<DictionaryCacheVO> queryDictionaryCacheList(@Param("keyPrefix")String keyPrefix);
}
