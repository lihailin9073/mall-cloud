package com.wzliulan.mall.cloud.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.enums.FileFromEnum;
import com.wzliulan.mall.cloud.properties.OssProperties;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * 阿里云工具类
 */
public final class AliyunUtil {

    /**
     * 上传图片文件
     * @param fileFromEnum 文件来源类型：文章、问题、用户
     * @param file  MultipartFile文件对象
     * @param aliyun OssProperties 阿里云配置
     * @return
     */
    public static ApiResponse uploadFileToOss(FileFromEnum fileFromEnum, MultipartFile file, OssProperties aliyun ) {
        // 上传
        // 上传文件所在目录名，当天上传的文件放到当天日期的目录下。
        String folderName = fileFromEnum.name().toLowerCase() + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd");

        // 保存到 OSS 中的文件名，采用 UUID 命名。
        String fileName = UUID.randomUUID().toString().replace("-", "");

        // 从原始文件名中，获取文件扩展名
        String fileExtensionName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        // 文件在 OSS 中存储的完整路径
        String filePath = folderName + "/" + fileName + fileExtensionName;

        OSS ossClient = null;
        try {
            // 获取 OSS 客户端实例
            ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());

            // 上传文件到OSS 并响应结果
            PutObjectResult putObjectResult = ossClient.putObject(aliyun.getBucketName(), filePath, file.getInputStream());

            ResponseMessage response = putObjectResult.getResponse();
            if(response == null) {
                // 上传成功

                // 返回上传文件的访问完整路径
                return ApiResponse.ok( aliyun.getBucketDomain() + "/" + filePath );
            }else {
                // 上传失败，OOS服务端会响应状态码和错误信息
                String errorMsg = "响应的错误状态码是【" + response.getStatusCode() +"】，" +
                        "错误信息【"+response.getErrorResponseAsString()+"】";
                return ApiResponse.error(errorMsg);
            }
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        } finally {
            if (ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }

    /**
     * 根据文件url删除
     * @param fileUrl
     */
    public static ApiResponse delete(String fileUrl, OssProperties aliyun) {
        // 去除文件 url 中的 Bucket域名，得到如下格式的文件路径：article/20210211/xxx.jpg
        String filePath = fileUrl.replace(aliyun.getBucketDomain() + "/", "");

        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
            // 删除
            ossClient.deleteObject(aliyun.getBucketName(), filePath);
            return ApiResponse.ok();
        } catch (Exception e) {
            return ApiResponse.error("删除失败："+e.getMessage());
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}