package com.vue007.admin.service.system;

import com.vue007.admin.model.system.Permission;
import com.vue007.admin.mapper.system.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> getByMap(Map<String, Object> map) {
        return permissionMapper.getPermissionByMap(map);
    }

    public Permission getById(String id) {
        return permissionMapper.getPermissionById(id);
    }

    public Permission create(Permission permission) {
        permissionMapper.createPermission(permission);
        return permission;
    }

    public Permission update(Permission permission) {
        permissionMapper.updatePermission(permission);
        return permission;
    }

    public int delete(String id) {
        return permissionMapper.deletePermission(id);
    }

}