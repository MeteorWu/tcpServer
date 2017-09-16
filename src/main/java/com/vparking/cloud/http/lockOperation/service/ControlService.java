package com.vparking.cloud.http.lockOperation.service;

/**
 * Created by Talen on 9/13/17.
 */
public interface ControlService {
    void down(String hubId, String lockId) throws Exception;

    void up(String hubId, String lockId) throws Exception;
}
