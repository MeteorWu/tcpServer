package com.vparking.cloud.common.http.response;

/**
 * Created by Administrator on 2017/7/26 0026.
 */
public enum ReturnStatus {

    SUCCESS(ReturnConstant.SUCCESS.value, ReturnConstant.SUCCESS.desc),
    INTERNAL_SERVER_ERROR(ReturnConstant.INTERNAL_SERVER_ERROR.value, ReturnConstant.INTERNAL_SERVER_ERROR.desc),
    NOT_FOUND(ReturnConstant.NOT_FOUND.value, ReturnConstant.NOT_FOUND.desc),


    FAULT_ACCESS_TOKEN(ReturnConstant.FAULT_ACCESS_TOKEN.value, ReturnConstant.FAULT_ACCESS_TOKEN.desc),
    NO_ACCESS_TOKEN(ReturnConstant.NO_ACCESS_TOKEN.value, ReturnConstant.NO_ACCESS_TOKEN.desc),
    EXPIRE_ACCESS_TOKEN(ReturnConstant.EXPIRE_ACCESS_TOKEN.value, ReturnConstant.EXPIRE_ACCESS_TOKEN.desc),
    KEY_IN_USED(ReturnConstant.KEY_IN_USED.value, ReturnConstant.KEY_IN_USED.desc),
    BUSINESS_ERROR(ReturnConstant.BUSINESS_ERROR.value, ReturnConstant.BUSINESS_ERROR.desc),

    // Common
    PARAMETER_EMPTY(ReturnConstant.PARAMETER_EMPTY.value, ReturnConstant.PARAMETER_EMPTY.desc),
    PARAMETER_INCORRECT(ReturnConstant.PARAMETER_INCORRECT.value, ReturnConstant.PARAMETER_INCORRECT.desc),
    CONFIG_KEY_REPEAT(ReturnConstant.CONFIG_KEY_REPEAT.value, ReturnConstant.CONFIG_KEY_REPEAT.desc),
    CONFIG_DETAIL_REPEAT(ReturnConstant.CONFIG_DETAIL_REPEAT.value, ReturnConstant.CONFIG_DETAIL_REPEAT.desc),
    GLOBAL_CONFIG_FANGLE_ERROR(ReturnConstant.GLOBAL_CONFIG_FANGLE_ERROR.value, ReturnConstant.GLOBAL_CONFIG_FANGLE_ERROR.desc),
    GLOBAL_CONFIG_USER_BEHAVIOR_VALID_ERROR(ReturnConstant.GLOBAL_CONFIG_USER_BEHAVIOR_VALID_ERROR.value, ReturnConstant.GLOBAL_CONFIG_USER_BEHAVIOR_VALID_ERROR.desc),
    GLOBAL_CONFIG_USER_BEHAVIOR_INVALID_ERROR(ReturnConstant.GLOBAL_CONFIG_USER_BEHAVIOR_INVALID_ERROR.value, ReturnConstant.GLOBAL_CONFIG_USER_BEHAVIOR_INVALID_ERROR.desc),
    BUSINESS_DISTRICTID_NOT_EXIST(ReturnConstant.BUSINESS_DISTRICTID_NOT_EXIST.value, ReturnConstant.BUSINESS_DISTRICTID_NOT_EXIST.desc),

    // lock operation

    UPLOAD_ERROR(ReturnConstant.UPLOAD_ERROR.value, ReturnConstant.UPLOAD_ERROR.desc),
    NULL_POINT_ERROR(ReturnConstant.NULL_POINT_ERROR.value, ReturnConstant.NULL_POINT_ERROR.desc),
    SQL_ERROR(ReturnConstant.SQL_ERROR.value, ReturnConstant.SQL_ERROR.desc),
    FILE_NOT_FOUND_ERROR(ReturnConstant.FILE_NOT_FOUND_ERROR.value, ReturnConstant.FILE_NOT_FOUND_ERROR.desc),
    OTHER_ERROR(ReturnConstant.OTHER_ERROR.value, ReturnConstant.OTHER_ERROR.desc);



    private final int value;
    private String description;

    ReturnStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
