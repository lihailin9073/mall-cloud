package com.wzliulan.mall.cloud.article.controller.security;


import com.wzliulan.mall.cloud.article.service.IAdvertService;
import com.wzliulan.mall.cloud.domain.dto.blog.AdvertAddDto;
import com.wzliulan.mall.cloud.domain.dto.blog.AdvertQueryDto;
import com.wzliulan.mall.cloud.domain.dto.blog.AdvertUpdateDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Advert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 广告信息表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Api(description = "广告相关服务api")
@Slf4j
@RestController
@RequestMapping("/advert")
public class AdvertController {
    @Autowired
    private IAdvertService advertService;

    @ApiOperation("广告查询接口")
    @PostMapping("/search")
    public ApiResponse search(@RequestBody AdvertQueryDto advertQueryDto) {
        return advertService.queryByPage(advertQueryDto);
    }

    @ApiOperation("广告删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告ID", required = true),
    })
    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable("id") String id) {
        return advertService.deleteById(id);
    }

    @ApiOperation("广告详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告ID", required = true),
    })
    @GetMapping("/find/{id}")
    public ApiResponse find(@PathVariable("id") String id) {
        return ApiResponse.ok(advertService.getById(id));
    }

    @ApiOperation("广告创建接口")
    @PostMapping("/add")
    public ApiResponse add(@RequestBody AdvertAddDto advertQueryDto) {
        Advert advert = Advert.builder().build();
        BeanUtils.copyProperties(advertQueryDto, advert);
        try {
            advertService.save(advert);
            return ApiResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @ApiOperation("广告更新接口")
    @PostMapping("/update")
    public ApiResponse update(@RequestBody AdvertUpdateDto advertQueryDto) {
        Advert advert = Advert.builder().build();
        BeanUtils.copyProperties(advertQueryDto, advert);
        advert.setUpdateDate(new Date());
        try {
            advertService.updateById(advert);
            return ApiResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }
}
