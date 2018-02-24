package com.vue007.admin.model.user;

import com.vue007.admin.model.BaseEntity;

/**
 * user 表实体
 * @author Xiangyang
 * @date 2017年10月23日
 */
public class User extends BaseEntity {

    //用户类型：系统内部用户
    public final static int TYPE_SYSTEM = 0;
    //用户类型：注册用户
    public final static int TYPE_REGISTER = 1;

    //用户状态：禁用
    public final static int STATUS_DISABLED = 0;
    //用户状态：可用
    public final static int STATUS_ENABLED = 1;

    private String nickname;
    private String headImg;
    private int userType;

    public static int getTypeSystem() {
        return TYPE_SYSTEM;
    }

    public static int getTypeRegister() {
        return TYPE_REGISTER;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
