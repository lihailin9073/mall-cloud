package com.wzliulan.mall.cloud.goods.controller.security;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.dto.mall.BrandCreateDto;
import com.wzliulan.mall.cloud.domain.dto.mall.BrandQueryDto;
import com.wzliulan.mall.cloud.domain.dto.mall.BrandUpdateDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Brand;
import com.wzliulan.mall.cloud.goods.service.IBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Api(description = "品牌管理接口")
@RequestMapping("/brand")
@RestController
public class BrandController {
    @Resource
    private IBrandService brandService;

    @ApiOperation("品牌创建接口")
    @PreAuthorize("hasAuthority('brand:add')")
    @PostMapping("/create")
    public Object create(@RequestBody BrandCreateDto brandCreateDto) {
        Brand brand = Brand.builder().build();
        BeanUtils.copyProperties(brandCreateDto, brand);
        brand.setCreateTime(new Date());

        try {
            boolean result = brandService.save(brand);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败！");
        }
    }

    @ApiOperation("品牌删除接口")
    @ApiImplicitParam(name = "id", value = "品牌ID", required = true)
    @PreAuthorize("hasAuthority('brand:delete')")
    @DeleteMapping("/remove/{id}")
    public Object remove(@PathVariable("id") String id) {
        try {
            boolean result = brandService.removeById(id);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败！");
        }
    }

    @ApiOperation("品牌修改接口")
    @PreAuthorize("hasAuthority('brand:update')")
    @PutMapping("/edit")
    public Object edit(@RequestBody BrandUpdateDto brandUpdateDto) {
        Brand brand = Brand.builder().build();
        brand.setUpdateTime(new Date());
        BeanUtils.copyProperties(brandUpdateDto, brand);

        try {
            boolean result = brandService.updateById(brand);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败！");
        }
    }

    @ApiOperation("品牌查找接口")
    @PreAuthorize("hasAuthority('brand:find')")
    @ApiImplicitParam(name = "id", value = "品牌ID", required = true)
    @GetMapping("/find/{id}")
    public Object find(@PathVariable("id") String id) {
        try {
            Brand brand = brandService.getById(id);
            return ApiResponse.ok(brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.error("查询服务异常！");
    }

    @ApiOperation("品牌搜索接口")
    @PreAuthorize("hasAuthority('brand:search')")
    @PostMapping("/search")
    public Object search(@RequestBody BrandQueryDto queryDto) {
        IPage<Brand> brandIPage = null;
        try {
            brandIPage = brandService.queryByPage(queryDto);
            return ApiResponse.ok(brandIPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常！");
        }
    }

    @ApiOperation("显示设置接口")
    @ApiImplicitParam(name = "id", value = "品牌ID", required = true)
    @PreAuthorize("hasAuthority('brand:is-show')")
    @GetMapping("/set-display/{id}")
    public Object setDisplay(@PathVariable("id") String id) {
        Brand brand = brandService.getById(id);
        if (null != brand) {
            brand.setIsShow(brand.getIsShow()==0?1:0);
            brand.setUpdateTime(new Date());
        }

        try {
            boolean result = brandService.updateById(brand);
            if (result) return ApiResponse.ok(brand);
            return ApiResponse.error("设置失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("设置失败");
        }
    }

}
