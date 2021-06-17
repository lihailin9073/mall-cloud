package com.wzliulan.mall.cloud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 认证过滤器：处理请求头是否携带Authentication信息
 */
@Slf4j
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {
    // 定义放行的api白名单
    private static final String[] white = {"/api/", "/mini-app/", "/mobile-app/", "/swagger-ui.html"};
    // 定义swagger接口文档地址
    private static final String[] swagger = {"/webjars/springfox-swagger-ui/", "/swagger-resources", "/v2/api-docs"};

    /**
     * 验证请求头是否携带 Authentication
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求体、响应体对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 判断是否白名单的api
        String path = request.getPath().pathWithinApplication().value(); // 获取请求URI
        // log.info("认证过滤器AuthenticationFilter，准备对请求URI = {} 进行请求头校验", path);
        if (StringUtils.indexOfAny(path, AuthenticationFilter.white) != -1 || StringUtils.indexOfAny(path, AuthenticationFilter.swagger) != -1) { // 包含了白名单配置的路径或swagger相关地址，直接放行
            // 放行请求
            return chain.filter(exchange);
        } else {
            // 获取访问令牌
            String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);  // 从请求头获取访问令牌
            if (StringUtils.isEmpty(authorization)) { // 尝试从Get请求的查询参数中获取访问令牌
                MultiValueMap<String, String> queryParams = request.getQueryParams();
                List<String> access_token = queryParams.get("access_token");
                if (null!=access_token && StringUtils.isNotEmpty(access_token.get(0))) {
                    log.info("网关截获的access_token：{}", access_token.get(0));
                    authorization = access_token.get(0);
                }
            }

            // 检查访问令牌
            if (StringUtils.isEmpty(authorization)) { // 未携带Authentication 认证信息
                JSONObject message = new JSONObject();
                message.put("code", 1401);
                message.put("message", "请求头缺少认证信息[Authorization]");

                // 转换对象为字节
                byte[] bytes = message.toJSONString().getBytes(StandardCharsets.UTF_8);
                DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);
                // 设置响应状态码 401
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                // 设置响应内容，处理中文乱码
                response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");

                // 返回响应对象
                return response.writeWith(Mono.just(dataBuffer));
            }

            // 携带了请求头 Authentication，则放行给内部各个微服务进行校验
            return chain.filter(exchange);
        }
    }

    /**
     * 设置该过滤器的优先级，值越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
