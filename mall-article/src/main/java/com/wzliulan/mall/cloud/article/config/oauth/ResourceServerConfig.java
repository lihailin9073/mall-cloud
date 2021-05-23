package com.wzliulan.mall.cloud.article.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务配置类
 */
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级别的权限控制
@EnableResourceServer // 标志为资源服务器：请求资源接口时，必须在请求头携带访问令牌access_token
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;

    /**
     * 资源配置
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //super.configure(resources);
        resources.tokenStore(tokenStore); // 采用Jwt管理令牌

        /**
         * 指定资源ID才能访问：客户端client_id的resource_ids中必须包含了 [resource_ids=article-service-resource] 才有权限访问，
         *                      不指定则要求默认的[resource_ids=oauth2-resource]才可访问
         */
        resources.resourceId("article-service-resource");
    }

    /**
     * 安全配置
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        // 关闭SESSION：因为前后端分离采用的是JwtTokenStore进行令牌管理，而不是SESSION
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 定义放行的API
        String[] swagger = {
                "/v2/api-docs", "/v2/feign-docs",
                "/swagger-resources/configuration/ui",
                "/swagger-resources","/swagger-resources/configuration/security",
                "/swagger-ui.html", "/webjars/**"
        };
        String[] openApiArray = {"/api/**", "/mini-app/**", "/mobile-app/**"};

        // 设置放行的接口
        http.authorizeRequests()
                // 放行Swagger
                .antMatchers(swagger).permitAll()
                // 放行开放API
                .antMatchers(openApiArray).permitAll()
                // 所有请求要有 [scope=all] 范围权限
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                // 其它请求都要经过身份认证系统
                .anyRequest().authenticated();
    }

}
