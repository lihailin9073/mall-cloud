package com.wzliulan.mall.cloud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关限流处理器
 */
@Slf4j
@Component("uriKeyResolver")
public class UriKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        // log.info("网关限流过滤器-uriKeyResolver，限流的URL为：{}", path);
        // 针对微服务的每个请求都进行限流
        Mono<String> mono = Mono.just(path);
        return mono;
    }
}
