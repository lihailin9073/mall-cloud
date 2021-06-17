package com.wzliulan.mall.cloud.system.controller.security;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.*;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.system.User;
import com.wzliulan.mall.cloud.system.service.IMenuService;
import com.wzliulan.mall.cloud.system.service.IUserService;
import com.wzliulan.mall.cloud.domain.dto.blog.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
@Api(description = "用户相关服务的api")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IMenuService menuService;

    @ApiOperation("用户搜索接口")
    @PostMapping("/search")
    public ApiResponse search(@RequestBody UserQueryDto userQueryDto) {
        try {
            IPage<User> iPage = userService.queryByPage(userQueryDto);
            return ApiResponse.ok(iPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常！");
        }
    }

    @ApiOperation("用户绑定的角色查询")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @GetMapping("/{id}/role/ids")
    public ApiResponse findMenu(@PathVariable("id") String id) {
        try {
            List<String> roleIdList = userService.findRoleIdByUser(id);
            return ApiResponse.ok(roleIdList);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("用户角色分配接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true),
            @ApiImplicitParam(name = "roles", value = "角色ID集合", required = true, allowMultiple = true, dataType = "String")
    })
    @PostMapping("/{id}/role/save")
    public ApiResponse saveRoleMenus(@PathVariable("id") String id, @RequestBody List<String> roles) {
        boolean result = userService.saveUserRole(id, roles);
        if (result) {
            return ApiResponse.ok();
        }
        return ApiResponse.error();
    }

    @ApiOperation("用户删除")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable("id") String id) {
        boolean result = userService.deleteUser(id);
        if (result) return ApiResponse.ok();
        return ApiResponse.error();
    }

    @ApiOperation("用户新增接口")
    @PostMapping("/add")
    public ApiResponse save(@RequestBody UserAddDto userAddDto) {
        // 初始化登录密码
        String password = passwordEncoder.encode(userAddDto.getUsername());
        User user = User.builder().build();
        BeanUtils.copyProperties(userAddDto, user);
        user.setPassword(password);

        // 创建用户
        try {
            userService.save(user);
            return ApiResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @ApiOperation("用户详情查询")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @GetMapping("/find/{id}")
    public ApiResponse findUser(@PathVariable("id") String id) {
        try {
            User user = userService.getById(id);
            if (null == user) {
                return ApiResponse.error("查无此用户！");
            }
            return ApiResponse.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @ApiOperation("密码校验接口")
    @PostMapping("/verify/password")
    public ApiResponse verifyPassword(@RequestBody UserVerifyPasswordDto verifyPasswordDto) {
        return userService.verifyPassword(verifyPasswordDto);
    }

    @ApiOperation("密码修改接口")
    @PutMapping("/update/password")
    public ApiResponse updatePassword(@RequestBody UserUpdatePasswordDto userUpdatePasswordDto) {
        return userService.updatePassword(userUpdatePasswordDto);
    }

    @ApiOperation("用户信息更新接口")
    @PutMapping("/update/user")
    public ApiResponse updateUser(@RequestBody UserInfoUpdateDto userInfoUpdateDto) {
        return userService.updateUserInfo(userInfoUpdateDto);
    }

    @ApiOperation("用户总数搜索接口")
    @GetMapping("/count")
    public ApiResponse userCount() {
        return userService.countTotalUser();
    }

    @ApiOperation("指定用户所拥有的菜单及按钮搜索接口")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true)
    @GetMapping("/find/menus/{userId}")
    public ApiResponse findUserMenuTreeAndButton(@PathVariable("userId") String userId) {
        ApiResponse userMenuTreeAndButtonList = menuService.findUserMenuTree(userId);
        return userMenuTreeAndButtonList;
    }

}
