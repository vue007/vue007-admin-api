package com.vue007.admin.controller.file;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vue007.admin.model.file.FileInfo;
import com.vue007.admin.service.file.FileInfoService;
import com.vue007.admin.domain.jsonp.StatusCode;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(description = "已上传文件信息 控制器")
@RequestMapping(value = "/api/file/info")
@RestController
public class FileInfoController {

    @Resource
    private FileInfoService fileInfoService;

    @ApiOperation(
        value = "获取文件分页列表",
        notes = "获取文件分页列表数据"
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
    public PageInfo<FileInfo> list(@ModelAttribute @ApiParam(hidden = true) FileInfo param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<FileInfo> list = fileInfoService.findPage(param);

        return new PageInfo<>(list);
    }

    @ApiOperation(value = "创建新文件", notes = "创建一个新的文件")
    @ApiResponses({
            @ApiResponse(code = StatusCode.CODE_SUCCESS, message = "创建成功"),
            @ApiResponse(code = StatusCode.CODE_ERROR, message = "创建失败"),
    })
    @PostMapping("/create")
    public FileInfo create(@RequestBody FileInfo fileInfo) throws Exception {
        return fileInfoService.create(fileInfo);
    }

    @ApiOperation(value = "更新文件信息", notes = "更新文件信息")
    @ApiResponses({
        @ApiResponse(code = StatusCode.CODE_SUCCESS, message = "更新成功"),
        @ApiResponse(code = StatusCode.CODE_ERROR, message = "更新失败"),
    })
    @PostMapping("/update")
    public boolean update(@RequestBody FileInfo fileInfo) throws Exception {
        return fileInfoService.update(fileInfo);
    }

    @ApiOperation(
            value = "删除文件",
            notes = "软删除文件，参数：文件ID id、标记 flag"
    )
    @PostMapping("/flag")
    public boolean flag(@RequestBody FileInfo fileInfo) throws Exception {
        return fileInfoService.setFlag(fileInfo);
    }

}