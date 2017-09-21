package com.vparking.cloud.tcp.model.messages;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/11 0011.
 */
public class BaseMsg extends Message implements Serializable {
    public enum MsgType {
        // Downstream Control
        LOGIN,                      // 登录命令
        PING,                       // ping
        STATUS,                     // 获取锁状态命令
        UP,                         // 升锁命令
        DOWN,                       // 降锁命令
        ALARM_ON,                   // 响警报命令
        ALARM_OFF,                  // 关闭警报命令
        // Upstream Notify
        NOTIFY_UP_COMPLETE,         // 通知升锁完成
        NOTIFY_DOWN_COMPLETE,       // 通知降锁完成
        NOTIFY_BATTERY_ALERT,       // 通知电量报警
        NOTIFY_LOCK_FAULT,          // 通知锁异常
        NOTIFY_HUB_FAULT,           // 通知HUB异常
        NOTIFY_ALARM_ON,            // 通知警报响起
        NOTIFY_ALARM_OFF            // 通知警报关闭
    }

    private MsgType type;
    private String hubId;
    private String lockId;

    public BaseMsg(){}

    public BaseMsg(MsgType type){
        this.setType(type);
    }

    public BaseMsg(MsgType type, String hubId){
        this.setType(type);
        this.setHubId(hubId);
    }

    public BaseMsg(MsgType type, String hubId, String lockId){
        this.setType(type);
        this.setHubId(hubId);
        this.setLockId(lockId);
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
