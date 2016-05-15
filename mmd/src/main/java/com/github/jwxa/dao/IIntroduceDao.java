package com.github.jwxa.dao;

import com.github.jwxa.model.IntroduceVO;
import com.github.jwxa.model.PageVO;
import com.github.jwxa.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 添加软件简介Dao接口
 * Created by Jwxa on 2015/2/9.
 */
public interface IIntroduceDao {
    /**
     * 插入软件简介信息s
     * @param introduceVO
     * @return
     */
    public int addIntroduceInfo(@Param("introduceVO") IntroduceVO introduceVO);

    /**
     * 插入软件简介详情
     * @param introduceVO
     * @return
     */
    public int addIntroduceDetail(IntroduceVO introduceVO);

    /**
     * 通过UserId查询其下的所有软件简介
     * @param userInfo 用户信息
     * @param statusCode 状态码
     * @return 软件简介List
     */
    public List<IntroduceVO> queryIntroduceByUserId(@Param("userInfo") UserInfo userInfo, @Param("statusCode") String statusCode);


    /**
     * 通过uuid查询软件简介
     * @param uuid 唯一标示符
     * @return 实体类
     */
    public IntroduceVO queryIntroduceByUuid(@Param("uuid") String uuid);


    public List<IntroduceVO> queryIntroduceByUserIdAndPage(@Param("userInfo")UserInfo userInfo,  @Param("statusCode")String statusCode, @Param("pageVO") PageVO pageVO);

    public int queryIntroduceCountByUserId(@Param("userInfo")UserInfo userInfo,  @Param("statusCode")String statusCode);

}
