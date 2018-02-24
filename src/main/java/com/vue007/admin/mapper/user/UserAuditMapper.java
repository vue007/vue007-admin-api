package com.vue007.admin.mapper.user;

import com.vue007.admin.model.user.UserAudit;

import java.util.List;

/**
 * 用户审核表 user_audit mapper
 *
 * @author Xiangyang
 * @date 2017年10月31日6
 */
public interface UserAuditMapper {

    List<UserAudit> findPage(UserAudit userAudit);

    UserAudit getById(String id);

    int update(UserAudit userAudit);

    int deleteById(String id);

    int deleteDuplicate(UserAudit userAudit);
}
