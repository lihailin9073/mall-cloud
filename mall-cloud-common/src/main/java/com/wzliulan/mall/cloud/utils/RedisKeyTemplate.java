package com.wzliulan.mall.cloud.utils;

/**
 * Redis键名模板
 */
public class RedisKeyTemplate {
    // 默认的Redis键名模板
    public static final String REDIS_KEY_PREFIX_DEFAULT_TEMPLATE = "mall:cloud:%s";
    // 文章微服务的Redis键名模板
    public static final String REDIS_KEY_PREFIX_ARTICLE_TEMPLATE = "mall:cloud:goods:%s";
    // 问答微服务的Redis键名模板
    public static final String REDIS_KEY_PREFIX_QUESTION_TEMPLATE = "mall:cloud:order:%s";
    // 系统微服务的Redis键名模板
    public static final String REDIS_KEY_PREFIX_SYSTEM_TEMPLATE = "mall:cloud:system:%s";
    // 认证中心的Redis键名模板
    public static final String REDIS_KEY_PREFIX_OAUTH2_TEMPLATE = "mall:cloud:oauth2:%s";
}
