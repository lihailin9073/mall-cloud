package com.wzliulan.mall.cloud.marketing.controller.open;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "秒杀管理接口")
@Slf4j
@RequestMapping("/api/sec-kill")
@RestController()
public class SecKillApiController {

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