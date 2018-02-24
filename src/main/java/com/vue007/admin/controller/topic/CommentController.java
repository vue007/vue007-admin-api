package com.vue007.admin.controller.topic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vue007.admin.service.topic.TopicCommentService;
import com.vue007.admin.model.topic.TopicComment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 帖子评论控制器
 *
 * @author Xiangyang
 * @date 2017年11月9日18:01:56
 */
@Api(description = "帖子评论控制器")
@RequestMapping("/api/topic/comment")
@RestController("topicCommentController")
public class CommentController {

    @Resource
    private TopicCommentService topicCommentService;

    @ApiOperation(value = "帖子评论列表分页数据接口")
    @GetMapping("/list")
    public PageInfo<TopicComment> findPage(@ModelAttribute TopicComment param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return new PageInfo<>(topicCommentService.findPage(param));
    }

    @ApiOperation(value = "修改帖子评论状态 status 接口")
    @PostMapping("/status")
    public boolean status(@RequestBody TopicComment topicComment) throws Exception {
        return topicCommentService.changeStatus(topicComment);
    }
}
