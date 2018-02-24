package com.vue007.admin.mapper.system;


import com.vue007.admin.util.MyMapper;
import com.vue007.admin.model.system.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by akai on 2017/7/9.
 */
public interface RoleMapper extends MyMapper<Role> {

    List<Role> getRoleByMap(Map<String, Object> map);

    Role getRoleById(Integer id);

    Integer createRole(Role role);

    int updateRole(Role role);

    int deleteRole(Integer id);
}