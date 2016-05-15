package com.github.jwxa.dao;

import com.github.jwxa.model.UserInfo;

/**
 * Created by Jwxa on 2015/2/8.
 */
public interface IMainPageDao {
    /**
     * 从userinfo表中查询是否有这个用户
     * @param userInfo 用户信息实体类
     * @return
     */
    public UserInfo verifyUserInfo(UserInfo userInfo) ;

}
