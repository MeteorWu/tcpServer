package com.vparking.cloud.tcp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */
public class BaseMsg implements Serializable {
    public enum MsgType {
        PING, LOGIN, REPLY, UP, DOWN, STATUS, ALARM_ON, ALARM_OFF
    }

    private MsgType type;
    private String hubId;
    private String lockId;

    public BaseMsg(){}

    BaseMsg(List<String> list) {

        if(list.size()<4)
            return;

        this.hubId = list.get(0);
        this.lockId = list.get(1);
        this.type = MsgType.valueOf(list.get(2));
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public String getHubId() {
        return hubId;
    }

    public void setHubId(String hubId) {
        this.hubId = hubId;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }
}
