package com.vue007.admin.domain.shiro;

import com.alibaba.fastjson.JSON;
import com.vue007.admin.domain.conf.ValueConf;
import com.vue007.admin.domain.jsonp.ResponseMessage;
import com.vue007.admin.domain.jsonp.StatusCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Shiro 权限过滤结果处理
 * @author Xiangyang
 * @date 2017年10月17日
 */
public class ShiroFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletResponse resp = (HttpServletResponse) response;
        resp.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        Session session = SecurityUtils.getSubject().getSession();
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        //登录操作
        if (isLoginRequest(request, response) && isLoginSubmission(request, response)) {
            return executeLogin(request, response);
        }

        ResponseMessage<Void> responseMessage = new ResponseMessage<>();

        if (session == null || session.getAttribute(ValueConf.SESSION_USER_KEY) == null) {
            responseMessage.setMessage("请登录");
            responseMessage.setCode(StatusCode.CODE_ACCESS_ERROR);
        } else {
            responseMessage.setMessage("权限不足");
            responseMessage.setCode(StatusCode.CODE_AUTH_ERROR);
        }

        resp.getOutputStream().write(JSON.toJSONString(responseMessage).getBytes());

        return false;
    }

}
