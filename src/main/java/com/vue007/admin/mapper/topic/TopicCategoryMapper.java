package com.vue007.admin.mapper.topic;

import com.vue007.admin.model.topic.TopicCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 帖子分类 topic_category mapper
 *
 * @author Akai
 * @date 2018-01-12 15:42:43
 */
public interface TopicCategoryMapper {

    List<TopicCategory> findPage(TopicCategory topicCategory);

    TopicCategory getById(String id);

    int create(TopicCategory topicCategory);

    int update(TopicCategory topicCategory);

    int sortBatch(@Param("sortMap") Map<String, Integer> sortMap);

    int deleteById(String id);

}
