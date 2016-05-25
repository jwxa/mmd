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
 * Project : WebQQCoreAsync Package : iqq.im.action File : GetFriendInfoAction.java Author : solosky < solosky772@qq.com
 * > Created : 2012-9-5 License : Apache License 2.0
 */
package iqq.im.action;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.bean.QQAccount;
import iqq.im.bean.QQAllow;
import iqq.im.bean.QQClientType;
import iqq.im.core.QQConstants;
import iqq.im.core.QQContext;
import iqq.im.event.QQActionEvent;
import iqq.im.http.QQHttpRequest;
import iqq.im.http.QQHttpResponse;
import iqq.im.util.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * 
 * 获取登陆用户信息的请求
 * 
 * @author SG
 */
public class GetSelfInfoAction extends AbstractHttpAction {
    /**
     * <p>
     * Constructor for GetFriendInfoAction.
     * </p>
     * 
     * @param context a {@link iqq.im.core.QQContext} object.
     * @param listener a {@link iqq.im.QQActionListener} object.
     * @param buddy a {@link iqq.im.bean.QQUser} object.
     */
    public GetSelfInfoAction(QQContext context, QQActionListener listener) {
        super(context, listener);
    }

    /** {@inheritDoc} */
    @Override
    protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
        QQHttpRequest req = createHttpRequest("GET", QQConstants.URL_GET_SELF_INFO);
        req.addGetValue("t", System.currentTimeMillis() / 1000 + "");

        req.addHeader("Referer", QQConstants.REFFER);
        return req;
    }

    /** {@inheritDoc} */
    @Override
    protected void onHttpStatusOK(QQHttpResponse response) throws QQException, JSONException {
        JSONObject json = new JSONObject(response.getResponseString());
        if (json.getInt("retcode") == 0) {
            JSONObject obj = json.getJSONObject("result");
            QQAccount qqAccount = getContext().getAccount();
            try {
                qqAccount.setBirthday(DateUtils.parse(obj.getJSONObject("birthday")));
            } catch (ParseException e) {
                qqAccount.setBirthday(null);
            }
            qqAccount.setOccupation(obj.getString("occupation"));
            qqAccount.setPhone(obj.getString("phone"));
            qqAccount.setAllow(QQAllow.values()[obj.getInt("allow")]);
            qqAccount.setCollege(obj.getString("college"));
            if (!obj.isNull("reg_time")) {
                qqAccount.setRegTime(obj.getInt("reg_time"));
            }
            qqAccount.setUin(obj.getLong("uin"));
            qqAccount.setConstel(obj.getInt("constel"));
            qqAccount.setBlood(obj.getInt("blood"));
            qqAccount.setHomepage(obj.getString("homepage"));
//            qqAccount.setStat(obj.getInt("stat"));
            qqAccount.setVipLevel(obj.getInt("vip_info")); // VIP等级 0为非VIP
            qqAccount.setCountry(obj.getString("country"));
            qqAccount.setCity(obj.getString("city"));
            qqAccount.setPersonal(obj.getString("personal"));
            qqAccount.setNickname(obj.getString("nick"));
            qqAccount.setChineseZodiac(obj.getInt("shengxiao"));
            qqAccount.setEmail(obj.getString("email"));
            qqAccount.setProvince(obj.getString("province"));
            qqAccount.setGender(obj.getString("gender"));
            qqAccount.setMobile(obj.getString("mobile"));
            getContext().getSession().setFace(obj.getInt("face"));
            if (!obj.isNull("client_type")) {
                qqAccount.setClientType(QQClientType.valueOfRaw(obj.getInt("client_type")));
            }
        }
        notifyActionEvent(QQActionEvent.Type.EVT_OK, null);
    }
}
