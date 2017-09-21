package com.vparking.cloud.tcp.model.messages;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
public class BatteryAlertMsg extends BaseMsg{

    private int batteryPercentage;

    public BatteryAlertMsg() {
    }

    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }
}
