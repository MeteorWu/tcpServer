package com.vparking.cloud.http.lockOperation.service.impl;

import com.vparking.cloud.http.lockOperation.service.ControlService;
import com.vparking.cloud.tcp.handler.ServerHandler;
import com.vparking.cloud.tcp.model.messages.BaseMsg;
import com.vparking.cloud.tcp.model.messages.LockStatus;
import com.vparking.cloud.tcp.model.messages.StatusMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/16 0016.
 */
@Service
public class ControlServiceImpl implements ControlService {
    @Autowired
    ServerHandler handler;

    @Override
    public BaseMsg down(String hubId, String lockId) throws Exception {
        BaseMsg msg = new BaseMsg(BaseMsg.MsgType.DOWN, hubId, lockId);
        return handler.sendRequest(msg);
    }

    @Override
    public BaseMsg up(String hubId, String lockId) throws Exception {
        BaseMsg msg = new BaseMsg(BaseMsg.MsgType.UP, hubId, lockId);
        return handler.sendRequest(msg);
    }

    @Override
    public BaseMsg alarmOn(String hubId, String lockId) throws Exception {

        BaseMsg msg = new BaseMsg(BaseMsg.MsgType.ALARM_ON, hubId, lockId);
        return handler.sendRequest(msg);
    }

    @Override
    public BaseMsg alarmOff(String hubId, String lockId) throws Exception {

        BaseMsg msg = new BaseMsg(BaseMsg.MsgType.ALARM_OFF, hubId, lockId);
        return handler.sendRequest(msg);
    }

    @Override
    public BaseMsg getLockStatus(String hubId, List<String> lockIds) throws Exception {
        StatusMsg msg = new StatusMsg(BaseMsg.MsgType.STATUS, hubId, lockIds);
        List<LockStatus> list = new ArrayList<>();
        LockStatus status = new LockStatus();
        status.setAlert(true);
        status.setBattery(1);
        status.setStatus(LockStatus.Status.FAULT);
        list.add(status);
        msg.setStatuses(list);
        return handler.sendRequest(msg);
    }

    @Override
    public BaseMsg getHubStatus(String hubId) throws Exception {
        return null;
    }
}
