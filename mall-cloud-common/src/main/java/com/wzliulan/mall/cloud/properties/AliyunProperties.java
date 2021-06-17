package com.wzliulan.mall.cloud.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@ConfigurationProperties(prefix = "mall.aliyun")
public class AliyunProperties implements Serializable {
    /**
     * 阿里云OSS配置
     */
    private OssProperties oss;
}
