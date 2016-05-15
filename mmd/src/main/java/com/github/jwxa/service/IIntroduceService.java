package com.github.jwxa.service;

import com.github.jwxa.model.IntroduceVO;
import com.github.jwxa.model.PageVO;
import com.github.jwxa.model.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * 软件简介页面Service接口
 * Created by Jwxa on 2015/2/9.
 */
public interface IIntroduceService {
    /**
     * 添加软件简介
     * @param introduceVO 软件简介实体类
     * @return 插入行数
     */
    public Map addIntroduce(IntroduceVO introduceVO);

    /**
     * 通过UserId查询其下的所有软件简介
     * @param userInfo 用户信息实体类
     * @param statusCode 状态码
     * @return 软件简介信息实体类
     */
    public List<IntroduceVO> queryIntroduceByUserId(UserInfo userInfo, String statusCode);

    /**
     * 通过uuid查询软件简介
     * @param uuid
     * @return 软件简介实体类
     */
    public IntroduceVO queryIntroduceByUuid(String uuid);

    /**
     * 工作流提交审核软件简介
     * @param introduceVO
     * @return
     */
    public String submitToAuditIntroduce(IntroduceVO introduceVO);

    /**
     * 通过UserId和分页条件查询其下的所有软件简介
     * @param userInfo 用户信息实体类
     * @param statusCode 状态码
     * @param pageVO 分页实体类
     * @return
     */
    List<IntroduceVO> queryIntroduceByUserIdAndPage(UserInfo userInfo, String statusCode, PageVO pageVO);


    public int queryIntroduceCountByUserId(UserInfo userInfo, String statusCode);
}
