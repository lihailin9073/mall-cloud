package com.wzliulan.mall.cloud.goods.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "测试服务接口")
@RequestMapping("/test")
@RestController
public class TestController {

    @ApiOperation("t1-测试端点")
    @GetMapping("/t1")
    public Object t1() {
        return RandomStringUtils.randomNumeric(9999);
    }

}
