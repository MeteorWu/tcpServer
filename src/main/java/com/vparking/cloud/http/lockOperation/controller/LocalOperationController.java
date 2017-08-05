package com.vparking.cloud.http.lockOperation.controller;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.vparking.cloud.common.http.BaseController;
import com.vparking.cloud.common.http.response.Response;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * Created by Administrator on 2017/7/26 0026.
 */
@RestController
@Api(value = "/operation", description = "指令")
@RequestMapping("/api/lock/operation")
public class LocalOperationController extends BaseController{

    @RequestMapping(value = "/down", method = RequestMethod.POST)
    @ApiOperation(value = "降锁", notes = "发送降锁指令, \n" +
            "每个business应该对应多个hub, " +
            "每个hub对应多个lock, " +
            "每次指令由服务器发送给需要降锁对应的hub, " +
            "然后由hub转发给需要降锁的lock")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功完成"),
            @ApiResponse(code = 200, message = "10004 参数错误")})
    public Response<?> down(
            @ApiParam(required = false, value = "hubId") @RequestParam String hubId,
            @ApiParam(required = true, value = "lockId") @RequestParam String lockId
    ) throws Exception{

        return Response.success();
    }
}
