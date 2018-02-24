package com.vue007.admin.controller.article;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vue007.admin.model.article.Wallpaper;
import com.vue007.admin.service.article.WallpaperService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * @author anquan
 * desc:
 * date 2018-01-22 14:01
 */
@Controller
@RestController("wallpaperController")
@RequestMapping("/api/article/wallpaper")
public class WallpaperController {

    @Resource
    private WallpaperService wallpaperService;

    @ApiOperation(
            value = "壁纸分页接口",
            notes = "获取壁纸分页数据"
    )
    @GetMapping("/list")
    public PageInfo<Wallpaper> findPage(@ModelAttribute Wallpaper param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return new PageInfo<>(wallpaperService.findPage(param));
    }
    @ApiOperation(
            value = "根据ID获壁纸",
            notes = "根据ID获壁纸详情"
    )
    @GetMapping("/info")
    public Wallpaper getById(String id) {
        return wallpaperService.getById(id);
    }

    @ApiOperation(
            value = "添加壁纸",
            notes = "添加壁纸数据"
    )
    @PostMapping("/create")
    public boolean create(@RequestBody Wallpaper record) throws Exception {
        return wallpaperService.create(record);
    }

    @ApiOperation(
            value = "更新壁纸",
            notes = "更新壁纸数据"
    )
    @PostMapping("/update")
    public boolean update(@RequestBody Wallpaper record) throws Exception {
        return wallpaperService.update(record);
    }

    @ApiOperation(
            value = "更新壁纸删除标志",
            notes = "更新壁纸删除标志"
    )
    @PostMapping("/flag")
    public boolean flag(@RequestBody Wallpaper record) throws Exception {
        return wallpaperService.changeFlag(record);
    }

    @ApiOperation(
            value = "修改壁纸状态",
            notes = "修改壁纸状态，参数：资讯ID id、状态 status"
    )
    @PostMapping("/status")
    public boolean changeStatus(@RequestBody Wallpaper record) throws Exception {
        return wallpaperService.changeStatus(record);
    }
}
