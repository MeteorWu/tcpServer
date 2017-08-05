package com.vparking.cloud.common.http.response;

import com.vparking.cloud.common.http.exception.BusinessException;

/**
 * Created by Administrator on 2017/7/26 0026.
 */
public class Response<T> {

    private T body;
    private int code;
    private String message;
    private String exceptionDescription;

    public static <T> Response<T> success() {
        return new Response<T>(ReturnStatus.SUCCESS);
    }

    public static <T> Response<T> success(T body) {
        return new Response<T>(body, ReturnStatus.SUCCESS);
    }

    public static <T> Response<T> fail() {
        return new Response<T>(ReturnStatus.BUSINESS_ERROR);
    }

    public static <T> Response<T> fail(ReturnStatus status) {
        return new Response<T>(status);
    }

    public static <T> Response<T> fail(ReturnStatus status, Exception e) {
        Response<T> response = new Response<T>(status);
        if (status.getValue() <= ReturnStatus.BUSINESS_ERROR.getValue()) {
            response.setExceptionDescription(e.getMessage());
        }
        return response;
    }

    public static <T> Response<T> customStatusAndValues(ReturnStatus status, T body) {
        return new Response<T>(body, status);
    }

    public static <T> Response<T> customStatus(ReturnStatus status) {
        return new Response<T>(status);
    }

    public static <T> Response<T> fail(BusinessException be, T body) {
        return new Response<T>(be, body);
    }

    private Response(ReturnStatus returnStatus) {
        this.code = returnStatus.getValue();
        this.message = returnStatus.getDescription();
    }

    private Response(T body, ReturnStatus returnStatus) {
        super();
        this.body = body;
        this.code = returnStatus.getValue();
        this.message = returnStatus.getDescription();
    }

    private Response(BusinessException be, T body) {
        this.code = be.getCode();
        this.message = be.getDescription();
        this.body = body;
    }

    public T getbody() {
        return body;
    }

    public int getCode() {
        if (code <= 0) {
            code = ReturnStatus.SUCCESS.getValue();
        }
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

}
