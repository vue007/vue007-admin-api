package com.vue007.admin.model.topic;

import com.vue007.admin.model.BaseEntity;

/**
 * topic_comment 帖子评论表实体
 *
 * @author Xiangyang
 * @date 2017年10月23日
 */
public class TopicComment extends BaseEntity {

    private String commentedId;
    private String userId;
    private String topicId;
    private String content;
    private int likeCount;

    public String getCommentedId() {
        return commentedId;
    }

    public void setCommentedId(String commentedId) {
        this.commentedId = commentedId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
