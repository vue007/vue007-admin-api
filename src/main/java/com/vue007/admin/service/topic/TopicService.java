package com.vue007.admin.service.topic;

import com.vue007.admin.model.topic.Topic;
import com.vue007.admin.mapper.topic.TopicMapper;
import com.vue007.admin.util.CommUtil;
import com.vue007.admin.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 帖子 topic 业务逻辑
 *
 * @author Xiangyang
 * @date 2017年10月27日
 */
@Service
public class TopicService {

    @Resource
    private TopicMapper topicMapper;

    public List<Topic> findPage(Topic topic) {
        return topicMapper.findPage(topic);
    }

    public Topic getById(String id) {
        return topicMapper.getById(id);
    }

    public boolean update(Topic topic) throws Exception {
        if (topic == null || StringUtils.isBlank(topic.getId()) || null == this.getById(topic.getId())) {
            throw new Exception("不存在此帖子");
        }

        topic.setUpdateTime(new Date());
        topic.setUpdateUser(ContextUtil.getSessionUsername());

        return topicMapper.update(topic) > 0;
    }

    public Topic recommend(Topic topic) {
        Date now = new Date();
        String curUser = ContextUtil.getSessionUsername();

        // 存在则删除 不存在则 新增推荐记录
        if ( null == topic.getRecommendId() || "".equals( topic.getRecommendId() ) ){

            topic.setCreateTime(now);
            topic.setCreateUser(curUser);
            topic.setRecommendId( CommUtil.random() );
            return (topicMapper.createRecommend(topic) > 0) ? topic : null;
        } else {

            boolean flag = topicMapper.deleteRecommendById( topic.getRecommendId() ) > 0;
            if (flag) topic.setRecommendId(null);
            return (flag) ? topic : null;
        }

    }

    public boolean changeFlag(String id, int flag) throws Exception {
        Topic topic = getOrThrowNotExists(id);
        topic.setUpdateUser(ContextUtil.getSessionUsername());
        topic.setUpdateTime(new Date());
        topic.setFlag(flag);

        return topicMapper.update(topic) > 0;
    }

    public boolean changeStatus(String id, int status) throws Exception {
        Topic topic = getOrThrowNotExists(id);
        topic.setUpdateTime(new Date());
        topic.setUpdateUser(ContextUtil.getSessionUsername());
        topic.setStatus(status);

        return topicMapper.update(topic) > 0;
    }

    private Topic getOrThrowNotExists(String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("不存在此分类！");
        }

        Topic entity = this.getById(id);

        if (null == entity) {
            throw new Exception("不存在此分类！");
        }

        return entity;
    }
}
