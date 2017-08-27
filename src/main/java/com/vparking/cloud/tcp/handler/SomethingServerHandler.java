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
package com.vparking.cloud.tcp.handler;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.vparking.cloud.tcp.ChannelRepository;
import com.vparking.cloud.tcp.model.BaseMsg;
import com.vparking.cloud.tcp.model.ControlMsg;
import com.vparking.cloud.tcp.model.LoginMsg;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * event handler to process receiving messages
 *
 * @author Jibeom Jung
 */
@Component
@Qualifier("somethingServerHandler")
@ChannelHandler.Sharable
public class SomethingServerHandler extends ChannelInboundHandlerAdapter {

    private String loginPassword = "password";

    @Autowired
    private ChannelRepository channelRepository;

    private static Logger logger = Logger.getLogger(SomethingServerHandler.class.getName());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Assert.notNull(this.channelRepository, "[Assertion failed] - ChannelRepository is required; it must not be null");

        ctx.fireChannelActive();

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        String msg = (String) obj;

        logger.debug(msg);
        Gson gson = new Gson();
        BaseMsg baseMsg;
        try {
            baseMsg = gson.fromJson(msg, BaseMsg.class);
        }catch (Exception e){
            logger.error(e);
            return;
        }

        // login
        if(channelRepository.get(baseMsg.getHubId()) == null){
            if(baseMsg.getType().equals(BaseMsg.MsgType.LOGIN)){
                LoginMsg loginMsg = gson.fromJson(msg, LoginMsg.class);
                if(loginMsg.getPassword().equals(loginPassword)) {
                    channelRepository.put(loginMsg.getHubId(), ctx.channel());
                    return;
                }
            }else {
                return;
            }
        }


        switch (baseMsg.getType()){
            case PING:
                channelRepository.get(baseMsg.getHubId()).writeAndFlush("Ping Success");
                break;
            case UP:
                ControlMsg controlMsg = gson.fromJson(msg, ControlMsg.class);

                break;
            case DOWN:
                break;
            case REPLY:
                break;
            case STATUS:
                break;
            case ALARM_ON:
                break;
            case ALARM_OFF:
                break;
            default:
                    break;

        }



    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(cause.getMessage(), cause);
        //ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        Assert.notNull(this.channelRepository, "[Assertion failed] - ChannelRepository is required; it must not be null");
        Assert.notNull(ctx);

        String channelKey = ctx.channel().remoteAddress().toString();
        this.channelRepository.remove(channelKey);

        logger.debug("Binded Channel Count is " + this.channelRepository.size());
    }

    public void setChannelRepository(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }
}
