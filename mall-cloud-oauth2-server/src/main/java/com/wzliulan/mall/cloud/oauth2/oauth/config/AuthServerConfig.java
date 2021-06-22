package com.wzliulan.mall.cloud.oauth2.oauth.config;

import com.wzliulan.mall.cloud.oauth2.oauth.component.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务配置类
 */
@EnableAuthorizationServer // 标志此应用为认证服务器
@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource; // 注入配置文件中配置的数据源

    /**
     * 采用jdbc方式管理认证中心的客户端（资源服务器）信息
     * @return
     */
    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        return jdbcClientDetailsService;
    }

    /**
     * 配置被允许访问的客户端资源服务器
     * @param clients 客户端资源服务器清单明细对象 ClientDetailsServiceConfigurer
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //super.configure(clients);
        ClientDetailsService clientDetailsService = this.jdbcClientDetailsService();
        clients.withClientDetails(clientDetailsService);
    }


    /**
     * 此Bean在 SpringSecurityConfig 配置类已经添加到Spring容器中
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 认证服务器端点配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //super.configure(endpoints);
        // 设置认证管理器：密码模式需要该对象（实际上，4种模式中除了客户端模式外，其它3中模式都需要用到这个认证管理器）
        endpoints.authenticationManager(authenticationManager);

        // 设置刷新令牌需要用的用户信息
        endpoints.userDetailsService(userDetailsService);

        // 设置令牌的管理方式、令牌的jwt转换器
        endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter);

        // 设置令牌增强器
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(enhancerList);
        endpoints.tokenEnhancer(tokenEnhancerChain).accessTokenConverter(jwtAccessTokenConverter); // 将令牌增强器添加到端点上
    }

    /**
     * 认证服务器访问配置
     * @param security 认证配置对象
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //super.configure(security);
        // 开放令牌解析端点 /oauth/check_token， 该端点默认是禁止访问的
        security.checkTokenAccess("permitAll()");
    }

}
