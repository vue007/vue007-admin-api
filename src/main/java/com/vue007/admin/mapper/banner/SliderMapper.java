package com.vue007.admin.mapper.banner;

import com.vue007.admin.model.banner.Slider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * banner管理
 */
public interface SliderMapper {

    List<Slider> findPage(Slider record);

    Slider getByid(String id);

    int create(Slider record);

    int update(Slider record);

    int deleteById(String id);

    int sortBatch(@Param("sortList") List<Slider> sortList);

}