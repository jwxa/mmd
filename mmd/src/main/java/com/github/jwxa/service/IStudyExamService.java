package com.github.jwxa.service;

import com.github.jwxa.model.QuestionVO;
import com.github.jwxa.model.AliasVO;
import com.google.common.collect.Multimap;

import java.util.List;


/**
 * 五十音图 学习测试 Service层接口
 * Created by Jwxa on 2015/6/22.
 */
public interface IStudyExamService {

    /**
     * 通过种类查询假名
     * @param kind 002001平假名 002002片假名 null 所有
     * @return 假名实体类List
     */
    Multimap<String, AliasVO> queryAliaListsByKind(String kind);

    /**
     * 生成五十音图题目方法
     * @param testAliasList 题目域list
     * @param createType 创建类型 1.看假名选读音 2.看读音选假名 3.混合
     * @param questionNum 题目量
     * @return 题目实体类List
     */
    List<QuestionVO> createQuestionByAliasList(List<AliasVO> testAliasList, String createType, int questionNum);
}
