package com.vue007.admin.service.banner;

import com.vue007.admin.mapper.banner.SliderMapper;
import com.vue007.admin.model.banner.Slider;
import com.vue007.admin.util.CommUtil;
import com.vue007.admin.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * @author anquan
 * desc: Banner管理
 * date 2018-01-16 10:40
 */
@Service
public class SliderService {

    @Resource
    private SliderMapper sliderMapper;

    public List<Slider> findPage(Slider record){
        return sliderMapper.findPage(record);
    }

    public Slider getByid(String id){
        return sliderMapper.getByid(id);
    }

    public boolean create(Slider record) throws Exception {
        Date nowDate = new Date();
        String user = ContextUtil.getSessionUsername();

        record.setSliderId(CommUtil.random());
        record.setPosterLittle(record.getPoster());
        record.setCreateUser(user);
        record.setUpdateUser(user);
        record.setCreateTime(nowDate);
        record.setUpdateTime(nowDate);
        record.setFlag(1);

        return sliderMapper.create(record) > 0;
    }

    public boolean update(Slider record) throws Exception {
        record.setPosterLittle(record.getPoster());
        record.setUpdateUser(ContextUtil.getSessionUsername());
        record.setUpdateTime(new Date());

        return sliderMapper.update(record) > 0;
    }

    public boolean changeFlag(Slider record) throws Exception {
        if (record == null || StringUtils.isBlank(record.getSliderId())) {
            throw new Exception("ID不能为空！");
        }
        Slider entity = sliderMapper.getByid(record.getSliderId());
        if(entity == null){
            throw new Exception("数据不存在！");
        }
        entity.setSliderId(record.getSliderId());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(ContextUtil.getSessionUsername());
        entity.setFlag(record.getFlag());

        return sliderMapper.update(record) > 0;
    }

    /**
     * 排序
     * @param sortList
     * @return
     */
    public boolean sort(List<Slider> sortList) {
        if ( null == sortList || 0 == sortList.size() ) {
            return false;
        }
        return sliderMapper.sortBatch(sortList) > 0;
    }
}
