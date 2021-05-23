package com.wzliulan.mall.cloud.oauth2.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Spring Security提示信息汉化配置
 */
@Configuration
public class MessageReloadConfig {

    /**
     * 加载中文提示信息
     * @return
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:messages_zh_CN"); // 加载指定的消息配置文件
        return source;
    }
}
