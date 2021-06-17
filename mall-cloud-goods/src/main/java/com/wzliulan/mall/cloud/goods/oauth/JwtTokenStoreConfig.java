package com.wzliulan.mall.cloud.goods.oauth;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;
import java.util.Map;

/**
 * 令牌管理配置类
 */
@Configuration
public class JwtTokenStoreConfig {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // 加载公钥文件
        ClassPathResource resource = new ClassPathResource("public.txt");

        // 获取公钥内容
        String publicKey = null;
        try {
            publicKey = IOUtils.toString(resource.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(publicKey);

        // 将用户信息转换器添加到jwt转换器中
        converter.setAccessTokenConverter(new UserInfoConverter());

        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(this.jwtAccessTokenConverter());
        return jwtTokenStore;
    }

    /**
     * 用户信息转换器：从AccessToken中转换用户信息
     */
    private class UserInfoConverter extends DefaultAccessTokenConverter {
        @Override
        public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
            OAuth2Authentication oAuth2Authentication = super.extractAuthentication(map);
            oAuth2Authentication.setDetails(map);
            return oAuth2Authentication;
        }
    }
}
