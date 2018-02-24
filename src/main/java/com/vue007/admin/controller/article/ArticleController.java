package com.vue007.admin.controller.article;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vue007.admin.model.article.Article;
import com.vue007.admin.service.article.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 资讯控制器
 *
 * @author Xiangyang
 * @date 2017年10月27日
 */
@Api(description = "资讯控制器")
@RequestMapping("/api/article")
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @ApiOperation(
            value = "获取资讯列表分页数据接口",
            notes = "获取资讯分页列表数据"
    )
    @GetMapping("/list")
    public PageInfo<Article> findPage(@ModelAttribute Article param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return new PageInfo<>(articleService.findPage(param));
    }

    @ApiOperation(
            value = "根据资讯ID获取资讯信息",
            notes = "根据资讯ID获取资讯信息"
    )
    @GetMapping("/info")
    public Article getById(String id) {
        return articleService.getById(id);
    }

    @ApiOperation(
            value = "创建资讯",
            notes = "创建资讯，参数：资讯实体属性"
    )
    @PostMapping("/create")
    public Article create(@RequestBody Article article) {
        return articleService.create(article);
    }

    @ApiOperation(
            value = "更新资讯信息",
            notes = "更新资讯信息，参数：资讯实体属性"
    )
    @PostMapping("/update")
    public boolean update(@RequestBody Article article) throws Exception {
        return articleService.update(article);
    }

    @ApiOperation(
            value = "推荐操作",
            notes = "为文章添加或取消推荐 toggle（取消推荐|加入推荐）"
    )
    @PostMapping("/recommend")
    public Article recommend(@RequestBody Article article) throws Exception {
        return articleService.recommend(article);
    }

    @ApiOperation(
            value = "修改资讯软删除标志",
            notes = "修改资讯软删除标志 flag，参数：资讯ID id、标志 flag"
    )
    @PostMapping("/flag")
    public boolean changeFlag(@RequestBody Article article) throws Exception {
        return articleService.changeFlag(article);
    }

    @ApiOperation(
            value = "修改资讯状态",
            notes = "修改资讯状态，参数：资讯ID id、状态 status"
    )
    @PostMapping("/status")
    public boolean changeStatus(@RequestBody Article article) throws Exception {
        return articleService.changeStatus(article);
    }

}
