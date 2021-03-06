package com.wzliulan.mall.cloud.oauth2.oauth.service;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@ApiModel(value="自定义实现的登录认证用户类", description="自定义实现的登录认证用户类")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser implements UserDetails {
    @ApiModelProperty(value = "用户ID")
    private String uid;

    @ApiModelProperty(value = "用户名")
    private String username;

    @JSONField(serialize = false) // 忽略转json
    @ApiModelProperty(value = "密码，加密存储, admin/1234")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像url")
    private String imageUrl;

    @ApiModelProperty(value = "注册手机号")
    private String mobile;

    @ApiModelProperty(value = "注册邮箱")
    private String email;

    // 1 true 0 false
    @JSONField(serialize = false) // 忽略转json
    @ApiModelProperty(value = "帐户是否过期(1 未过期，0已过期)")
    private boolean isAccountNonExpired; // 不要写小写 boolean

    @JSONField(serialize = false) // 忽略转json
    @ApiModelProperty(value = "帐户是否被锁定(1 未过期，0已过期)")
    private boolean isAccountNonLocked;

    @JSONField(serialize = false) // 忽略转json
    @ApiModelProperty(value = "认证是否过期(1 未过期，0已过期)")
    private boolean isCredentialsNonExpired;

    @JSONField(serialize = false) // 忽略转json
    @ApiModelProperty(value = "帐户是否可用(1 可用，0 删除用户)")
    private  boolean isEnabled;

    @JSONField(serialize = false) // 忽略转json
    @ApiModelProperty(value = "用户的权限集合：权限码或角色名")
    private List<GrantedAuthority> authorities;

//    // isAccountNonExpired 是 Integer 类型接收，然后转 boolean
//    public JwtUser(String uid, String username, String password,
//                   String nickName, String imageUrl, String mobile, String email,
//                   Integer isAccountNonExpired, Integer isAccountNonLocked,
//                   Integer isCredentialsNonExpired, Integer isEnabled,
//                   List<GrantedAuthority> authorities) {
//        this.uid = uid;
//        this.username = username;
//        this.password = password;
//        this.nickName = nickName;
//        this.imageUrl = imageUrl;
//        this.mobile = mobile;
//        this.email = email;
//        this.isAccountNonExpired = isAccountNonExpired == 1 ? true: false;
//        this.isAccountNonLocked = isAccountNonLocked == 1 ? true: false;
//        this.isCredentialsNonExpired = isCredentialsNonExpired == 1 ? true: false;
//        this.isEnabled = isEnabled == 1 ? true: false;
//        this.authorities = authorities;
//    }
}
