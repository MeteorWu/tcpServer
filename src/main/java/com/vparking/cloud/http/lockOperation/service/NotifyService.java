package com.vparking.cloud.http.lockOperation.service;

import com.vparking.cloud.tcp.model.messages.BaseMsg;
import com.vparking.cloud.tcp.model.messages.Message;

/**
 * Created by Administrator on 2017/9/16 0016.
 */
public interface NotifyService {
    void notify(BaseMsg msg) throws Exception;
}
