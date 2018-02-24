package com.vue007.admin.domain.jsonp;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by Xie Gengcai on 2017/6/27.
 */
public class ResponseMessage<T> {
    private int code;
    private String message;
    private Date responseTime = Calendar.getInstance().getTime();//响应时间
    private T data;

    public ResponseMessage() {
    }

    public ResponseMessage(int code) {
        this.code = code;
    }

    public ResponseMessage(T data) {
        this(StatusCode.CODE_SUCCESS, data);
    }

    public ResponseMessage(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }
}