package com.wzliulan.mall.cloud.feign;

import com.wzliulan.mall.cloud.domain.model.system.Menu;
import com.wzliulan.mall.cloud.domain.model.system.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "system-service", contextId = "system-service-client-001")//, configuration = FeignRequestInterceptor.class)
public interface ISystemFeign {
    @ApiOperation("用户信息搜索接口")
    @ApiImplicitParam(name = "userName", value = "用户登录账号", required = true)
    @PutMapping("/api/feign/user/{userName}")
    User findUserByUserName(@PathVariable("userName") String userName);

    @ApiOperation("用户权限菜单搜索接口")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true)
    @PutMapping("/api/feign/menu/{userId}")
    List<Menu> findMenuListByUserId(@PathVariable("userId") String userId);
}
