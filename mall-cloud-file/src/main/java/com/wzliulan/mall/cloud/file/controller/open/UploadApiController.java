package com.wzliulan.mall.cloud.file.controller.open;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.enums.FileFromEnum;
import com.wzliulan.mall.cloud.properties.AliyunProperties;
import com.wzliulan.mall.cloud.properties.OssProperties;
import com.wzliulan.mall.cloud.utils.AliyunUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;

@Api(description = "文件上传开放接口")
@RequestMapping("/api/upload")
@RestController
public class UploadApiController {
    @Resource
    private AliyunProperties aliyunProperties;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public ApiResponse upload(@RequestParam("my_file") MultipartFile file) {
        // 获取OSS配置
        OssProperties oss = aliyunProperties.getOss();
        //log.info("oss-oss配置：{}", oss.toString());

        // 上传文件
        return AliyunUtil.uploadFileToOss(FileFromEnum.ARTICLE, file, oss);
    }

    @ApiOperation("文件删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file_url", value = "文件URL", required = true)
    })
    @DeleteMapping("/delete")
    public ApiResponse delete(@RequestParam(value = "file_url", required = true) String fileUrl) {
        // 获取OSS配置
        OssProperties oss = aliyunProperties.getOss();
        // 删除文件
        return AliyunUtil.delete(fileUrl, oss);
    }

    @ApiOperation("oss配置获取")
    @GetMapping("/get-oss-config")
    public ApiResponse getOssConfig() {
        return ApiResponse.ok(aliyunProperties.getOss());
    }

}
