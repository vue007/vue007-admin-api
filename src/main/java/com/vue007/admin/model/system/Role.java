package com.vue007.admin.model.system;

import com.vue007.admin.model.BaseEntity;

public class Role extends BaseEntity {
    private String name;
    private Integer roleLevel;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + this.getId() +
                ", name=" + name +
                ", roleLevel=" + roleLevel +
                ", description=" + description +
                '}';
    }
}