package com.vue007.admin.model.system;

import com.vue007.admin.model.BaseEntity;

public class Permission extends BaseEntity {
    private String name;
    private String url;
    private String method;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geturl() {
        return url;
    }

    public void seturl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + this.getId() +
                ", name=" + name +
                ", url=" + url +
                ", method=" + method +
                ", description=" + description +
                '}';
    }
}