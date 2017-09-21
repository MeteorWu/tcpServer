package com.vparking.cloud.tcp.model.repo;

import com.vparking.cloud.tcp.model.responses.ResponseFuture;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2017/9/18 0018.
 */
@Component
public class ResponseRepository {
    // keep a list of all pending responses so that they can be mapped to the approriate future
    private ConcurrentMap<String, ResponseFuture> responseMap = new ConcurrentHashMap<>();

    public ResponseRepository put(String key, ResponseFuture value) {
        responseMap.put(key, value);
        return this;
    }
    public ConcurrentMap<String, ResponseFuture> getResponseMap (){
        return this.responseMap;
    }
    public ResponseFuture get(String key) {
        return responseMap.get(key);
    }

    public ResponseFuture remove(String key) { return responseMap.remove(key); }

    public int size() {
        return this.responseMap.size();
    }
}
