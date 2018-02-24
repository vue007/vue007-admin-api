package com.vue007.admin.service.file;

import com.vue007.admin.mapper.file.FileInfoMapper;
import com.vue007.admin.model.BaseEntity;
import com.vue007.admin.model.file.FileInfo;
import com.vue007.admin.model.system.Admin;
import com.vue007.admin.util.CommUtil;
import com.vue007.admin.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Akai
 * @date 2017年10月17日
 */
@Service
public class FileInfoService {

    @Resource
    private FileInfoMapper fileInfoMapper;

    public List<FileInfo> findPage(FileInfo file) {
        return fileInfoMapper.findPage(file);
    }
    public FileInfo getById(String id) {
        return fileInfoMapper.getById(id);
    }

    /**
     * 新增管理员
     * @param fileInfo 管理员对象
     * @return 新增成功，返回管理员数据库实体，失败，返回 null
     * @throws Exception 用户名已存在
     */
    public FileInfo create(FileInfo fileInfo) {

        Date nowDate = new Date();
        Admin sessionAdmin = ContextUtil.getSessionAdmin();

        fileInfo.setId(CommUtil.random());

        fileInfo.setCreateAdmin(sessionAdmin.getId());
        fileInfo.setCreateUser(sessionAdmin.getUsername());
        fileInfo.setUpdateUser(sessionAdmin.getUsername());
        fileInfo.setCreateTime(nowDate);
        fileInfo.setUpdateTime(nowDate);
        fileInfo.setFlag(BaseEntity.FLAG_ENABLED);

        return fileInfoMapper.create(fileInfo) > 0 ? fileInfo : null;
    }

    public boolean update(FileInfo file) throws Exception {

        file.setUpdateTime(new Date());
        file.setUpdateUser(ContextUtil.getSessionUsername());

        return fileInfoMapper.update(file) > 0;
    }


    public boolean setFlag(FileInfo file) throws Exception {
        if (file == null || StringUtils.isBlank(file.getId()) || null == this.getById(file.getId())) {
            throw new Exception("该文件不存在！");
        }
        Admin sessionAdmin = ContextUtil.getSessionUser();
        file.setUpdateUser(sessionAdmin.getUsername());
        file.setUpdateTime(new Date());
        return fileInfoMapper.setFlag(file) > 0;
    }
}