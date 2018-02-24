package com.vue007.admin.mapper.user;

import com.vue007.admin.model.user.User;

import java.util.List;

/**
 * user 表 Mapper
 *
 * @author Xiangyang
 * @date 2017年10月23日
 */
public interface UserMapper {

    List<User> findPage(User user);

    User getById(String userId);

    int create(User user);

    int update(User user);

    int changeStatus(User user);

    int deleteById(String userId);

}
