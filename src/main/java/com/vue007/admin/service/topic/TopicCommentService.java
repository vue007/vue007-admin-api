package com.vue007.admin.service.topic;

import com.vue007.admin.mapper.topic.TopicCommentMapper;
import com.vue007.admin.model.topic.TopicComment;
import com.vue007.admin.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 帖子评论 topic_comment 业务层
 *
 * @author Xiangyang
 * @date 2017年10月27日
 */
@Service
public class TopicCommentService {

    @Resource
    private TopicCommentMapper topicCommentMapper;

    public List<TopicComment> findPage(TopicComment topicComment) {
        return topicCommentMapper.findPage(topicComment);
    }

    public TopicComment getById(String id) {
        return topicCommentMapper.getById(id);
    }

    public boolean update(TopicComment topicComment) throws Exception {
        if (null == topicComment || StringUtils.isBlank(topicComment.getId()) || null == this.getById(topicComment.getId())) {
            throw new Exception("不存在此帖子评论！");
        }

        topicComment.setUpdateTime(new Date());
        topicComment.setUpdateUser(ContextUtil.getSessionUsername());

        return topicCommentMapper.update(topicComment) > 0;
    }

    public boolean changeStatus(TopicComment topicComment) throws Exception {
        if (null == topicComment || StringUtils.isBlank(topicComment.getId())) {
            throw new Exception("不存在此帖子评论！");
        }

        TopicComment comment = this.getById(topicComment.getId());

        if (null == comment) {
            throw new Exception("不存在此帖子评论！");
        }

        comment.setId(topicComment.getId());
        comment.setStatus(topicComment.getStatus());
        comment.setUpdateUser(ContextUtil.getSessionUsername());
        comment.setUpdateTime(new Date());

        return topicCommentMapper.update(comment) > 0;
    }
}
