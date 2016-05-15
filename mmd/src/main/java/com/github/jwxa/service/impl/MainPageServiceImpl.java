package com.github.jwxa.service.impl;

import com.github.jwxa.util.DataSource;
import com.github.jwxa.dao.IMainPageDao;
import com.github.jwxa.datasource.DBContextHolder;
import com.github.jwxa.model.UserInfo;
import com.github.jwxa.service.IMainPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录 首页面 Service层
 * Created by Jwxa on 2015/2/8.
 */
@Service
@Slf4j
public class MainPageServiceImpl implements IMainPageService {
    //不使用lombok包以及插件时日志记录
    //private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger (MainPageServiceImpl.class);

    @Resource
    private IMainPageDao mainPageDao;

    @Override
    public UserInfo verifyUserInfo(UserInfo inputUserInfo) {
        UserInfo userInfo = null;
        try {
            //数据源切换举例
            DBContextHolder.setDBType(DataSource.MMD.toString());
            userInfo = mainPageDao.verifyUserInfo(inputUserInfo);
            if(userInfo!=null){
                log.info("获取用户信息成功");
                return  userInfo;
            }
        } catch (Exception e) {
            log.info("验证用户信息时发生错误");
            log.error(e.getMessage(),e);
            return null;
        }
        return null;
    }
}
