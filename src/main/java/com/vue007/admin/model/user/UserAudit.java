package com.vue007.admin.model.user;

import com.vue007.admin.model.BaseEntity;

public class UserAudit extends BaseEntity {

    //变更内容类型：头像
    public final static int TYPE_HEAD_IMG = 1;
    //变更内容类型：昵称
    public final static int TYPE_NICKNAME  =2;

    //状态：待审核
    public final static int STATUS_WAIT_AUDIT = 0;
    //状态：通过
    public final static int STATUS_PASS = 1;
    //状态：不通过
    public final static int STATUS_NO_PASS = 2;

    private String userId;
    private int type;
    private String nickname;
    private String headImg;
    private int status;
    private String userNickname;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}
