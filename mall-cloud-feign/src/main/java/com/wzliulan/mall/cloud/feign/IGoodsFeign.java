package com.wzliulan.mall.cloud.feign;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "goods-service", contextId = "goods-service-client-001" )//, configuration = FeignRequestInterceptor.class)
public interface IGoodsFeign {
    @ApiOperation("t1-测试端点")
    @PostMapping("/feign/test/t1")
    ApiResponse test1();

    @ApiOperation("t2-测试端点")
    @GetMapping("/api/feign/test/t2")
    ApiResponse test2();
}
