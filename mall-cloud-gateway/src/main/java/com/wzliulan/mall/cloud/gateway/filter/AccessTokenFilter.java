package com.wzliulan.mall.cloud.gateway.filter;

import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;

/**
 * 令牌过滤器：处理令牌是否过期
 */
@Slf4j
@Component
public class AccessTokenFilter implements GlobalFilter, Ordered {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // 认证中心的Redis键名模板：注意需要与RedisKeyTemplate类定义的key模板一致
    public static final String REDIS_KEY_PREFIX_OAUTH2_TEMPLATE = "mall:cloud:oauth2:%s";

    /**
     * 校验请求头中的令牌是否有效：Redis中存在即有效，不存在即失效
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求体、响应体对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 获取访问令牌
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String accessToken = StringUtils.substringAfter(authentication, "Bearer ");
        if (StringUtils.isEmpty(accessToken)) { // 如果为空，则是放行的请求
            return chain.filter(exchange);
        } else {
            // 定义错误响应的描述信息
            String message = null;
            // 解析令牌
            try {
                JWSObject jwsObject = JWSObject.parse(accessToken);
                JSONObject jsonObject = jwsObject.getPayload().toJSONObject();
                String jti = jsonObject.get("jti").toString(); // 获取访问令牌的唯一标志 jti
                String key = String.format(AccessTokenFilter.REDIS_KEY_PREFIX_OAUTH2_TEMPLATE, jti);
                String value = stringRedisTemplate.opsForValue().get(key);
                if (null==value || StringUtils.isEmpty(value)) { // 访问令牌无效
                    message = "访问令牌已过期，请检查";
                    log.info("访问令牌过期，当前查询的jwt-jti是：{}", jti);
                } else { // 访问令牌有效，放行请求
                    return chain.filter(exchange);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                message = "访问令牌无效，请检查";
            }

            // 响应错误信息
            JSONObject result = new JSONObject();
            result.put("code", 1401);
            result.put("message", message);
            result.put("data", message);

            // 转换对象为字节
            byte[] bytes = result.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);
            // 设置响应状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 设置响应内容，处理中文乱码
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");

            // 返回响应对象
            return response.writeWith(Mono.just(dataBuffer));
        }
    }

    /**
     * 设置该过滤器的优先级，值越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        // 此过滤器在认证过滤器AuthenticationFilter之后执行
        return 10;
    }
}
