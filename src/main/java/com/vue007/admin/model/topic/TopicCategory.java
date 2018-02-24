package com.vue007.admin.model.topic;

import com.vue007.admin.model.BaseEntity;

import java.util.Date;

/**
 * topic_category 帖子分类表实体
 * @author Xiangyang
 * @date 2017年10月23日
 */
public class TopicCategory extends BaseEntity {

    private String name;
    private String imgUrl;
    private String littleImgUrl;
    private int sort;

    private int topicCount;
    private Date latestTopicUpdateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLittleImgUrl() {
        return littleImgUrl;
    }

    public void setLittleImgUrl(String littleImgUrl) {
        this.littleImgUrl = littleImgUrl;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(int topicCount) {
        this.topicCount = topicCount;
    }

    public Date getLatestTopicUpdateTime() {
        return latestTopicUpdateTime;
    }

    public void setLatestTopicUpdateTime(Date latestTopicUpdateTime) {
        this.latestTopicUpdateTime = latestTopicUpdateTime;
    }
}
