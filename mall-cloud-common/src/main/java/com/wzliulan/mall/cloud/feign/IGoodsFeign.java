package com.wzliulan.mall.cloud.feign;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "goods-service", contextId = "goods-service-client-001" )//, configuration = FeignRequestInterceptor.class)
public interface IGoodsFeign {
    @ApiOperation("批量贴标接口")
    @PostMapping("/feign/tag/goods-tag-add")
    ApiResponse addGoodsTag(@RequestBody Object object);

    @ApiOperation("商品已有标签查询接口")
    @GetMapping("/api/feign/tag/find-goods-tag")
    ApiResponse findGoodsTag(String goodsId);
}
