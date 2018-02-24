package com.vue007.admin.mapper.system;

import com.vue007.admin.model.system.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Akai
 * @date 2018-01-12 15:42:14
 */
public interface AdminMapper {
    List<Admin> findPage(Admin admin);

    Admin getById(String id);

    Admin getByUserName(String username);

    Admin getByUsernameNoId(@Param("username") String username, @Param("excludeId") String excludeId);

    int create(Admin admin);

    int update(Admin admin);

    int setFlag(Admin admin);
}
