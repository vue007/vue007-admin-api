package com.vue007.admin.service.system;

import com.vue007.admin.model.system.Admin;
import com.vue007.admin.util.ContextUtil;
import com.vue007.admin.util.SecurityUtil;
import com.vue007.admin.mapper.system.AdminMapper;
import com.vue007.admin.model.BaseEntity;
import com.vue007.admin.util.CommUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xiangyang
 * @date 2017年10月17日
 */
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    public List<Admin> findPage(Admin admin) {
        return adminMapper.findPage(admin);
    }

    public Admin getById(String id) {
        return adminMapper.getById(id);
    }

    /**
     * 新增管理员
     * @param admin 管理员对象
     * @return 新增成功，返回管理员数据库实体，失败，返回 null
     * @throws Exception 用户名已存在
     */
    public Admin create(Admin admin) throws Exception {
        if (null != adminMapper.getByUserName(admin.getUsername())) {
            throw new Exception("用户名为 " + admin.getUsername() + " 的管理员已经存在！");
        }

        Date nowDate = new Date();
        String currentUser = ContextUtil.getSessionUsername();

        admin.setId(CommUtil.random());

        admin.setCreateUser(currentUser);
        admin.setUpdateUser(currentUser);
        admin.setCreateTime(nowDate);
        admin.setUpdateTime(nowDate);
        admin.setFlag(BaseEntity.FLAG_ENABLED);
        admin.setPassword(SecurityUtil.encryptPassword(admin.getPassword()));

        //昵称为空，则使用用户名代替
        if (StringUtils.isBlank(admin.getNickname())) {
            admin.setNickname(admin.getUsername());
        }

        return adminMapper.create(admin) > 0 ? admin : null;
    }

    public boolean update(Admin admin) throws Exception {
        if (admin == null || StringUtils.isBlank(admin.getId()) || null == this.getById(admin.getId())) {
            throw new Exception("不存在此管理员！");
        }

        if (null != adminMapper.getByUsernameNoId(admin.getUsername(), admin.getId())) {
            throw new Exception("用户名 " + admin.getUsername() + " 已存在！");
        }

        if (StringUtils.isNotBlank(admin.getPassword())) {
            admin.setPassword(SecurityUtil.encryptPassword(admin.getPassword()));
        }

        admin.setUsername(null);
        admin.setUpdateTime(new Date());
        admin.setUpdateUser(ContextUtil.getSessionUsername());

        return adminMapper.update(admin) > 0;
    }

    public boolean updateLoginInfo(String id) {
        Admin admin = new Admin();
        admin.setLastLoginTime(new Date());
        admin.setId(id);
        return adminMapper.update(admin) > 0;
    }

    public Admin getByUserName(String userName) {
        return adminMapper.getByUserName(userName);
    }

    public boolean setFlag(Admin admin) throws Exception {
        if (admin == null || StringUtils.isBlank(admin.getId()) || null == this.getById(admin.getId())) {
            throw new Exception("不存在此管理员！");
        }
        Admin sessionAdmin = ContextUtil.getSessionUser();
        admin.setUpdateUser(sessionAdmin.getUsername());
        admin.setUpdateTime(new Date());
        return adminMapper.setFlag(admin) > 0;
    }
}