package com.wzliulan.mall.cloud.feign;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service", contextId = "order-service-client-001" )//, configuration = FeignRequestInterceptor.class)
public interface IOrderFeign {
    @ApiOperation("t1-测试端点")
    @PostMapping("/feign/tag/goods-tag-add")
    ApiResponse addGoodsTag(@RequestBody Object object);

    @ApiOperation("t2-测试端点")
    @GetMapping("/api/feign/tag/find-goods-tag")
    ApiResponse findGoodsTag(String goodsId);
}
