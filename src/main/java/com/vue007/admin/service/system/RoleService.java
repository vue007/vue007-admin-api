package com.vue007.admin.service.system;

import com.vue007.admin.mapper.system.RoleMapper;
import com.vue007.admin.model.system.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getByMap(Map<String, Object> map) {
        return roleMapper.getRoleByMap(map);
    }

    public Role getById(Integer id) {
        return roleMapper.getRoleById(id);
    }

    public Role create(Role role) {
        roleMapper.createRole(role);
        return role;
    }

    public Role update(Role role) {
        roleMapper.updateRole(role);
        return role;
    }

    public int delete(Integer id) {
        return roleMapper.deleteRole(id);
    }

}