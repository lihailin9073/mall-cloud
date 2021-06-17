package com.wzliulan.mall.cloud.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Feign请求拦截器：扩展请求头携带身份认证信息
 */
@Slf4j
@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 通过RequestContextHolder工具来获取请求相关变量
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        log.info("...Feign请求拦截器...");
        if (null != requestAttributes) {
            HttpServletRequest  request = requestAttributes.getRequest();
            String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            log.info("Feign请求拦截器，截获的访问令牌为：{}", accessToken);
            if (StringUtils.isNotEmpty(accessToken)) {
                // 将访问令牌添加到Feign请求头
                requestTemplate.header(HttpHeaders.AUTHORIZATION, accessToken);
            }
        }
    }
}
