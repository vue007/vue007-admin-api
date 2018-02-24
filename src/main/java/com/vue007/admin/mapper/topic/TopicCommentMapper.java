package com.vue007.admin.mapper.topic;

import com.vue007.admin.model.topic.TopicComment;

import java.util.List;

/**
 * topic_comment 帖子评论 mapper
 *
 * @author Xiangyang
 * @date 2017年10月25日
 */
public interface TopicCommentMapper {

    List<TopicComment> findPage(TopicComment topicComment);

    TopicComment getById(String id);

    int update(TopicComment topicComment);

    int deleteById(String id);

}
