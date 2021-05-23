package com.wzliulan.mall.cloud.system.controller.open;

import com.wzliulan.mall.cloud.domain.dto.blog.UserRegisterDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "提供用户相关的开放API接口服务")
@RequestMapping("/api/user")
@RestController
public class ApiUserController {
    @Autowired
    private IUserService userService;

    @ApiOperation("用户是否存在判断接口")
    @ApiImplicitParam(name = "username", value = "用户账号", required = true)
    @GetMapping("/is-exists/{username}")
    public ApiResponse isExists(@PathVariable("username") String userName) {
        return userService.userIsExists(userName);
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public ApiResponse register(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.register(userRegisterDto);
    }
}
