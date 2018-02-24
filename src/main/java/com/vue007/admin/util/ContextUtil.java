package com.vue007.admin.util;

import com.vue007.admin.model.system.Admin;

public class ContextUtil {

    private static ThreadLocal<Admin> sessionUser = new ThreadLocal<>();

    public static void setSessionUser(Admin admin) {
        sessionUser.set(admin);
    }

    /**
     * 获取当前session 用户admin实体
     * @return Admin Session 实例
     */
    public static Admin getSessionUser() {
        return sessionUser.get();
    }

    /**
     * 获取当前管理员用户名
     * @return 管理员用户名
     */
    public static String getSessionUsername() {
        Admin admin = getSessionUser();

        return null == admin ? null : admin.getUsername();
    }
    /**
     * 获取当前管理员用户名
     * @return 管理员用户名
     */
    public static Admin getSessionAdmin() {
        Admin admin = getSessionUser();

        return admin;
    }
}
