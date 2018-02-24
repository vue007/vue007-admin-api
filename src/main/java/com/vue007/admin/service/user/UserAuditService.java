package com.vue007.admin.service.user;

import com.vue007.admin.domain.conf.ValueConf;
import com.vue007.admin.model.user.UserAudit;
import com.vue007.admin.mapper.user.UserAuditMapper;
import com.vue007.admin.model.user.User;
import com.vue007.admin.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户审核业务层
 *
 * @author Xiangyang
 * @date 2017年10月31日
 */
@Service
public class UserAuditService {

    @Resource
    private UserAuditMapper userAuditMapper;

    @Resource
    private UserService userService;

    public List<UserAudit> findPage(UserAudit userAudit) {
        return userAuditMapper.findPage(userAudit);
    }

    public UserAudit getById(String auditId) {
        return userAuditMapper.getById(auditId);
    }

    public boolean update(UserAudit userAudit) throws Exception {
        if (userAudit == null || StringUtils.isBlank(userAudit.getId()) || null == this.getById(userAudit.getId())) {
            throw new Exception("不存在此审核记录！");
        }

        userAudit.setUpdateTime(new Date());
        userAudit.setUpdateUser(ContextUtil.getSessionUsername());

        return userAuditMapper.update(userAudit) > 0;
    }

    public boolean deleteById(String auditId) throws Exception {
        if (StringUtils.isBlank(auditId) || null == this.getById(auditId)) {
            throw new Exception("不存在此审核记录！");
        }

        return userAuditMapper.deleteById(auditId) > 0;
    }

    public boolean changeStatus(UserAudit userAudit) throws Exception {
        if (null == userAudit || StringUtils.isBlank(userAudit.getId())) {
            throw new Exception("不存在此审核记录！");
        }

        UserAudit entity = this.getById(userAudit.getId());

        if (null == entity) {
            throw new Exception("不存在此审核记录！");
        }

        entity.setStatus(userAudit.getStatus());
        entity.setUpdateUser(ContextUtil.getSessionUsername());
        entity.setUpdateTime(new Date());

        boolean ret =  userAuditMapper.update(entity) > 0;

        //删除冗余的待审核记录，例如存在多条重复的待审核记录，但只以最新的一条为准
        //理论上，数据处于待审核的情况下是不能做修改的
        if (ret) {
            //删除旧的相同类型、相同用户的待审核记录
            entity.setStatus(UserAudit.STATUS_WAIT_AUDIT);
            userAuditMapper.deleteDuplicate(entity);
        }

        //未通过审核处理，数据恢复默认
        if (userAudit.getStatus() == UserAudit.STATUS_NO_PASS) {
            User user = new User();
            user.setId(entity.getUserId());

            if (entity.getType() == UserAudit.TYPE_HEAD_IMG) {
                user.setHeadImg(ValueConf.DEFAULT_USER_HEAD_IMG);
            } else if (entity.getType() == UserAudit.TYPE_NICKNAME) {
                user.setNickname(ValueConf.DEFAULT_USER_NICKNAME);
            }

            userService.update(user);
         }

        return ret;
    }

}
