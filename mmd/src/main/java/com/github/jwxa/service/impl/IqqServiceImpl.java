package com.github.jwxa.service.impl;

import com.github.jwxa.service.IIqqService;
import iqq.im.*;
import iqq.im.actor.ThreadActorDispatcher;
import iqq.im.bean.QQMsg;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQNotifyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;

/**
 * qq模块<br>
 * User: Jwxa<br>
 * Date: 2016/5/25.
 */
@Service
@Slf4j
public class IqqServiceImpl implements IIqqService {

    static QQClient mClient = new WebQQClient(new QQNotifyListener() {
        @Override
        public void onNotifyEvent(QQNotifyEvent event) {
            switch (event.getType()) {
                case CHAT_MSG:
                    QQMsg revMsg = (QQMsg) event.getTarget();
                    revMsg(revMsg);
                    break;
            }
        }
    }, new ThreadActorDispatcher());


    @Override
    public BufferedImage getQRCode() {
        mClient.setHttpUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
        // 获取二维码
        final BufferedImage[] verify = {null};
        final CountDownLatch latch = new CountDownLatch(1);
        mClient.getQRcode(new QQActionListener() {
            @Override
            public void onActionEvent(QQActionEvent event) {
                if (event.getType() == QQActionEvent.Type.EVT_OK) {
                    try {
                        verify[0] = (BufferedImage) event.getTarget();
                        latch.countDown();
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                } else {
                    log.info("获取二维码失败");
                }
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        return verify[0];
    }

    @Override
    public String checkQRCode() {
        final String[] resultStr = new String[1];
        final CountDownLatch latch = new CountDownLatch(1);
        // 检查二维码状态
        mClient.checkQRCode(new QQActionListener() {
            @Override
            public void onActionEvent(QQActionEvent event) {
                QQActionEvent.Type result = event.getType();
                switch (result) {
                    case EVT_OK:
                        resultStr[0] = "登录成功";
                        break;
                    case EVT_ERROR:
                        QQException ex = (QQException) (event.getTarget());
                        QQException.QQErrorCode code = ex.getError();
                        switch (code) {
                            // 二维码有效,等待用户扫描
                            case QRCODE_OK:
                                resultStr[0] = "二维码未失效,等待用户扫描";
                            // 二维码已经扫描,等用户允许登录
                                break;
                            case QRCODE_AUTH:
                                resultStr[0] = "二维码认证中,等用户允许登录";
                                break;
                            case QRCODE_INVALID:
                                resultStr[0] = "二维码失效";
                                break;
                        }
                        break;
                }

            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        return resultStr[0];
    }


    private static void revMsg(QQMsg revMsg) {
        log.info("收到来自{}的qq信息", revMsg.getFrom());
        switch (revMsg.getType()) {
            case BUDDY_MSG:
//                sendMsg(revMsg.getFrom());
                break;
            case GROUP_MSG:
//                sendMsg(revMsg.getGroup());
                break;
            case DISCUZ_MSG:
//                sendDiscuz(revMsg.getDiscuz());
        }
    }
}
