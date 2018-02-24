package com.vue007.admin.controller.file;

import com.vue007.admin.model.file.FileInfo;
import com.vue007.admin.service.file.FileInfoService;
import com.vue007.admin.domain.jsonp.StatusCode;
import com.vue007.admin.util.OssStsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@Api(description = "文件上传")
@RequestMapping("/api/file/upload")
@RestController
public class UploadController {

    @Resource
    private FileInfoService fileInfoService;

    /**
     * 获取阿里的上传token
     * @author Akai
     * @date 2018-01-13 11:52:20
     * @return String sts token
     */
    @ApiOperation(value = "获取阿里的上传token", notes = "获取阿里的上传临时STStoken")
    @ApiResponses({
            @ApiResponse(code = StatusCode.CODE_SUCCESS, message = "获取成功"),
            @ApiResponse(code = StatusCode.CODE_ERROR, message = "获取失败"),
    })
    @GetMapping("/STSToken")
    public Map<String, Object> getOssInfo() {
        Map<String, Object> stsInfo = OssStsUtil.getStsInfo();
        return stsInfo;
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

}
