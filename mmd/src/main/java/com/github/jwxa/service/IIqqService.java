package com.github.jwxa.service;

import iqq.im.event.QQActionEvent;

import java.awt.image.BufferedImage;

/**
 * 标题<br>
 * User: Jwxa<br>
 * Date: 2016/5/25.
 */
public interface IIqqService {

    /**
     * 获取二维码
     * 异步转同步的写法
     * @return
     */
    BufferedImage getQRCode();

    /**
     * 检查二维码状态
     * @return
     */
    String checkQRCode();
}
