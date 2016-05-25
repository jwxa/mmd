/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

/**
 * Project : WebQQCoreAsync Package : iqq.im.module File : ProcModule.java Author : solosky < solosky772@qq.com >
 * Created : 2012-9-2 License : Apache License 2.0
 */
package iqq.im.module;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.QQException.QQErrorCode;
import iqq.im.bean.QQAccount;
import iqq.im.bean.QQStatus;
import iqq.im.core.QQModule;
import iqq.im.core.QQSession;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionFuture;
import iqq.im.event.QQNotifyEvent;
import iqq.im.event.future.ProcActionFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 处理整体登陆逻辑
 * 
 * @author solosky
 */
public class ProcModule extends AbstractModule {
    private static final Logger LOG = LoggerFactory.getLogger(ProcModule.class);

    /**
     * <p>
     * check qrcode
     * </p>
     * 
     * @param listener
     * @return
     */
    public QQActionFuture checkQRCode(final QQActionListener listener) {
        final ProcActionFuture future = new ProcActionFuture(listener, true);
        LoginModule login = getContext().getModule(QQModule.Type.LOGIN);
        login.checkQRCode(new QQActionListener() {
            @Override
            public void onActionEvent(QQActionEvent event) {
                if (event.getType() == QQActionEvent.Type.EVT_OK) {
                    doCheckLoginSig((String) event.getTarget(), future);
                } else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
                    future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR, event.getTarget());
                }
            }
        });
        return future;
    }

    private void doCheckLoginSig(String checkSigUrl, final ProcActionFuture future) {
        LoginModule login = getContext().getModule(QQModule.Type.LOGIN);
        login.checkLoginSig(checkSigUrl, new QQActionListener() {
            @Override
            public void onActionEvent(QQActionEvent event) {
                if (event.getType() == QQActionEvent.Type.EVT_OK) {
                    getVfWebQq(future);
                } else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
                    future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR, (QQException) event.getTarget());
                }

            }
        });
    }

    private void getVfWebQq(final ProcActionFuture future) {
        LoginModule login = getContext().getModule(QQModule.Type.LOGIN);
        login.getVfWebQq(new QQActionListener() {
            @Override
            public void onActionEvent(QQActionEvent event) {
                if (event.getType() == QQActionEvent.Type.EVT_OK) {
                    doChannelLogin(future);
                } else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
                    future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR, (QQException) event.getTarget());
                }
            }
        });
    }

    private void doChannelLogin(final ProcActionFuture future) {
        LoginModule login = getContext().getModule(QQModule.Type.LOGIN);
        login.channelLogin(getContext().getAccount().getStatus(), new QQActionListener() {
            public void onActionEvent(QQActionEvent event) {
                if (event.getType() == QQActionEvent.Type.EVT_OK) {
                    future.notifyActionEvent(QQActionEvent.Type.EVT_OK, null);
                } else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
                    future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR, (QQException) event.getTarget());
                }
            }
        });
    }

    /**
     * <p>
     * relogin.
     * </p>
     * 
     * @param status a {@link iqq.im.bean.QQStatus} object.
     * @param listener a {@link iqq.im.QQActionListener} object.
     * @return a {@link iqq.im.event.QQActionFuture} object.
     */
    public QQActionFuture relogin(QQStatus status, final QQActionListener listener) {
        getContext().getAccount().setStatus(status);
        getContext().getSession().setState(QQSession.State.LOGINING);
        LOG.info("iqq client relogin...");
        final ProcActionFuture future = new ProcActionFuture(listener, true);
        doChannelLogin(future);
        return future;
    }

    /**
     * <p>
     * relogin.
     * </p>
     */
    public void relogin() {
        QQSession session = getContext().getSession();
        if (session.getState() == QQSession.State.LOGINING)
            return;
        // 登录失效，重新登录
        relogin(getContext().getAccount().getStatus(), new QQActionListener() {
            @Override
            public void onActionEvent(QQActionEvent event) {
                if (event.getType() == QQActionEvent.Type.EVT_OK) {
                    // 重新登录成功重新POLL
                    getContext().fireNotify(new QQNotifyEvent(QQNotifyEvent.Type.RELOGIN_SUCCESS, null));
                } else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
                    getContext().fireNotify(new QQNotifyEvent(QQNotifyEvent.Type.UNKNOWN_ERROR, null));
                }
            }

        });
    }

    /**
     * <p>
     * doPollMsg.
     * </p>
     */
    public void doPollMsg() {
        final LoginModule login = getContext().getModule(QQModule.Type.LOGIN);
        login.pollMsg(new QQActionListener() {
            public void onActionEvent(QQActionEvent event) {
                // 回调通知事件函数
                if (event.getType() == QQActionEvent.Type.EVT_OK) {
                    @SuppressWarnings("unchecked")
                    List<QQNotifyEvent> events = (List<QQNotifyEvent>) event.getTarget();
                    for (QQNotifyEvent evt : events) {
                        getContext().fireNotify(evt);
                    }

                    // 准备提交下次poll请求
                    QQSession session = getContext().getSession();
                    if (session.getState() == QQSession.State.ONLINE) {
                        doPollMsg();
                    } else if (session.getState() != QQSession.State.KICKED) {
                        relogin();
                    }
                } else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
                    QQSession session = getContext().getSession();
                    QQAccount account = getContext().getAccount();
                    session.setState(QQSession.State.OFFLINE);
                    account.setStatus(QQStatus.OFFLINE);
                    // 因为自带了错误重试机制，如果出现了错误回调，表明已经超时多次均失败，这里直接返回网络错误的异常
                    QQException ex = (QQException) event.getTarget();
                    QQErrorCode code = ex.getError();
                    if (code == QQErrorCode.INVALID_LOGIN_AUTH) {
                        relogin();
                    } else if (code == QQErrorCode.IO_ERROR || code == QQErrorCode.IO_TIMEOUT) {
                        // 粗线了IO异常，直接报网络错误
                        getContext().fireNotify(new QQNotifyEvent(QQNotifyEvent.Type.NET_ERROR, ex));
                    } else {
                        LOG.warn("poll msg unexpected error, ignore it ...", ex);
                        relogin();
                        doPollMsg();
                    }
                } else if (event.getType() == QQActionEvent.Type.EVT_RETRY) {
                    System.err.println("Poll Retry:" + this);
                    LOG.warn("poll msg error, retrying....", (QQException) event.getTarget());
                }
            }
        });
    }

    /**
     * <p>
     * doLogout.
     * </p>
     *
     * @param listener a {@link iqq.im.QQActionListener} object.
     * @return a {@link iqq.im.event.QQActionFuture} object.
     */
    public QQActionFuture doLogout(QQActionListener listener) {
        LoginModule login = (LoginModule) getContext().getModule(QQModule.Type.LOGIN);
        return login.logout(listener);
    }
}
