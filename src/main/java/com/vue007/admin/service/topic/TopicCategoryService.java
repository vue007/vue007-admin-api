package com.vue007.admin.service.topic;

import com.vue007.admin.mapper.topic.TopicCategoryMapper;
import com.vue007.admin.model.BaseEntity;
import com.vue007.admin.model.topic.TopicCategory;
import com.vue007.admin.util.CommUtil;
import com.vue007.admin.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 帖子分类业务
 *
 * @author Xiangyang
 * @date 2017年11月8日16:57:19
 */
@Service
public class TopicCategoryService {

    @Resource
    private TopicCategoryMapper topicCategoryMapper;

    public List<TopicCategory> findPage(TopicCategory category) {
        return topicCategoryMapper.findPage(category);
    }

    public TopicCategory getById(String id) {
        return topicCategoryMapper.getById(id);
    }

    public boolean create(TopicCategory topicCategory) {
        Date nowDate = new Date();
        String currentUser = ContextUtil.getSessionUsername();
        topicCategory.setId(CommUtil.random());
        topicCategory.setStatus(BaseEntity.STATUS_ENABLED);
        topicCategory.setCreateTime(nowDate);
        topicCategory.setCreateUser(currentUser);
        topicCategory.setUpdateTime(nowDate);
        topicCategory.setUpdateUser(currentUser);

        return topicCategoryMapper.create(topicCategory) > 0;
    }

    public boolean update(TopicCategory category) throws Exception {
        if (category == null || StringUtils.isBlank(category.getId()) || null == this.getById(category.getId())) {
            throw new Exception("不存在此分类！");
        }

        category.setUpdateUser(ContextUtil.getSessionUsername());
        category.setUpdateTime(new Date());

        return topicCategoryMapper.update(category) > 0;
    }

    public boolean changeFlag(String id, int flag) throws Exception {
        TopicCategory category = getOrThrowNotExists(id);
        category.setFlag(flag);
        category.setUpdateTime(new Date());
        category.setUpdateUser(ContextUtil.getSessionUsername());

        return topicCategoryMapper.update(category) > 0;
    }

    public boolean changeStatus(String id, int status) throws Exception {
        TopicCategory category = this.getOrThrowNotExists(id);
        category.setStatus(status);
        category.setUpdateTime(new Date());
        category.setUpdateUser(ContextUtil.getSessionUsername());

        return topicCategoryMapper.update(category) > 0;
    }

    private TopicCategory getOrThrowNotExists(String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("不存在此分类！");
        }

        TopicCategory entity = this.getById(id);

        if (null == entity) {
            throw new Exception("不存在此分类！");
        }

        return entity;
    }

    public boolean sort(Map<String, Integer> sortMap) {
        if (sortMap == null || sortMap.isEmpty()) {
            return false;
        }

        return topicCategoryMapper.sortBatch(sortMap) > 0;
    }

}
