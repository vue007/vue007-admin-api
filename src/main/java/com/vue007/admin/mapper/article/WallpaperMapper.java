package com.vue007.admin.mapper.article;

import com.vue007.admin.model.article.Wallpaper;
import java.util.List;
/**
 * @author anquan
 * desc: 壁纸
 * date 2018-01-22 13:53
 */
public interface WallpaperMapper {
    
    List<Wallpaper> findPage(Wallpaper record);

    Wallpaper getById(String id);

    int create(Wallpaper record);

    int update(Wallpaper record);

    int deleteById(String id);
}
