package com.wzliulan.mall.cloud.goods.controller.feign;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.feign.IGoodsFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Goods Feign客户端接口")
@RequestMapping("/feign")
@RestController
public class GoodsFeign implements IGoodsFeign {

    @ApiOperation("t1-测试端点")
    @GetMapping("/t1")
    public ApiResponse t1() {
        // TODO
        String uuid = RandomStringUtils.randomNumeric(9999);
        return ApiResponse.ok(uuid);
    }

    @Override
    public ApiResponse addGoodsTag(Object object) {
        // TODO
        String uuid = RandomStringUtils.randomNumeric(9999);
        return ApiResponse.ok(uuid);
    }

    @Override
    public ApiResponse findGoodsTag(String goodsId) {
        // TODO
        String uuid = RandomStringUtils.randomNumeric(9999);
        return ApiResponse.ok(uuid);
    }
}
