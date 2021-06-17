package com.wzliulan.mall.cloud.oauth2.oauth.config;

import com.wzliulan.mall.cloud.oauth2.oauth.component.AuthFailHandler;
import com.wzliulan.mall.cloud.oauth2.oauth.component.AuthSuccessHandler;
import com.wzliulan.mall.cloud.oauth2.oauth.component.LogoutSucHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * 安全配置类
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthSuccessHandler authSuccessHandler; // 认证成功处理器
    @Autowired
    private AuthFailHandler authFailHandler; // 认证失败处理器
    @Autowired
    private LogoutSucHandler logoutSucHandler; // 退出成功处理器

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        // 使用自定义用户信息进行身份认证
        auth.userDetailsService(this.userDetailsService);
    }

    @Bean // 认证模式为密码认证模式时，需要此Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        // 开启表单登录、指定成功处理器、指定失败处理器
        http.formLogin().successHandler(authSuccessHandler).failureHandler(authFailHandler);
        // 指定退出成功处理器
        http.logout().logoutSuccessHandler(logoutSucHandler);
        // 关闭csrf攻击
        http.csrf().disable();

        // HttpSecurity支持连贯写法：http.formLogin().and().csrf().disable();
    }
}
