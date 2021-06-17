package com.wzliulan.mall.cloud.oauth2.web.controller;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.google.common.base.Preconditions;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.oauth2.web.service.UserAuthService;
import com.wzliulan.mall.cloud.utils.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Api(description = "提供认证相关的API接口服务")
@Slf4j
@RequestMapping("/user")
@RestController
public class AuthController {
    @Autowired
    private UserAuthService userAuthService;
    private static final String HEADER_TYPE = "Basic "; // 请求头类型
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("令牌刷新接口")
    @PostMapping("/refresh/token")
    public ApiResponse refreshToken(HttpServletRequest request) {

        // 1、获取刷新令牌值
        String refreshToken = request.getParameter("refreshToken");
        Preconditions.checkArgument(StringUtils.isNotEmpty(refreshToken), "刷新令牌值为空，请检查"); // 参数校验

        // 2、获取请求头认证信息 [Basic clientId:clientSecret]
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (null==header || !header.startsWith(AuthController.HEADER_TYPE)) { // 请求头信息为空
            throw new UnsupportedOperationException("请求头的clientId、clientSecret认证信息为空，请检查");
        }
        Map<String, String> clientInfo = null;
        try {
            clientInfo = RequestUtil.parseAuthHeaderInfo(header);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (clientInfo.size() != 2) { //
            return ApiResponse.error("请求头的clientId、clientSecret认证信息解析失败，请检查");
        }

        // 3、校验请求头认证信息
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientInfo.get("client_id"));
        if (null == clientDetails) {
            return ApiResponse.error("请求头的认证信息未经系统登记，请检查");
        }
        if (!passwordEncoder.matches(clientInfo.get("client_secret"), clientDetails.getClientSecret())) {
            return ApiResponse.error("请求头的认证信息与系统记录不匹配，请检查");
        }

        // 4、代理刷新令牌
        try {
            ApiResponse apiResponse = userAuthService.refreshToken(header, refreshToken);
            return apiResponse;
        } catch (HttpProcessException e) {
            e.printStackTrace();
            return ApiResponse.error("令牌代理刷新失败");
        }
    }

}
