package com.wzliulan.mall.cloud.article.controller.security;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.article.service.ICategoryService;
import com.wzliulan.mall.cloud.domain.dto.blog.CategoryQueryDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章分类表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Api(description = "分类相关服务api")
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation("分类搜索接口")
    @PostMapping("/search")
    public ApiResponse search(@RequestBody CategoryQueryDto categoryReqDto) {
        IPage<Category> categoryIPage = null;
        try {
            categoryIPage = categoryService.queryByPage(categoryReqDto);
            return ApiResponse.ok("搜索成功", categoryIPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常！");
        }
    }

    @ApiOperation("常态分类搜索接口")
    @GetMapping("/normal")
    public ApiResponse queryAllNormal() {
        try {
            List<Category> categoryList = categoryService.findAllNormal();
            return ApiResponse.ok("搜索成功", categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常！");
        }
    }

    @ApiOperation("常态分类及其下标签搜索接口")
    @GetMapping("/label/list")
    public ApiResponse queryNormalCategoryLabel() {
        try {
            List<Category> categoryList = categoryService.findNormalCategoryAndLabel();
            return ApiResponse.ok("搜索成功", categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常！");
        }
    }

    @ApiOperation("分类详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类ID", required = true),
    })
    @GetMapping("/find/{id}")
    public ApiResponse find(@PathVariable("id") String id) {
        Category category = null;
        try {
            category = categoryService.getById(id);
            return ApiResponse.ok("查找成功", category);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查找服务异常！");
        }
    }

    @ApiOperation("分类更新接口")
    @PutMapping("/update")
    public ApiResponse update(@RequestBody Category category) {
        boolean res = categoryService.updateById(category);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("更新失败");
        }
    }

    @ApiOperation("分类创建接口")
    @PostMapping("/add")
    public ApiResponse add(@RequestBody Category category) {
        boolean res = categoryService.save(category);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("创建失败");
        }
    }

    @ApiOperation("分类删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类ID", required = true)
    })
    @DeleteMapping("/remove/{id}")
    public ApiResponse remove(@PathVariable("id") String id) {
        boolean res = categoryService.removeById(id);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("删除失败");
        }
    }
}
