package com.vue007.admin.service.user;

import com.vue007.admin.domain.conf.ValueConf;
import com.vue007.admin.mapper.user.UserMapper;
import com.vue007.admin.model.user.User;
import com.vue007.admin.util.CommUtil;
import com.vue007.admin.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户模块业务逻辑层
 *
 * @author Xiangyang
 * @date 2017年10月23日
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> findPage(User user) {
        return userMapper.findPage(user);
    }

    public User getById(String id) {
        return userMapper.getById(id);
    }

    public boolean create(User user) {
        Date nowDate = new Date();
        String currentUser = ContextUtil.getSessionUsername();
        user.setId(CommUtil.random());
        user.setCreateUser(currentUser);
        user.setUpdateUser(currentUser);
        user.setCreateTime(nowDate);
        user.setUpdateTime(nowDate);
        //手动添加的用户默认为系统用户
        user.setUserType(User.TYPE_SYSTEM);

        //默认头像
        if (StringUtils.isBlank(user.getHeadImg())) {
            user.setHeadImg(ValueConf.DEFAULT_USER_HEAD_IMG);
        }

        return userMapper.create(user) > 0;
    }

    public boolean update(User user) throws Exception {
        if (user == null || StringUtils.isBlank(user.getId()) || null == this.getById(user.getId())) {
            throw new Exception("用户不存在！");
        }

        user.setUpdateTime(new Date());
        user.setUpdateUser(ContextUtil.getSessionUsername());

        return userMapper.update(user) > 0;
    }

    public boolean deleteById(String userId) throws Exception {
        if (StringUtils.isBlank(userId) || null == this.getById(userId)) {
            throw new Exception("用户不存在！");
        }
        return userMapper.deleteById(userId) > 0;
    }

    public boolean changeStatus(String userId, int status) throws Exception {
        if (StringUtils.isBlank(userId)) {
            throw new Exception("用户不存在！");
        }

        User user = this.getById(userId);

        if (null == user) {
            throw new Exception("用户不存在！");
        }

        user.setStatus(status);
        user.setUpdateUser(ContextUtil.getSessionUsername());
        user.setUpdateTime(new Date());

        return userMapper.changeStatus(user) > 0;
    }

    public boolean disable(String userId) throws Exception {
        return this.changeStatus(userId, User.STATUS_DISABLED);
    }

    public boolean enabled(String userId) throws Exception {
        return this.changeStatus(userId, User.STATUS_ENABLED);
    }
}
