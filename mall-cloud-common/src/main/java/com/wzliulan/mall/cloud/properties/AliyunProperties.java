package com.wzliulan.mall.cloud.properties;

import lombok.Data;

import java.io.Serializable;

@Data
public class AliyunProperties implements Serializable {
    // OSS端点信息
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    // OSS端点信息

    /**
     * Bucket存储空间名称
     */
    private String bucketName;
    /**
     * Bucket 域名：访问图片时的基础URL
     */
    private String bucketDomain;

}
