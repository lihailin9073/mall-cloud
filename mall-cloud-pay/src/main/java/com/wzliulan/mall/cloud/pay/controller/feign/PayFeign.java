package com.wzliulan.mall.cloud.pay.controller.feign;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.feign.IFileFeign;
import com.wzliulan.mall.cloud.feign.IPayFeign;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(description = "Feign接口")
@RequestMapping("/feign")
@RestController
public class PayFeign implements IPayFeign {

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
