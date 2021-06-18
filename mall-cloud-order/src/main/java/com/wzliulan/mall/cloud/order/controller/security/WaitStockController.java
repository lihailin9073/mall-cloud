package com.wzliulan.mall.cloud.order.controller.security;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "缺货登记接口")
@Slf4j
@RequestMapping("/wait-stock")
@RestController()
public class WaitStockController {

    @ApiOperation("测试端点-01")
    @PostMapping("/t1")
    public ApiResponse test1() {
        // TODO
        return ApiResponse.ok();
    }

    @ApiOperation("测试端点-02")
    @PostMapping("/t2")
    public ApiResponse test2() {
        // TODO
        return ApiResponse.ok();
    }
}
