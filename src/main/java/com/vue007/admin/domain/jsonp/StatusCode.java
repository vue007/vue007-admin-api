package com.vue007.admin.domain.jsonp;

/**
 * 响应状态码
 * @author Xiangyang
 * @date 2017年10月19日
 */
public class StatusCode {
    //成功
    public final static int CODE_SUCCESS = 2000;

    //未登录
    public final static int CODE_ACCESS_ERROR = 4001;

    //没有权限
    public final static int CODE_AUTH_ERROR = 4003;

    //通用错误
    public final static int CODE_ERROR = 5000;
}
