package com.vue007.admin.controller.system;

import com.vue007.admin.domain.conf.ValueConf;
import com.vue007.admin.domain.jsonp.StatusCode;
import com.vue007.admin.model.system.Admin;
import com.vue007.admin.service.system.AdminService;
import com.vue007.admin.util.ContextUtil;
import com.vue007.admin.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akai on 2017-07-03 16:24:59.
 * 登陆控制器
 */
@Api(description = "后台用户登录控制器")
@RestController
public class LoginController {

    @Resource
    private AdminService adminService;

    @ApiOperation(
            value = "后台登录接口",
            response = Admin.class,
            notes = "使用用户名和密码登录后台，需要的参数：用户名 username, 密码 password"
    )
    @ApiResponses({
            @ApiResponse(code = StatusCode.CODE_SUCCESS, response = Boolean.class, message = "登录成功"),
            @ApiResponse(code = StatusCode.CODE_ERROR, message = "登录失败")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody Admin admin) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        token.setRememberMe(false);

        try {
            subject.login(token);
        } catch (Exception e) {
            if (e instanceof IncorrectCredentialsException) {
                throw new Exception("密码错误");
            } else if (e instanceof UnknownAccountException) {
                throw new Exception("账号不存在");
            } else {
                throw new Exception("登录失败");
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("token", getToken(admin.getUsername()));
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("default_avatar", ValueConf.DEFAULT_USER_HEAD_IMG);
        result.put("config", configMap);

        return result;
    }

    @ApiOperation(
            value = "获取当前登录用户信息",
            notes = "获取当前登录用户信息"
    )
    @GetMapping("/api/login/info")
    public Admin currentAdmin() {
        return ContextUtil.getSessionUser();
    }

    @ApiOperation(
            value = "后台用户注销接口",
            notes = "后台用户注销会话接口"
    )
    @GetMapping("/logout")
    public void logout() {

    }

    private String getToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        Admin admin = adminService.getByUserName(username);
        //设置用户ID
        claims.put(ValueConf.SESSION_USER_ID, admin.getId());
        //获取key
        String key = JWTUtil.getKey(admin);
        return JWTUtil.creatCompact(key, claims);
    }
}