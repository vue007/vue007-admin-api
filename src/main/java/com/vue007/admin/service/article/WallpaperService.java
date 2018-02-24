package com.vue007.admin.service.article;

import com.vue007.admin.mapper.article.WallpaperMapper;
import com.vue007.admin.model.article.Wallpaper;
import com.vue007.admin.util.CommUtil;
import com.vue007.admin.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * @author anquan
 * desc: 壁纸
 * date 2018-01-22 13:55
 */
@Service
public class WallpaperService {

    @Resource
    private WallpaperMapper wallpaperMapper;

    public List<Wallpaper> findPage(Wallpaper record){
        return wallpaperMapper.findPage(record);
    }

    public Wallpaper getById(String id){
        return wallpaperMapper.getById(id);
    }

    public boolean create(Wallpaper record) throws Exception {
        Date nowDate = new Date();
        String user = ContextUtil.getSessionUsername();

        record.setId(CommUtil.random());
        record.setCreateUser(user);
        record.setUpdateUser(user);
        record.setCreateTime(nowDate);
        record.setUpdateTime(nowDate);
        record.setFlag(1);

        return wallpaperMapper.create(record) > 0;
    }

    public boolean update(Wallpaper wallpaper) throws Exception {
        if (wallpaper == null || StringUtils.isBlank(wallpaper.getId()) || null == this.getById(wallpaper.getId())) {
            throw new Exception("不存在此壁纸！");
        }
        wallpaper.setUpdateUser(ContextUtil.getSessionUsername());
        wallpaper.setUpdateTime(new Date());

        return wallpaperMapper.update(wallpaper) > 0;
    }

    public boolean changeFlag(Wallpaper record) throws Exception {
        if (record == null || StringUtils.isBlank(record.getId())) {
            throw new Exception("ID不能为空！");
        }
        Wallpaper entity = wallpaperMapper.getById(record.getId());
        if(entity == null){
            throw new Exception("数据不存在！");
        }
        entity.setId(record.getId());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(ContextUtil.getSessionUsername());
        entity.setFlag(record.getFlag());

        return wallpaperMapper.update(record) > 0;
    }

    public boolean changeStatus(Wallpaper record) throws Exception {
        if (record == null || StringUtils.isBlank(record.getId())) {
            throw new Exception("不存在此壁纸！");
        }
        Wallpaper entity = this.getById(record.getId());
        if (null == entity) {
            throw new Exception("不存在此壁纸！");
        }

        entity.setUpdateTime(new Date());
        entity.setUpdateUser(ContextUtil.getSessionUsername());
        entity.setStatus(record.getStatus());

        return wallpaperMapper.update(entity) > 0;
    }
}
