package com.vue007.admin.controller.banner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vue007.admin.model.banner.Slider;
import com.vue007.admin.service.banner.SliderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author anquan
 * desc: Banner管理
 * date 2018-01-16 10:51
 */
@Api(description = "Banner管理控制器")
@RestController("sliderController")
@RequestMapping("/api/banner/slider")
public class SliderController {

    @Resource
    private SliderService sliderService;

    @ApiOperation(
            value = "slider分页接口",
            notes = "获取slider分页数据"
    )
    @GetMapping("/list")
    public PageInfo<Slider> findPage(@ModelAttribute Slider param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return new PageInfo<>(sliderService.findPage(param));
    }

    @ApiOperation(
            value = "添加slider",
            notes = "添加slider数据"
    )
    @PostMapping("/create")
    public boolean create(@RequestBody Slider record) throws Exception {
        return sliderService.create(record);
    }

    @ApiOperation(
            value = "更新slider",
            notes = "更新slider数据"
    )
    @PostMapping("/update")
    public boolean update(@RequestBody Slider record) throws Exception {
        return sliderService.update(record);
    }

    @ApiOperation(
            value = "更新slider状态",
            notes = "更新slider数据"
    )
    @PostMapping("/flag")
    public boolean flag(@RequestBody Slider record) throws Exception {
        return sliderService.changeFlag(record);
    }

    @ApiOperation(value = "排序")
    @PostMapping("/sort")
    public boolean sort(@RequestBody List<Slider> sortList) {
        return sliderService.sort(sortList);
    }
}
