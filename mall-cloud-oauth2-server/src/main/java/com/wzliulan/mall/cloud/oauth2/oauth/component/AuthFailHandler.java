package com.wzliulan.mall.cloud.oauth2.oauth.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 失败处理器：认证失败后返回统一格式的json数据给客户端
 */
@Slf4j
@Component
public class AuthFailHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.error(e.getMessage())));
    }
}
