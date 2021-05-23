package com.wzliulan.mall.cloud.system.controller.security;


import com.wzliulan.mall.cloud.domain.dto.blog.MenuAddDto;
import com.wzliulan.mall.cloud.domain.dto.blog.MenuQueryDto;
import com.wzliulan.mall.cloud.domain.dto.blog.MenuUpdateDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.system.Menu;
import com.wzliulan.mall.cloud.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 菜单信息表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
@Api(description = "菜单相关服务的api")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation("菜单查询接口")
    @PostMapping("/search")
    public ApiResponse querySystemMenuList(@RequestBody MenuQueryDto menuQueryDto) {
        try {
            List<Menu> menuList = menuService.queryTreeMenu(menuQueryDto);
            return ApiResponse.ok(menuList);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("菜单级联删除接口")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true)
    @DeleteMapping("/del/{id}")
    public ApiResponse deleteMenu(@PathVariable("id") String id) {
        boolean res = menuService.deleteById(id);
        if (!res) {
            return ApiResponse.error("删除失败");
        }
        return ApiResponse.ok();
    }

    @ApiOperation("菜单新增接口")
    @PostMapping("/save")
    public ApiResponse saveMenu(@RequestBody MenuAddDto menuAddDto) {
        Menu menu = Menu.builder().build();
        BeanUtils.copyProperties(menuAddDto, menu);
        menu.setCreateDate(new Date());
        menu.setUpdateDate(new Date());
        boolean result = menuService.save(menu);
        if (!result) {
            return ApiResponse.error("新增失败");
        }
        return ApiResponse.ok();
    }

    @ApiOperation("菜单详情查询接口")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true)
    @PostMapping("/find/{id}")
    public ApiResponse findById(@PathVariable("id") String id) {
        try {
            Menu menu = menuService.getById(id);
            return ApiResponse.ok(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("菜单更新接口")
    @PostMapping("/update")
    public ApiResponse updateMenu(@RequestBody MenuUpdateDto menuUpdateDto) {
        Menu menu = Menu.builder().build();
        BeanUtils.copyProperties(menuUpdateDto, menu);
        menu.setUpdateDate(new Date());
        boolean result = menuService.updateById(menu);
        if (!result) {
            return ApiResponse.error("更新失败");
        }
        return ApiResponse.ok();
    }


}
