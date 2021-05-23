package com.wzliulan.mall.cloud.system.controller.security;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.RoleQueryDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.system.Role;
import com.wzliulan.mall.cloud.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
@Api(description = "角色相关服务的api")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @ApiOperation("角色查询接口")
    @PostMapping("/query")
    public ApiResponse query(@RequestBody RoleQueryDto roleQueryDto) {
        try {
            IPage<Role> iPage = roleService.queryByPage(roleQueryDto);
            return ApiResponse.ok(iPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("角色详情查询")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true)
    @GetMapping("/find/{id}")
    public ApiResponse find(@PathVariable("id") String id) {
        try {
            Role role = roleService.getById(id);
            return ApiResponse.ok(role);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("角色创建")
    @PostMapping("/save")
    public ApiResponse save(@RequestBody Role role) {
        boolean result = roleService.save(role);
        if (result) {
            return ApiResponse.ok();
        }
        return ApiResponse.error();
    }

    @ApiOperation("角色更新")
    @PutMapping("/update")
    public ApiResponse update(@RequestBody Role role) {
        boolean result = roleService.updateById(role);
        if (result) {
            return ApiResponse.ok();
        }
        return ApiResponse.error();
    }

    @ApiOperation("角色删除")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true)
    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable("id") String id) {
        boolean result = roleService.deleteRole(id);
        if (result) return ApiResponse.ok();
        return ApiResponse.error();
    }

    @ApiOperation("角色绑定的菜单查询")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true)
    @GetMapping("/{id}/menu/ids")
    public ApiResponse findMenu(@PathVariable("id") String id) {
        try {
            List<String> roleMenuIdList = roleService.findMenuIdByRole(id);
            return ApiResponse.ok(roleMenuIdList);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("角色菜单分配接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true),
            @ApiImplicitParam(name = "menus", value = "菜单ID集合", required = true, allowMultiple = true, dataType = "String")
    })
    @PostMapping("/{id}/menu/save")
    public ApiResponse saveRoleMenus(@PathVariable("id") String id, @RequestBody List<String> menus) {
        boolean result = roleService.saveRoleMenu(id, menus);
        if (result) {
            return ApiResponse.ok();
        }
        return ApiResponse.error();
    }
}
