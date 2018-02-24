package com.vue007.admin.controller.topic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vue007.admin.model.topic.Topic;
import com.vue007.admin.service.topic.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 帖子控制器
 *
 * @author Xiangyang
 * @date 2017年10月27日
 */
@Api(description = "帖子控制器")
@RequestMapping("/api/topic")
@RestController
public class TopicController {

    @Resource
    private TopicService topicService;

    @ApiOperation(value = "获取帖子列表分页数据接口")
    @GetMapping("/list")
    public PageInfo<Topic> findPage(@ModelAttribute Topic param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());

        return new PageInfo<>(topicService.findPage(param));
    }

    @ApiOperation(
            value = "精选操作",
            notes = "为帖子添加或取消推荐 toggle（设为精选|取消精选）"
    )
    @PostMapping("/recommend")
    public Topic recommend(@RequestBody Topic topic) throws Exception {
        return topicService.recommend(topic);
    }
    @ApiOperation(value = "更新帖子 flag 标志接口")
    @PostMapping("/flag")
    public boolean flag(@RequestBody Topic topic) throws Exception {
        return topicService.changeFlag(topic.getId(), topic.getFlag());
    }

    @ApiOperation(value = "更新帖子状态 status 接口")
    @PostMapping("/status")
    public boolean status(@RequestBody Topic topic) throws Exception {
        return topicService.changeStatus(topic.getId(), topic.getStatus());
    }

}
