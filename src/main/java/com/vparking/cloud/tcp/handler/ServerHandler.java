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

import com.google.gson.Gson;
import com.vparking.cloud.http.lockOperation.service.NotifyService;
import com.vparking.cloud.tcp.model.messages.*;
import com.vparking.cloud.tcp.model.repo.ChannelRepository;
import com.vparking.cloud.tcp.model.repo.ResponseRepository;
import com.vparking.cloud.tcp.model.responses.ResponseFuture;
import io.netty.channel.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.*;

/**
 * event handler to process receiving messages
 *
 * @author Jibeom Jung
 */
@Component
@Qualifier("serverHandler")
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private String loginPassword = "password";
    private final long maxResponseAge = 100000;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    // keep a list of all pending responses so that they can be mapped to the approriate future
    private ResponseRepository responseMap;

    @Autowired
    private NotifyService notifyService;

    // thread pool for handling responses
    private final ThreadPoolExecutor responseExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    // scheduler to cleanup the responseMap
    private final ScheduledExecutorService responseMapCleaner = Executors.newScheduledThreadPool(1);

    private static Logger logger = Logger.getLogger(ServerHandler.class.getName());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Assert.notNull(this.channelRepository, "[Assertion failed] - ChannelRepository is required; it must not be null");

        responseMapCleaner.scheduleAtFixedRate(() -> {
            responseMap.getResponseMap().forEach((key, value) -> {
                if (value.age() > maxResponseAge) {
                    responseMap.remove(key).completeExceptionally(new Exception("Response timed-out"));
                }
            });
        }, maxResponseAge, maxResponseAge, TimeUnit.MILLISECONDS);

        ctx.fireChannelActive();

        ctx.writeAndFlush("Welcome to VParking, Please Login \n");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        String msg = (String) obj;

        logger.debug(msg);
        Gson gson = new Gson();
        BaseMsg baseMsg  = gson.fromJson(msg, BaseMsg.class);

        // login
        if(channelRepository.get(baseMsg.getHubId()) == null){
            if(baseMsg.getType().equals(BaseMsg.MsgType.LOGIN)){
                LoginMsg loginMsg = gson.fromJson(msg, LoginMsg.class);
                if(loginMsg.getPassword().equals(loginPassword)) {
                    channelRepository.put(loginMsg.getHubId(), ctx.channel());
                    ctx.writeAndFlush("Login Success!! \n");
                    return;
                }
            }else {
                return;
            }
        }

        // process message based on types
        switch (baseMsg.getType()){
            case PING:
                channelRepository.get(baseMsg.getHubId()).writeAndFlush("Ping Success");
                break;
            case UP:
            case DOWN:
            case ALARM_ON:
            case ALARM_OFF:
                ControlMsg controlMsg = gson.fromJson(msg, ControlMsg.class);
                completeResponse(controlMsg);
                break;
            case STATUS:
                StatusMsg statusMsg = gson.fromJson(msg, StatusMsg.class);
                completeResponse(statusMsg);
                break;
            case NOTIFY_BATTERY_ALERT:
                BatteryAlertMsg batteryAlertMsg = gson.fromJson(msg, BatteryAlertMsg.class);
                notifyService.notify(batteryAlertMsg);
                break;
            case NOTIFY_DOWN_COMPLETE:
            case NOTIFY_UP_COMPLETE:
            case NOTIFY_HUB_FAULT:
            case NOTIFY_LOCK_FAULT:
            case NOTIFY_ALARM_ON:
            case NOTIFY_ALARM_OFF:
                notifyService.notify(baseMsg);
                ctx.writeAndFlush("Notify Success!! \n");
                break;
            default:
                break;

        }
    }

    private void completeResponse(BaseMsg response) throws Exception{
        // lookup the ResponseFuture for this response
        ResponseFuture future = responseMap.remove(response.id());
        if (future != null) {
            response.setReceivedTS();
            future.complete(response);
        }
        else {
            logger.error("Recieved a response with no matching future");
        }
    }

    public BaseMsg sendRequest(BaseMsg request) throws Exception {

        Gson gson = new Gson();
        ResponseFuture response = new ResponseFuture();

        channelRepository.get(request.getHubId()).writeAndFlush(gson.toJson(request)).addListener((ChannelFutureListener) (ChannelFuture future) -> {
            if (future.isSuccess()) {
                request.setSentTS();
                // add the ResponseFuture to the map so that we can later map the respsone to it
                responseMap.put(request.id(), response);
            }
            else {
                logger.error("Communication error", future.cause());
                response.completeExceptionally(future.cause());
            }
        });
        return response.get();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(cause.getMessage(), cause);
        //ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){

        // remove address cache and channel cache
        String address = ctx.channel().remoteAddress().toString();
        String hubId = this.channelRepository.removeAddressCache(address);
        this.channelRepository.remove(hubId);

        logger.debug("Bind Channel Count is " + this.channelRepository.size());
    }

    public void setChannelRepository(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }
}
