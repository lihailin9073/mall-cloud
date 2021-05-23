package com.wzliulan.mall.cloud.article.controller.open;

import com.wzliulan.mall.cloud.article.service.ICategoryService;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 【/api/xxx】此控制层接口，不需要身份认证就可以调用到
 */
@Api(description = "分类相关服务api")
@Slf4j
@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation("正常分类查询接口")
    @GetMapping("/list")
    public ApiResponse queryAllNormal() {
        try {
            List<Category> categoryList = categoryService.findAllNormal();
            return ApiResponse.ok(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常！");
        }
    }

    @ApiOperation("常态分类及其下标签查询接口")
    @GetMapping("/label/list")
    public ApiResponse queryNormalCategoryLabel() {
        try {
            List<Category> categoryList = categoryService.findNormalCategoryAndLabel();
            return ApiResponse.ok(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常！");
        }
    }
}
