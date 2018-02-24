package com.vue007.admin.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vue007.admin.service.user.UserService;
import com.vue007.admin.domain.jsonp.StatusCode;
import com.vue007.admin.model.user.User;
import com.vue007.admin.model.user.UserAudit;
import com.vue007.admin.service.user.UserAuditService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(description = "用户管理控制器")
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserAuditService userAuditService;

    @GetMapping("/list")
    public PageInfo<User> findPage(@ModelAttribute User param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return new PageInfo<>(userService.findPage(param));
    }

    @ApiOperation(
            value = "新增用户",
            notes = "新增用户，默认为系统用户，参数：昵称 nickname、头像 headImg"
    )
    @PostMapping("/create")
    public boolean create(@RequestBody User user) {
        return userService.create(user);
    }

    @ApiOperation(
            value = "更新用户信息",
            notes = "更新用户个人信息"
    )
    @PostMapping("/update")
    public boolean update(@RequestBody User user) throws Exception {
        return userService.update(user);
    }

    @ApiOperation(
            value = "用户头像上传接口",
            notes = "用户头像上传接口，参数 ：头像文件 file"
    )
    @PostMapping("/headImg")
    public Map<String, Object> uploadHeadImg(@RequestPart("file") MultipartFile file, HttpServletRequest request) throws Exception {
//        String savePath = request.getServletContext().getRealPath(UPLOAD_PATH);
//        String url = uploadService.file(file, savePath);
        Map<String, Object> ret = new HashMap<>();
//        ret.put("url", url);

        return ret;
    }

    @ApiOperation(
            value = "删除用户",
            notes = "物理删除用户，参数：用户ID id"
    )
    @GetMapping("/delete")
    public boolean delete(String id) throws Exception {
        return userService.deleteById(id);
    }

    @ApiOperation(
            value = "拉黑用户接口",
            notes = "拉黑用户接口，参数：用户ID id、状态值 status"
    )
    @ApiResponses({
            @ApiResponse(code = StatusCode.CODE_SUCCESS, message = "操作成功", response = Boolean.class)
    })
    @PostMapping("/status")
    public boolean status(@RequestBody User user) throws Exception {
        return userService.changeStatus(user.getId(), user.getStatus());
    }

    @ApiOperation(
            value = "获取用户审核记录列表",
            notes = "获取用户审核记录列表数据"
    )
    @GetMapping("/audit")
    public PageInfo<UserAudit> findAuditPage(@ModelAttribute UserAudit param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return new PageInfo<>(userAuditService.findPage(param));
    }

    @ApiOperation(
            value = "更新审核记录状态",
            notes = "更新审核记录状态值，参数：审核ID id、审核状态 status"
    )
    @PostMapping("/audit/status")
    public boolean changeAuditStatus(@RequestBody UserAudit userAudit) throws Exception {
        return userAuditService.changeStatus(userAudit);
    }
}
