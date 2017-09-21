package com.vparking.cloud.http.lockOperation.controller;

import com.vparking.cloud.common.http.BaseController;
import com.vparking.cloud.common.http.response.Response;
import com.vparking.cloud.http.lockOperation.service.ControlService;
import com.vparking.cloud.tcp.handler.ServerHandler;
import com.vparking.cloud.tcp.model.messages.BaseMsg;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26 0026.
 */
@RestController
@Api(value = "/operation", description = "指令")
@RequestMapping("/api/lock/operation")
public class ControlController extends BaseController{

    @Autowired
    private ControlService controlService;

    @RequestMapping(value = "/down", method = RequestMethod.POST)
    @ApiOperation(value = "降锁", notes = "发送降锁指令, \n" +
            "每个business应该对应多个hub, " +
            "每个hub对应多个lock, " +
            "每次指令由服务器发送给需要降锁对应的hub, " +
            "然后由hub转发给需要降锁的lock")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功完成"),
            @ApiResponse(code = 200, message = "10004 参数错误")})
    public Response<BaseMsg> down(
            @ApiParam(required = true, value = "hubId") @RequestParam String hubId,
            @ApiParam(required = true, value = "lockId") @RequestParam String lockId
    ) throws Exception{

        BaseMsg response = controlService.down(hubId, lockId);

        return Response.success(response);
    }

    @RequestMapping(value = "/up", method = RequestMethod.POST)
    @ApiOperation(value = "升鎖", notes = "发送升鎖指令, \n" +
            "每个business应该对应多个hub, " +
            "每个hub对应多个lock, " +
            "每次指令由服务器发送给需要降锁对应的hub, " +
            "然后由hub转发给需要降锁的lock")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功完成"),
            @ApiResponse(code = 200, message = "10004 参数错误")})
    public Response<BaseMsg> up(
            @ApiParam(required = true, value = "hubId") @RequestParam String hubId,
            @ApiParam(required = true, value = "lockId") @RequestParam String lockId
    ) throws Exception{

        BaseMsg response = controlService.up(hubId, lockId);

        return Response.success(response);
    }

    @RequestMapping(value = "/alarmOn", method = RequestMethod.POST)
    @ApiOperation(value = "响警报", notes = "发送响起警报指令, \n" +
            "每个business应该对应多个hub, " +
            "每个hub对应多个lock, " +
            "每次指令由服务器发送给需要降锁对应的hub, " +
            "然后由hub转发给需要降锁的lock")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功完成"),
            @ApiResponse(code = 200, message = "10004 参数错误")})
    public Response<BaseMsg> alarmOn(
            @ApiParam(required = true, value = "hubId") @RequestParam String hubId,
            @ApiParam(required = true, value = "lockId") @RequestParam String lockId
    ) throws Exception{

        BaseMsg response = controlService.alarmOn(hubId, lockId);

        return Response.success(response);
    }

    @RequestMapping(value = "/alarmOff", method = RequestMethod.POST)
    @ApiOperation(value = "关闭警报", notes = "发送关闭警报指令, \n" +
            "每个business应该对应多个hub, " +
            "每个hub对应多个lock, " +
            "每次指令由服务器发送给需要降锁对应的hub, " +
            "然后由hub转发给需要降锁的lock")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功完成"),
            @ApiResponse(code = 200, message = "10004 参数错误")})
    public Response<BaseMsg> alarmOff(
            @ApiParam(required = true, value = "hubId") @RequestParam String hubId,
            @ApiParam(required = true, value = "lockId") @RequestParam String lockId
    ) throws Exception{

        BaseMsg response = controlService.alarmOn(hubId, lockId);

        return Response.success(response);
    }

    @RequestMapping(value = "/lockStatus", method = RequestMethod.POST)
    @ApiOperation(value = "获取锁状态列表", notes = "发送获取锁状态指令, \n" +
            "每个business应该对应多个hub, " +
            "每个hub对应多个lock, " +
            "每次指令由服务器发送给需要降锁对应的hub, " +
            "然后由hub转发给需要降锁的lock")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功完成"),
            @ApiResponse(code = 200, message = "10004 参数错误")})
    public Response<BaseMsg> getLockStatus(
            @ApiParam(required = true, value = "hubId") @RequestParam String hubId,
            @ApiParam(required = true, value = "lockIds") @RequestParam List<String> lockId
    ) throws Exception{

        BaseMsg response = controlService.getLockStatus(hubId, lockId);

        return Response.success(response);
    }
}
