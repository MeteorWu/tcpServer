package com.vparking.cloud.http.lockOperation.service;

import com.vparking.cloud.tcp.model.messages.BaseMsg;

import java.util.List;

/**
 * Created by Talen on 9/13/17.
 */
public interface ControlService {
    BaseMsg down(String hubId, String lockId) throws Exception;

    BaseMsg up(String hubId, String lockId) throws Exception;

    BaseMsg alarmOn(String hubId, String lockId) throws Exception;

    BaseMsg alarmOff(String hubId, String lockId) throws Exception;

    BaseMsg getLockStatus(String hubId, List<String> lockId) throws Exception;

    BaseMsg getHubStatus(String hubId) throws Exception;
}
