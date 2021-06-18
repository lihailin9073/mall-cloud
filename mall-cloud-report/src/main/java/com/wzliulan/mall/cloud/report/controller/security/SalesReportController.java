package com.wzliulan.mall.cloud.report.controller.security;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(description = "销售报告管理接口")
@RequestMapping("/sales-report")
@RestController
public class SalesReportController {

    @ApiOperation("t1-测试端点")
    @GetMapping("/t1")
    public ApiResponse t1() {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

    @ApiOperation("t2-测试端点")
    @GetMapping("/t2")
    public ApiResponse t2() {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

}
