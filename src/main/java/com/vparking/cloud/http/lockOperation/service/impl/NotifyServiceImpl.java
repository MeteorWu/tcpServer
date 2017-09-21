package com.vparking.cloud.http.lockOperation.service.impl;

import com.google.gson.Gson;
import com.vparking.cloud.common.http.rest.RestUtils;
import com.vparking.cloud.common.http.rest.bean.RestRequestBean;
import com.vparking.cloud.http.lockOperation.service.NotifyService;
import com.vparking.cloud.tcp.model.messages.BaseMsg;
import org.springframework.http.HttpMethod;
import com.vparking.cloud.common.http.response.Response;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/16 0016.
 */
@Service
public class NotifyServiceImpl implements NotifyService {
    @Override
    public void notify(BaseMsg msg) throws Exception {

        String url = "";
        Gson gson = new Gson();

        RestRequestBean request = new RestRequestBean();
        request.setMethod(HttpMethod.POST);
        request.setParameters(gson.fromJson(gson.toJson(msg), Map.class));
        request.setUrl(url);
        request.setResponseType(Response.success().getClass());
        Response<BaseMsg> response = (Response<BaseMsg>) RestUtils.callApi(request);

    }
}
