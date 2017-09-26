package com.vparking.cloud.tcp.model.messages;

/**
 * Created by Administrator on 2017/8/11 0011.
 */
public class LockStatus {

    public enum Status{
        UP, DOWN, MIDDLE, FAULT
    }

    private String lockId;
    private Integer battery;
    private Status status;
    private Boolean alert;

    public LockStatus(){
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getAlert() {
        return alert;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }
}
