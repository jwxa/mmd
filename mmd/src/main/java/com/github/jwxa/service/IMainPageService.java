package com.github.jwxa.service;

import com.github.jwxa.model.UserInfo;

/**
 * 主页面Service接口
 * Created by Jwxa on 2015/2/8.
 */
public interface IMainPageService {

    /**
     * 验证用户信息
     * @param userInfo 用户信息实体类
     * @return
     */
    public UserInfo verifyUserInfo(UserInfo userInfo);

}
