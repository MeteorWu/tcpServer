package com.vparking.cloud.tcp.model;

import javafx.beans.binding.IntegerBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */
public class StatusMsg extends BaseMsg {

    public enum LockStatus{
        UP, DOWN, MIDDLE, RAISING, LOWERING, FAULT
    }

    private Integer battery;
    private LockStatus status;
    private Boolean alert;

    public StatusMsg (List<String> list){
        super(list);
        List<String> msgList = Arrays.asList(list.get(3).split(";"));
        setStatus(LockStatus.values()[Integer.parseInt(msgList.get(0))]);
        setAlert(msgList.get(1).equals("0"));
        setBattery(Integer.parseInt(msgList.get(2)));
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public LockStatus getStatus() {
        return status;
    }

    public void setStatus(LockStatus status) {
        this.status = status;
    }

    public Boolean getAlert() {
        return alert;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }
}
