package com.wzliulan.mall.cloud.oauth2.oauth.component;

import com.alibaba.fastjson.JSON;
import com.wzliulan.mall.cloud.oauth2.oauth.service.JwtUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * OAuth2 响应信息定制化组件
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // 获取UserDetails的自定义实现类对象
        JwtUser jwtUser = (JwtUser)authentication.getPrincipal();

        // 封装需要扩展的响应信息
        Map<String, Object> extData = new HashMap<>();
        extData.put("userInfo", JSON.toJSON(jwtUser));
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(extData);

        return accessToken;
    }
}
