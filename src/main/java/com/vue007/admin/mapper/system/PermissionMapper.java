package com.vue007.admin.mapper.system;


import com.vue007.admin.model.system.Permission;
import com.vue007.admin.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by akai on 2017/7/9.
 */
public interface PermissionMapper extends MyMapper<Permission> {

    List<Permission> getPermissionByMap(Map<String, Object> map);

    Permission getPermissionById(String id);

    Integer createPermission(Permission permission);

    int updatePermission(Permission permission);

    int deletePermission(String id);

    List<Permission> getPermissionList();

    List<Permission> getPermissionByAdminId(String adminId);

}