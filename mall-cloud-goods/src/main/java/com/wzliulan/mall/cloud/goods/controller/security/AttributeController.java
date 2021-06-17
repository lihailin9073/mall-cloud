package com.wzliulan.mall.cloud.goods.controller.security;


import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-17
 */
@Api(description = "属性/规格/类型管理接口")
@RestController
@RequestMapping("/attribute")
public class AttributeController {

    @ApiOperation("属性/规格/类型创建")
    @PreAuthorize("hasAuthority('brand:add')")
    @PostMapping("/create")
    public Object create() {
        // TODO
        return ApiResponse.ok();
    }

}
