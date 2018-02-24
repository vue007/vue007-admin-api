package com.vue007.admin.domain.shiro;

import com.vue007.admin.model.system.Admin;
import com.vue007.admin.model.system.Permission;
import com.vue007.admin.mapper.system.PermissionMapper;
import com.vue007.admin.model.BaseEntity;
import com.vue007.admin.model.system.Role;
import com.vue007.admin.service.system.AdminService;
import com.vue007.admin.util.ContextUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class JWTRealm extends AuthorizingRealm {

    @Resource
    private AdminService adminService;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = ContextUtil.getSessionUser().getId();
        Admin admin = adminService.getById(userId);

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
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();

        Admin admin = adminService.getByUserName(userName);

        //确定该用户存在 且 可以登录
        if (admin != null && admin.getFlag() != BaseEntity.FLAG_DISABLED) {
            try {
                adminService.updateLoginInfo(admin.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return new SimpleAuthenticationInfo(userName, admin.getPassword(), getName());
        }

        return null;
    }
}
