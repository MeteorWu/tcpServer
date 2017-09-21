package com.vparking.cloud.tcp.model.messages;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
public class StatusMsg extends BaseMsg {
    List<String> lockIds;
    List<LockStatus> statuses;

    public StatusMsg(MsgType type, String hubId, List<String> lockIds) {
        super(type, hubId);
        this.lockIds = lockIds;
    }

    public StatusMsg(MsgType type, List<String> lockIds, List<LockStatus> statuses) {
        super(type);
        this.lockIds = lockIds;
        this.statuses = statuses;
    }

    public StatusMsg(MsgType type, String hubId, String lockId, List<String> lockIds, List<LockStatus> statuses) {
        super(type, hubId, lockId);
        this.lockIds = lockIds;
        this.statuses = statuses;
    }

    public List<String> getLockIds() {
        return lockIds;
    }

    public void setLockIds(List<String> lockIds) {
        this.lockIds = lockIds;
    }

    public List<LockStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<LockStatus> statuses) {
        this.statuses = statuses;
    }
}
