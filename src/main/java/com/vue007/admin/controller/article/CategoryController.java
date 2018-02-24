package com.vue007.admin.controller.article;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vue007.admin.model.article.ArticleCategory;
import com.vue007.admin.service.article.ArticleCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资讯分类控制器
 *
 * @author Xiangyang
 * @date 2017年11月8日16:37:46
 */
@Api(description = "资讯分类控制器")
@RestController("articleCategoryController")
@RequestMapping("/api/article/category")
public class CategoryController {

    @Resource
    private ArticleCategoryService articleCategoryService;

    @ApiOperation(
            value = "资讯分类列表分页接口",
            notes = "获取资讯分类列表分页数据"
    )
    @GetMapping("/list")
    public PageInfo<ArticleCategory> findPage(@ModelAttribute ArticleCategory param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return new PageInfo<>(articleCategoryService.findPage(param));
    }

    @ApiOperation(
            value = "添加资讯分类",
            notes = "添加资讯分类数据"
    )
    @PostMapping("/create")
    public boolean create(@RequestBody ArticleCategory articleCategory) {
        return articleCategoryService.create(articleCategory);
    }

    @ApiOperation(
            value = "更新资讯分类数据",
            notes = "更新资讯分类数据接口"
    )
    @PostMapping("/update")
    public boolean update(@RequestBody ArticleCategory articleCategory) throws Exception {
        return articleCategoryService.update(articleCategory);
    }

    @ApiOperation(value = "资讯分类排序")
    @PostMapping("/sort")
    public boolean sort(@RequestBody List<ArticleCategory> sortList) {
        return articleCategoryService.sort(sortList);
    }

    @ApiOperation(
            value = "更新资讯分类状态"
    )
    @PostMapping("/flag")
    public boolean flag(@RequestBody ArticleCategory articleCategory) throws Exception {
        return articleCategoryService.changeFlag(articleCategory);
    }


    @ApiOperation(
            value = "资讯分类列表分页接口",
            notes = "获取资讯分类列表分页数据"
    )
    @GetMapping("/v2/list")
    public PageInfo<ArticleCategory> findPages(@ModelAttribute ArticleCategory param) throws Exception {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        if( null == param.getCategoryType() ) {//|| null == param.getParentId()
            throw new Exception("传入参数不正确！");
        }
        return new PageInfo<>(articleCategoryService.findPages(param));
    }

}
