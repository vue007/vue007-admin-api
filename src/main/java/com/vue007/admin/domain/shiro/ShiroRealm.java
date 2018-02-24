package com.vue007.admin.domain.shiro;


import com.vue007.admin.domain.conf.ValueConf;
import com.vue007.admin.mapper.system.PermissionMapper;
import com.vue007.admin.model.BaseEntity;
import com.vue007.admin.model.system.Admin;
import com.vue007.admin.model.system.Permission;
import com.vue007.admin.model.system.Role;
import com.vue007.admin.service.system.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by akai on 2017/7/3.
 */
public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AdminService adminService;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("doGetAuthorizationInfo+" + principalCollection.toString());
        Admin admin = adminService.getByUserName((String) principalCollection.getPrimaryPrincipal());

        //把principals放session中 key=userId value=principals
        SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(admin.getId()), SecurityUtils.getSubject().getPrincipals());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //赋予角色
        for (Role userRole : admin.getRoles()) {
            info.addRole(userRole.getName());
        }
        //赋予权限
        for (Permission permission : permissionMapper.getPermissionByAdminId(admin.getId())) {
            //if(StringUtils.isNotBlank(permission.getPermCode()))
            info.addStringPermission(permission.getName());
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo +" + authenticationToken.toString());

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();

        Admin admin = adminService.getByUserName(token.getUsername());

        //确定该用户存在 且 可以登录
        if (admin != null && admin.getFlag() != BaseEntity.FLAG_DISABLED) {
            //byte[] salt = Encodes.decodeHex(user.getSalt());
            //ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginName(), user.getName());
            Admin login_user = new Admin();
            login_user.setId(admin.getId());
            login_user.setLastLoginTime(new Date());
            //设置用户session
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(ValueConf.SESSION_USER_KEY, admin);
            try {
                adminService.update(login_user);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return new SimpleAuthenticationInfo(userName, admin.getPassword(), getName());
        }

        return null;
    }

}
