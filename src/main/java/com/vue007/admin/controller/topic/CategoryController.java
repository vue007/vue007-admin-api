package com.vue007.admin.controller.topic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vue007.admin.model.topic.TopicCategory;
import com.vue007.admin.service.topic.TopicCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 帖子分类控制器
 *
 * @author Xiangyang
 * @date 2017年11月8日16:44:58
 */
@Api(description = "帖子分类控制器")
@RestController("topicCategoryController")
@RequestMapping("/api/topic/category")
public class CategoryController {

    @Resource
    private TopicCategoryService topicCategoryService;

    @ApiOperation(
            value = "获取帖子分类列表分页数据接口",
            notes = "获取帖子分类列表分页数据"
    )
    @GetMapping("/list")
    public PageInfo<TopicCategory> findPage(@ModelAttribute TopicCategory param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return new PageInfo<>(topicCategoryService.findPage(param));
    }

    @ApiOperation(value = "创建帖子分类接口")
    @PostMapping("/create")
    public boolean create(@RequestBody TopicCategory category) {
        return topicCategoryService.create(category);
    }

    @ApiOperation(value = "更新帖子分类数据接口")
    @PostMapping("/update")
    public boolean update(@RequestBody TopicCategory category) throws Exception {
        return topicCategoryService.update(category);
    }
    @ApiOperation(value = "资讯分类排序")
    @PostMapping("/sort")
    public boolean sort(@RequestBody HashMap<String, Integer> sortMap) {
        return topicCategoryService.sort(sortMap);
    }


    @ApiOperation(value = "更新帖子分类状态接口")
    @PostMapping("/status")
    public boolean status(@RequestBody TopicCategory category) throws Exception {
        return topicCategoryService.changeStatus(category.getId(), category.getStatus());
    }

    @ApiOperation(value = "更新帖子分类 flag 标记接口")
    @PostMapping("/flag")
    public boolean flag(@RequestBody TopicCategory category) throws Exception {
        return topicCategoryService.changeFlag(category.getId(), category.getFlag());
    }

}
