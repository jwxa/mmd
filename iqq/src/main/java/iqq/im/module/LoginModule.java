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
 * Project : WebQQCore Package : iqq.im.module File : CoreModule.java Author : solosky < solosky772@qq.com > Created :
 * 2012-7-31 License : Apache License 2.0
 */
package iqq.im.module;

import iqq.im.QQActionListener;
import iqq.im.action.*;
import iqq.im.bean.QQStatus;
import iqq.im.event.QQActionFuture;

/**
 * 登录模块，处理登录和退出
 * 
 * @author solosky
 */
public class LoginModule extends AbstractModule {

    /**
     * <p>
     * get QRCode
     * </p>
     * 
     * @param listener a {@link iqq.im.QQActionListener} object.
     * @return a {@link iqq.im.event.QQActionFuture} object.
     */
    public QQActionFuture getQRCode(QQActionListener listener) {
        return pushHttpAction(new GetQRCodeAction(getContext(), listener));
    }

    /**
     * <p>
     * check QRCode
     * </p>
     *
     * @param listener a {@link iqq.im.QQActionListener} object.
     * @return a {@link iqq.im.event.QQActionFuture} object.
     */
    public QQActionFuture checkQRCode(QQActionListener listener) {
        return pushHttpAction(new CheckQRCodeAction(getContext(), listener));
    }

    /**
     * <p>
     * channelLogin.
     * </p>
     *
     * @param status a {@link iqq.im.bean.QQStatus} object.
     * @param listener a {@link iqq.im.QQActionListener} object.
     * @return a {@link iqq.im.event.QQActionFuture} object.
     */
    public QQActionFuture channelLogin(QQStatus status, QQActionListener listener) {
        return pushHttpAction(new ChannelLoginAction(getContext(), listener, status));
    }

    /**
     * <p>
     * pollMsg.
     * </p>
     *
     * @param listener a {@link iqq.im.QQActionListener} object.
     * @return a {@link iqq.im.event.QQActionFuture} object.
     */
    public QQActionFuture pollMsg(QQActionListener listener) {
        return pushHttpAction(new PollMsgAction(getContext(), listener));
    }

    /**
     * <p>
     * logout.
     * </p>
     *
     * @param listener a {@link iqq.im.QQActionListener} object.
     * @return a {@link iqq.im.event.QQActionFuture} object.
     */
    public QQActionFuture logout(QQActionListener listener) {
        return pushHttpAction(new WebLogoutAction(getContext(), listener));
    }

    /**
     * <p>
     * checkLoginSig.
     * </p>
     *
     * @param checkUrl a {@link String} object.
     * @param listener a {@link iqq.im.QQActionListener} object.
     * @return a {@link iqq.im.event.QQActionFuture} object.
     */
    public QQActionFuture checkLoginSig(String checkUrl, QQActionListener listener) {
        return pushHttpAction(new CheckLoginSigAction(getContext(), listener, checkUrl));
    }

    /**
     * <p>
     * getVfWebQq.
     * </p>
     *
     * @param status a {@link iqq.im.bean.QQStatus} object.
     * @param listener a {@link iqq.im.QQActionListener} object.
     * @return a {@link iqq.im.event.QQActionFuture} object.
     */
    public QQActionFuture getVfWebQq(QQActionListener listener) {
        return pushHttpAction(new GetVfWebQqAction(getContext(), listener));
    }
}
