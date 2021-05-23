package com.wzliulan.mall.cloud.system.utils;

import com.wzliulan.mall.cloud.domain.model.system.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

/**
 * 认证用户信息获取工具类
 */
public class AuthUserUtil {

    /**
     * 认证用户信息获取方法
     * @return 返回包含了核心用户数据的User对象：注意，如果需要全字段的用户信息，请根据id去数据库查询
     */
    public static User getAuthUserInfo() {
        // 获取身份认证用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        Map<String, Object> map = (Map<String, Object>)details.getDecodedDetails();
        Map<String, String> userInfo = (Map<String, String>)map.get("userInfo");

        // 转换认证用户信息：注意，如果需要全字段的用户信息，请根据id去数据库查询
        User user = User.builder()
                .id(userInfo.get("uid"))
                .username(userInfo.get("username"))
                .nickName(userInfo.get("nickName"))
                .email(userInfo.get("email"))
                .imageUrl(userInfo.get("imageUrl"))
                .mobile(userInfo.get("mobile"))
                .build();

        return user;
    }
}
