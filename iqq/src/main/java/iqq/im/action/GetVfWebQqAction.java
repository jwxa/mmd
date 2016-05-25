/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

/**
 * Project  : WebQQCoreAsync
 * Package  : iqq.im.action
 * File     : LoginAction.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : 2012-9-2
 * License  : Apache License 2.0
 */
package iqq.im.action;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.QQException.QQErrorCode;
import iqq.im.core.QQConstants;
import iqq.im.core.QQContext;
import iqq.im.core.QQService;
import iqq.im.core.QQSession;
import iqq.im.event.QQActionEvent;
import iqq.im.http.QQHttpRequest;
import iqq.im.http.QQHttpResponse;
import iqq.im.service.HttpService;
import org.json.JSONException;
import org.json.JSONObject;

public class GetVfWebQqAction extends AbstractHttpAction {

    /**
     * <p>Constructor for GetVfWebQqAction.</p>
     *
     * @param context  a {@link iqq.im.core.QQContext} object.
     * @param listener a {@link iqq.im.QQActionListener} object.
     * 
     */
    public GetVfWebQqAction(QQContext context, QQActionListener listener) {
        super(context, listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QQHttpRequest onBuildRequest() throws QQException, JSONException {
        QQHttpRequest req = createHttpRequest("GET", QQConstants.URL_GET_VF_WEBQQ);
        HttpService httpService = (HttpService) getContext().getSerivce(QQService.Type.HTTP);
        QQSession session = getContext().getSession();
        
        req.addGetValue("ptwebqq", httpService.getCookie("ptwebqq", QQConstants.URL_GET_VF_WEBQQ).getValue());
        req.addGetValue("clientid", session.getClientId() + "");
        req.addGetValue("psessionid", "");
        req.addGetValue("t", System.currentTimeMillis()/1000+"");
        
        req.addHeader("Referer", QQConstants.REFFER);
        return req;
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onHttpStatusOK(QQHttpResponse response) throws QQException, JSONException {
        //{"retcode":0,"result":{"vfwebqq":"79e0e1f4eb788aed699b5acb42d0b738ec4f70117fe21ee8c431b409f14b83edce2ee7492fd41b88"}}
        JSONObject json = new JSONObject(response.getResponseString());
        QQSession session = getContext().getSession();
        if (json.getInt("retcode") == 0) {
            JSONObject ret = json.getJSONObject("result");
            session.setVfwebqq(ret.getString("vfwebqq"));
            notifyActionEvent(QQActionEvent.Type.EVT_OK, null);
        } else {
            notifyActionEvent(QQActionEvent.Type.EVT_ERROR, new QQException(QQErrorCode.INVALID_RESPONSE));    //TODO ..
        }
    }

}
