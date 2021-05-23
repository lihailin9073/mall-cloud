package com.wzliulan.mall.cloud.oauth2.oauth.config;

import com.wzliulan.mall.cloud.utils.RedisKeyTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.concurrent.TimeUnit;

/**
 * 令牌管理配置类
 */
@Slf4j
@Configuration
public class JwtTokenStoreConfig {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //private static final String REDIS_KEY_PREFIX_OAUTH2_TEMPLATE = "blog:oauth2:%s";

    /**
     * jwt转换器：转换私钥文件的内容
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // 参数-1：指定私钥文件，参数-2：指定存储口令
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(new ClassPathResource("blog.jks"), "mypass".toCharArray());
        // 参数为秘钥文件的别名
        KeyPair keyPair = factory.getKeyPair("blog");

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        return converter;
    }

    /**
     * token存储器：指定jwt进行令牌管理；
     *              TokenStore有多种实现，包括内存方式、jdbc方式、redis方式、jwt方式，可以按组合键 Ctrl+Alt+B 查看
     * @return 返回Jwt令牌存储器
     */
    @Bean
    public TokenStore tokenStore() {
        // 使用jwt管理令牌
        TokenStore tokenStore = new JwtTokenStore(this.jwtAccessTokenConverter()) {
            /**
             * 存储令牌到Redis
             * @param token
             * @param authentication
             */
            @Override
            public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
                // 将jwt中的身份认证信息的jti作为key、access_token作为value，存储到Redis中
                if (token.getAdditionalInformation().containsKey("jti")) {
                    String jti = token.getAdditionalInformation().get("jti").toString();
                    String tokenValue = token.getValue();
                    int expiresIn = token.getExpiresIn();
                    // 保存令牌到Redis
                    String key = String.format(RedisKeyTemplate.REDIS_KEY_PREFIX_OAUTH2_TEMPLATE, jti);
                    log.info("...生成的key={}", key);
                    stringRedisTemplate.opsForValue().set(key, tokenValue, expiresIn, TimeUnit.SECONDS);
                }

                // 调用父类方法
                super.storeAccessToken(token, authentication);
            }

            /**
             * 删除Redis中的令牌
             * @param token
             */
            @Override
            public void removeAccessToken(OAuth2AccessToken token) {
                // 删除Redis中存储的令牌
                if (token.getAdditionalInformation().containsKey("jti")) {
                    String jti = token.getAdditionalInformation().get("jti").toString();
                    String key = String.format(RedisKeyTemplate.REDIS_KEY_PREFIX_OAUTH2_TEMPLATE, jti);
                    log.info("...删除的key={}", key);
                    stringRedisTemplate.delete(key);
                }

                // 调用父类方法
                super.removeAccessToken(token);
            }
        };

        return tokenStore;
    }

}
