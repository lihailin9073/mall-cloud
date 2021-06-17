package com.wzliulan.mall.cloud.goods.controller.security;


import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.dto.mall.CategoryCreateDto;
import com.wzliulan.mall.cloud.domain.dto.mall.CategoryQueryDto;
import com.wzliulan.mall.cloud.domain.dto.mall.CategoryUpdateDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Category;
import com.wzliulan.mall.cloud.goods.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-11
 */
@Api(description = "品类管理接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private ICategoryService categoryService;

    @ApiOperation("品类创建接口")
    @PreAuthorize("hasAuthority('category:add')")
    @PostMapping("/create")
    public Object create(@RequestBody CategoryCreateDto categoryCreateDto) {
        Category category = Category.builder().build();
        BeanUtils.copyProperties(categoryCreateDto, category);
        category.setCreateTime(new Date());

        try {
            boolean result = categoryService.save(category);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败！");
        }
    }

    @ApiOperation("品类删除接口")
    @ApiImplicitParam(name = "id", value = "品类ID", required = true)
    @PreAuthorize("hasAuthority('category:delete')")
    @DeleteMapping("/remove/{id}")
    public Object remove(@PathVariable("id") String id) {
        try {
            boolean result = categoryService.removeById(id);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败！");
        }
    }

    @ApiOperation("品类修改接口")
    @PreAuthorize("hasAuthority('category:update')")
    @PutMapping("/edit")
    public Object edit(@RequestBody CategoryUpdateDto categoryUpdateDto) {
        Category category = Category.builder().build();
        BeanUtils.copyProperties(categoryUpdateDto, category);
        category.setUpdateTime(new Date());

        try {
            boolean result = categoryService.updateById(category);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败！");
        }
    }

    @ApiOperation("品类查找接口")
    @ApiImplicitParam(name = "id", value = "品类ID", required = true)
    @PreAuthorize("hasAuthority('category:find')")
    @GetMapping("/find/{id}")
    public Object find(@PathVariable("id") String id) {
        try {
            Category category = categoryService.getById(id);
            return ApiResponse.ok(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.error("查询服务异常！");
    }

    @ApiOperation("品类搜索接口")
    @PreAuthorize("hasAuthority('category:search')")
    @PostMapping("/search")
    public Object search(@RequestBody CategoryQueryDto categoryQueryDto) {
        try {
            List<Category> categoryList = categoryService.queryTreeCategory(categoryQueryDto);
            if (null!=categoryList && categoryList.size()>0) return ApiResponse.ok(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.error("查询服务异常！");
    }

    @ApiOperation("商品转移接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceCategoryId", value = "来源分类ID", required = true),
            @ApiImplicitParam(name = "targetCategoryId", value = "目标分类ID", required = true)
    })
    @PreAuthorize("hasAuthority('category:move')")
    @PutMapping("/move-goods")
    public Object moveGoods(String sourceCategoryId, String targetCategoryId) {
        // TODO 涉及商品表的业务，待后续开发
        // 1、查询源分类的所有商品

        // 2、批量修改商品，将归属的源分类ID设置为目标分类ID

        return "--suc";
    }

}
