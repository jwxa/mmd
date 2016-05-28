package com.github.jwxa.service.impl;

import com.github.jwxa.service.IIqqService;
import com.github.jwxa.util.TulinApiUtil;
import iqq.im.*;
import iqq.im.actor.ThreadActorDispatcher;
import iqq.im.bean.QQGroup;
import iqq.im.bean.QQMsg;
import iqq.im.bean.QQUser;
import iqq.im.bean.content.FontItem;
import iqq.im.bean.content.TextItem;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQNotifyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * qq模块<br>
 * User: Jwxa<br>
 * Date: 2016/5/25.
 */
@Service
@Slf4j
public class IqqServiceImpl implements IIqqService {

    private static QQClient mClient = new WebQQClient(new QQNotifyListener() {
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
            log.error(e.getMessage(), e);
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
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return resultStr[0];
    }

    @Override
    public void beginPollMsg() {
        mClient.beginPollMsg();
    }


    private static void revMsg(QQMsg revMsg) {
        log.info("收到来自{}的qq信息", revMsg.getFrom());
        if (revMsg.getText() != null && revMsg.getText().startsWith("#")) {
            switch (revMsg.getType()) {
                case BUDDY_MSG:
                    sendMsg2User(revMsg);
                    break;
                case GROUP_MSG:
                    String text = revMsg.getText().trim().substring(1);
                    String msg = TulinApiUtil.getMsgFromTulin(text);
                    sendMsg2Group(revMsg, msg);
                    break;
                case DISCUZ_MSG:
//                sendDiscuz(revMsg.getDiscuz());
            }
        }
    }

    public static void sendMsg2User(QQMsg revMsg) {
        QQUser user = revMsg.getFrom();
        log.info("sendMsg :{}", user);
        // 组装QQ消息发送回去
        QQMsg sendMsg = new QQMsg();
        sendMsg.setTo(user); // QQ好友UIN
        sendMsg.setType(QQMsg.Type.BUDDY_MSG); // 发送类型为好友
        // QQ内容
        sendMsg.addContentItem(new TextItem("hello" + new Random().nextInt())); // 添加文本内容
//        sendMsg.addContentItem(new FaceItem(74)); // QQ id为0的表情
        sendMsg.addContentItem(new FontItem()); // 使用默认字体
        mClient.sendMsg(sendMsg, null); // 调用接口发送消息
    }

    /**
     * 发送群组信息
     *
     * @param revMsg
     */
    public static void sendMsg2Group(QQMsg revMsg, String msg) {
        QQGroup group = revMsg.getGroup();
        // 组装QQ消息发送回去
        QQMsg sendMsg = new QQMsg();
        sendMsg.setGroup(group); // QQ好友UIN
        sendMsg.setType(QQMsg.Type.GROUP_MSG); // 发送类型为好友
        // QQ内容
        sendMsg.addContentItem(new TextItem(msg)); // 添加文本内容
        sendMsg.addContentItem(new FontItem()); // 使用默认字体

        mClient.sendMsg(sendMsg, new QQActionListener() {
            @Override
            public void onActionEvent(QQActionEvent event) {
                log.info("sendMsg {}" , event);
            }
        }); // 调用接口发送消息
    }


}
