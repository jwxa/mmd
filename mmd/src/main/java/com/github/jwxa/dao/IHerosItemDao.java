package com.github.jwxa.dao;

import com.github.jwxa.model.ItemVO;
import com.github.jwxa.model.PageVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jwxa on 2015/7/19.
 */
@Repository("herosItemDao")
public interface IHerosItemDao {

    public int addItemInfo(@Param("list") List<ItemVO> itemVOList);

    public List<ItemVO> queryItemInfoVO(@Param("pageVO") PageVO pageVO, @Param("keyword") String keyword);


}
