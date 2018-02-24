package com.vue007.admin.mapper.topic;

import com.vue007.admin.model.topic.Topic;

import java.util.List;

/**
 * topic 帖子 mapper
 *
 * @author Xiangyang
 * @date 2017年10月26日
 */
public interface TopicMapper {

    List<Topic> findPage(Topic topic);

    Topic getById(String id);

    int update(Topic topic);

    int deleteById(String id);

    int createRecommend(Topic topic);

    int deleteRecommendById(String recommendId);
}
