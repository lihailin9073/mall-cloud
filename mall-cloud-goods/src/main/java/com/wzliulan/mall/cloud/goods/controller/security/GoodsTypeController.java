package com.wzliulan.mall.cloud.goods.controller.security;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.dto.mall.TypeCreateDto;
import com.wzliulan.mall.cloud.domain.dto.mall.TypeQueryDto;
import com.wzliulan.mall.cloud.domain.dto.mall.TypeUpdateDto;
import com.wzliulan.mall.cloud.domain.entity.mall.GoodsTypeDomain;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Type;
import com.wzliulan.mall.cloud.goods.service.IGoodsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 商品类型 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-17
 */
@Api(description = "商品类型管理接口")
@RestController
@RequestMapping("/goods-type")
public class GoodsTypeController {
    @Resource
    private IGoodsTypeService goodsTypeService;

    @ApiOperation("商品类型创建")
    @PostMapping("/create")
    public Object create(@RequestBody TypeCreateDto typeCreateDto){
        Type type = Type.builder().build();
        BeanUtils.copyProperties(typeCreateDto, type);
        type.setCreateTime(new Date());

        try {
            boolean result = goodsTypeService.save(type);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败");
        }
    }

    @ApiOperation("商品类型删除")
    @ApiImplicitParam(name = "id", value = "类型ID", required = true)
    @DeleteMapping("/remove/{id}")
    public Object remove(@PathVariable("id") String id){
        try {
            boolean result = goodsTypeService.removeById(id);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败");
        }
    }

    @ApiOperation("商品类型更新")
    @PutMapping("/edit")
    public Object edit(@RequestBody TypeUpdateDto typeUpdateDto){
        Type type = Type.builder().build();
        BeanUtils.copyProperties(typeUpdateDto, type);
        type.setUpdateTime(new Date());

        try {
            boolean result = goodsTypeService.updateById(type);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败");
        }
    }

    @ApiOperation("商品类型搜索")
    @PostMapping("/search")
    public Object search(@RequestBody TypeQueryDto typeQueryDto){
        IPage<GoodsTypeDomain> iPage = null;
        try {
            iPage = goodsTypeService.search(typeQueryDto);
            return ApiResponse.ok(iPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }
}
