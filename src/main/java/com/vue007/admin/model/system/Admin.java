package com.vue007.admin.model.system;

import com.vue007.admin.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * admin 表实体
 * @author Xiangyang
 * @date 2017年10月17日
 */
@ApiModel(value = "后台用户 Admin")
public class Admin extends BaseEntity {

    private String nickname;
    private String username;
    private String password;
    @ApiParam(hidden = true)
    private Date lastLoginTime;

    @ApiParam(hidden = true)
    @Transient
    private List<Role> roles;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
