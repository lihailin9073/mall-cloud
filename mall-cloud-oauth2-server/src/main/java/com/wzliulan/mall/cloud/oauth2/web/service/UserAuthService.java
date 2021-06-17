package com.wzliulan.mall.cloud.oauth2.web.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.enums.ResultResDtoEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserAuthService {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 令牌代理刷新方法
     * @param header 请求头认证信息 [Basic clientId:clientSecret]
     * @param refreshToken 刷新令牌值
     * @return
     */
    public ApiResponse refreshToken(String header, String refreshToken) throws HttpProcessException {
        // 获取被调用的服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("auth-service");
        if (null == serviceInstance) {
            return ApiResponse.error("无法找到可用的 auth-service 服务实例，请检查");
        }

        // 2、代理请求原令牌刷新api
        String refreshTokenUrl = serviceInstance.getUri().toString() + "/oauth/token"; // 原令牌刷新api接口地址
        // 封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", refreshToken);
        // 构建请求头
        Header[] headers = HttpHeader.custom()
                .contentType(HttpHeader.Headers.APP_FORM_URLENCODED) // 设置请求头内容类型 [application/x-www-form-urlencoded]
                .authorization(header) // 封装请求头认证信息 [Basic clientId:clientSecret]
                .build();
        // 构建请求体
        HttpConfig httpConfig = HttpConfig.custom()
                .headers(headers) // 包装请求头
                .url(refreshTokenUrl) // 设置请求地址
                .map(params);
        // 代理请求
        String result = HttpClientUtil.post(httpConfig);

        // 3、包装代理请求结果
        JSONObject authToken = JSON.parseObject(result);
        if (StringUtils.isNotEmpty(authToken.getString("error"))) { // 请求结果包含 error 字段，则说明令牌刷新异常
            return ApiResponse.build(ResultResDtoEnum.TOKEN_PAST);
        }

        return ApiResponse.ok(authToken);
    }

}
