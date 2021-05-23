package com.wzliulan.mall.cloud.article.controller.security;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.enums.FileFromEnum;
import com.wzliulan.mall.cloud.properties.AliyunProperties;
import com.wzliulan.mall.cloud.properties.BlogProperties;
import com.wzliulan.mall.cloud.utils.AliyunUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(description = "文件相关服务api")
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private BlogProperties blogProperties;

    @ApiOperation("文件上传接口")
    @PostMapping("/upload")
    public ApiResponse upload(@RequestParam("my_file") MultipartFile file) {
        // 获取OSS配置
        AliyunProperties aliyun = blogProperties.getAliyun();
        //log.info("aliyun-oss配置：{}", aliyun.toString());

        // 上传文件
        return AliyunUtil.uploadFileToOss(FileFromEnum.ARTICLE, file, aliyun);
    }

    @ApiOperation("文件删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file_url", value = "文件URL", required = true)
    })
    @DeleteMapping("/delete")
    public ApiResponse delete(@RequestParam(value = "file_url", required = true) String fileUrl) {
        // 获取OSS配置
        AliyunProperties aliyun = blogProperties.getAliyun();
        // 删除文件
        return AliyunUtil.delete(fileUrl, aliyun);
    }
}
