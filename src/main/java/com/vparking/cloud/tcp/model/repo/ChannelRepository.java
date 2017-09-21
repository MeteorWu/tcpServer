/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vparking.cloud.tcp.model.repo;

import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * Channel Repository using HashMap
 *
 * @author Jibeom Jung
 */
public class ChannelRepository {
    private HashMap<String, Channel> channelCache = new HashMap<>();
    private HashMap<String, String> addressCache = new HashMap<>();

    public ChannelRepository put(String key, Channel value) {
        channelCache.put(key, value);
        return this;
    }

    public Channel get(String key) {
        return channelCache.get(key);
    }

    public void remove(String key) { this.channelCache.remove(key); }

    public int size() {
        return this.channelCache.size();
    }

    public void putAddressCache(String key, String value){
        this.addressCache.put(key, value);
    }
    public String removeAddressCache(String key){
        return this.addressCache.remove(key);
    }
}
