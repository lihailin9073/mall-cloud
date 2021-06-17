package com.wzliulan.mall.cloud.oauth2.oauth.service;

import com.wzliulan.mall.cloud.domain.model.system.Menu;
import com.wzliulan.mall.cloud.domain.model.system.User;
import com.wzliulan.mall.cloud.feign.SystemFeign;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SystemFeign systemFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1、判断用户名是否为空
        if (StringUtils.isEmpty(username)) {
            throw new BadCredentialsException("用户名不能为空");
        }

        // 2、通过用户名查询数据库中的用户信息
        User user = systemFeign.findUserByUserName(username);
        if (null == user) {
            throw new BadCredentialsException("用户名或密码错误");
        }

        // 3、通过用户ID查询数据库中的权限信息
        List<Menu> menuList = systemFeign.findMenuListByUserId(user.getId());

        // 4、封装权限信息：即把权限标志符[权限码] code 字段的值封装到 com.wzliulan.mall.cloud.oauth2.service.JwtUser.authorities 属性中，Spring Security支持两种鉴权数据：角色名、权限码
        List<GrantedAuthority> authorities = null;
        if (CollectionUtils.isNotEmpty(menuList)) {
            authorities = new ArrayList<>();
            for (Menu menu : menuList) {
                String code = menu.getCode();
                authorities.add(new SimpleGrantedAuthority(code));
            }
        }

        // 5、构建UserDetails的实现类JwtUser对象
        boolean isAccountNonExpired = false;
        if (1==user.getIsAccountNonExpired()) { // 帐户是否过期(1未过期，0已过期)
            isAccountNonExpired = true;
        }
        boolean isAccountNonLocked = false;
        if (1==user.getIsAccountNonLocked()) { // 帐户是否被锁定(1 未过期，0已过期)
            isAccountNonLocked = true;
        }
        boolean isCredentialsNonExpired = false;
        if (1==user.getIsCredentialsNonExpired()) { // 密码是否过期(1 未过期，0已过期)
            isCredentialsNonExpired = true;
        }
        boolean isEnabled = false;
        if (1==user.getIsEnabled()) { // 帐户是否可用(1 可用，0 删除用户)
            isEnabled = true;
        }
        JwtUser jwtUser = JwtUser.builder()
                .uid(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .imageUrl(user.getImageUrl())
                .mobile(user.getMobile())
                .email(user.getEmail())
                .isAccountNonExpired(isAccountNonExpired)
                .isAccountNonLocked(isAccountNonLocked)
                .isCredentialsNonExpired(isCredentialsNonExpired)
                .isEnabled(isEnabled)
                .authorities(authorities) // 获取用户的权限标志码
                .build();
        return jwtUser;
    }

}
