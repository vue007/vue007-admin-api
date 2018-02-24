package com.vue007.admin.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vue007.admin.model.system.Admin;
import com.vue007.admin.service.system.AdminService;
import com.vue007.admin.domain.jsonp.StatusCode;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(description = "管理员控制器")
@RequestMapping(value = "/api/admin")
@RestController
public class AdminController {

    @Resource
    private AdminService adminService;

    @ApiOperation(
        value = "获取管理员分页列表",
        notes = "获取管理员分页列表数据"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "页码", paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "keywords", value = "搜索关键字", paramType = "query", dataType = "string")
    })
    @ApiResponses({
        @ApiResponse(code = StatusCode.CODE_SUCCESS, message = "获取数据成功"),
        @ApiResponse(code = StatusCode.CODE_ERROR, message = "获取列表数据失败"),
    })
    @GetMapping(value = "/list", produces = "application/json")
    public PageInfo<Admin> list(@ModelAttribute @ApiParam(hidden = true) Admin param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<Admin> list = adminService.findPage(param);

        return new PageInfo<>(list);
    }

    @ApiOperation(value = "创建管理员", notes = "创建一个新的管理员")
    @ApiResponses({
        @ApiResponse(code = StatusCode.CODE_SUCCESS, message = "创建成功"),
        @ApiResponse(code = StatusCode.CODE_ERROR, message = "创建失败"),
    })
    @PostMapping("/create")
    public Admin create(@RequestBody Admin admin) throws Exception {
        return adminService.create(admin);
    }

    @ApiOperation(value = "更新管理员信息", notes = "更新管理员信息")
    @ApiResponses({
        @ApiResponse(code = StatusCode.CODE_SUCCESS, message = "更新成功"),
        @ApiResponse(code = StatusCode.CODE_ERROR, message = "更新失败"),
    })
    @PostMapping("/update")
    public boolean update(@RequestBody Admin admin) throws Exception {
        return adminService.update(admin);
    }

    @ApiOperation(
            value = "删除管理员",
            notes = "软删除管理员，参数：管理员ID id、标记 flag"
    )
    @PostMapping("/flag")
    public boolean flag(@RequestBody Admin admin) throws Exception {
        return adminService.setFlag(admin);
    }

}