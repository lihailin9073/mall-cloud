package com.wzliulan.mall.cloud.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@ConfigurationProperties(prefix = "blog")
public class BlogProperties implements Serializable {
    /**
     * 阿里云OSS相关的配置属性
     */
    private AliyunProperties aliyun;
}
