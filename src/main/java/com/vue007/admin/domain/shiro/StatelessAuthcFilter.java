package com.vue007.admin.domain.shiro;

import com.alibaba.fastjson.JSON;
import com.vue007.admin.domain.conf.ValueConf;
import com.vue007.admin.domain.jsonp.ResponseMessage;
import com.vue007.admin.domain.jsonp.StatusCode;
import com.vue007.admin.mapper.system.AdminMapper;
import com.vue007.admin.model.system.Admin;
import com.vue007.admin.util.ContextUtil;
import com.vue007.admin.util.JWTUtil;
import com.vue007.admin.util.SpringUtil;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("statelessAuthcFilter")
public class StatelessAuthcFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        resp.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        resp.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, X-Requested-With, Content-Type, Accept, x-token");

        if (req.getMethod().equalsIgnoreCase("options")) {
            return false;
        }

        ResponseMessage<Void> responseMessage = new ResponseMessage<>();

        String accessToken = getAccessToken((HttpServletRequest) request);

        if (StringUtils.isBlank(accessToken)) {
            responseMessage.setMessage("缺少 Token!");
            responseMessage.setCode(StatusCode.CODE_ACCESS_ERROR);
            resp.getOutputStream().write(JSON.toJSONString(responseMessage).getBytes());
            return false;
        }

        SqlSession sqlSession = SpringUtil.getBeanByClass(SqlSession.class);
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);


        try {
            //ExpiredJwtException JWT已过期
            //SignatureException JWT可能被篡改
            //获取userId
            String userId = getUserIdForToken(accessToken);
            Admin user = adminMapper.getById(userId);
            //获取用户的密钥
            String key = JWTUtil.getKey(user);
            Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken).getBody();
            // 将session用户放进ThreadLocal中 方便后续业务代码获取
            ContextUtil.setSessionUser(user);
        } catch (Exception e) {
            ContextUtil.setSessionUser(null);
            responseMessage.setMessage("认证失败，请重新登录！");
            responseMessage.setCode(StatusCode.CODE_ACCESS_ERROR);
            resp.getOutputStream().write(JSON.toJSONString(responseMessage).getBytes());
            return false;
        }

//        responseMessage.setMessage("权限不足");
//        responseMessage.setCode(StatusCode.CODE_AUTH_ERROR);


//        resp.getOutputStream().write(JSON.toJSONString(responseMessage).getBytes());

        return true;
    }

    private String getUserIdForToken(String accessToken) {
        String payload = JWTUtil.parseBase64UrlEncodedPayload(accessToken);
        Map claims = JSON.parseObject(payload, Map.class);
        Object value = claims.get(ValueConf.SESSION_USER_ID);

        return String.valueOf(value);
    }

    private String getAccessToken(HttpServletRequest request) {
        return request.getHeader(ValueConf.TOKEN);
    }
}
