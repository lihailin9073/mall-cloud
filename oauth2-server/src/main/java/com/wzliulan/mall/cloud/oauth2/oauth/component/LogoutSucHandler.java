package com.wzliulan.mall.cloud.oauth2.oauth.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LogoutSucHandler implements LogoutSuccessHandler {
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 1、获取访问令牌
        String access_token = request.getParameter("access_token");
        if (StringUtils.isBlank(access_token)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.error("访问令牌值为空，请检查")));
        }

        // 2、删除Redis中的访问令牌
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
        if (null != oAuth2AccessToken) {
            tokenStore.removeAccessToken(oAuth2AccessToken); // 删除Redis中的访问令牌
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.ok("已删除Redis中的登录令牌")));
    }
}
