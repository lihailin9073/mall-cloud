package com.wzliulan.mall.cloud.system.controller.feign;

import com.wzliulan.mall.cloud.domain.model.system.Menu;
import com.wzliulan.mall.cloud.domain.model.system.User;
import com.wzliulan.mall.cloud.feign.SystemFeign;
import com.wzliulan.mall.cloud.system.service.IMenuService;
import com.wzliulan.mall.cloud.system.service.IUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@Api(description = "提供给其它微服务进行Feign远程调用的接口")
@RestController
public class SystemFeignController implements SystemFeign {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;

    /**
     * 用户信息查询接口
     * @param userName 用户账号
     * @return
     */
    @Override
    public User findUserByUserName(String userName) {
        User user = userService.findByUserName(userName);
        return user;
    }

    /**
     * 用户权限菜单查询接口
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<Menu> findMenuListByUserId(String userId) {
        List<Menu> menuList = menuService.findByUserId(userId);
        return menuList;
    }
}
