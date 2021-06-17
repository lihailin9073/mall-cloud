package com.wzliulan.mall.cloud.oauth2.oauth.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 成功处理器：认证成功后返回统一格式的json数据给客户端
 */
@Slf4j
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    private static final String HEADER_TYPE = "Basic "; // 请求头类型
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功 {}", authentication.getPrincipal());
        ApiResponse apiResponse = null;

        // 1、获取请求头认证信息 [Basic clientId:clientSecret]
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (null==header || !header.startsWith(AuthSuccessHandler.HEADER_TYPE)) { // 请求头信息为空
            throw new UnsupportedOperationException("请求头的clientId、clientSecret认证信息为空，请检查");
        }
        Map<String, String> clientInfo = null;
        try {
            clientInfo = RequestUtil.parseAuthHeaderInfo(header);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        if (clientInfo.size() != 2) {
            apiResponse = ApiResponse.error("请求头的clientId、clientSecret认证信息解析失败，请检查");
        }

        // 2、校验请求头认证信息
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientInfo.get("client_id"));
        if (null == clientDetails) {
            throw new UnsupportedOperationException("请求头的认证信息未经系统登记，请检查");
        }
        if (!passwordEncoder.matches(clientInfo.get("client_secret"), clientDetails.getClientSecret())) {
            apiResponse = ApiResponse.error("请求头的认证信息与系统记录不匹配，请检查");
        }

        // 3、获取访问令牌
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientDetails.getClientId(), clientDetails.getScope(), "custom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        apiResponse = ApiResponse.ok(accessToken);

        // 4、响应数据
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
