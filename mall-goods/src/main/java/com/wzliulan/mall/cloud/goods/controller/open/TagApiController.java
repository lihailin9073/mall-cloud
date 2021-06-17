package com.wzliulan.mall.cloud.goods.controller.open;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "标签开放API接口")
@RequestMapping("/api/tag")
@RestController("apiTagController")
public class TagApiController {

    @ApiOperation("t1-测试端点")
    @GetMapping("/t1")
    public ApiResponse t1() {
        String uuid = RandomStringUtils.randomNumeric(9999);
        return ApiResponse.ok(uuid);
    }

}