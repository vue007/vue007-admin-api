package com.vue007.admin.model.topic;

import com.vue007.admin.model.BaseEntity;

/**
 * topic_recommend 帖子推荐表实体
 *
 * @author Xiangyang
 * @date 2017年10月23日
 */
public class TopicRecommend extends BaseEntity {

    private String topicId;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
}
