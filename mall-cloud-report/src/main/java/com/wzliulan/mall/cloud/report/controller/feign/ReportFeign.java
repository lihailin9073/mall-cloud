package com.wzliulan.mall.cloud.report.controller.feign;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.feign.IReportFeign;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(description = "Feign接口")
@RequestMapping("/report-feign")
@RestController
public class ReportFeign implements IReportFeign {

    @Override
    public ApiResponse test1() {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

    @Override
    public ApiResponse test2() {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }
}
