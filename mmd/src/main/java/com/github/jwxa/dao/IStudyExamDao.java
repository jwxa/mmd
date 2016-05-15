package com.github.jwxa.dao;

import com.github.jwxa.model.AliasVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学习五十音图以及测验Dao层接口
 * Created by Jwxa on 2015/6/22.
 */
@Repository
public interface IStudyExamDao {
    /**
     * 通过种类查询假名
     * @param kind 002001平假名 002002片假名 null 所有
     * @return 假名实体类List
     */
    List<AliasVO> queryAliaListsByKind(String kind);

}
