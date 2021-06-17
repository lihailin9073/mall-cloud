package com.wzliulan.mall.cloud.domain.dto.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel("用户注册Dto")
@Data
@Accessors(chain = true)
public class UserRegisterDto implements Serializable {
    @ApiModelProperty(value = "用户名称", required = true)
    private String userName; // 用户名称
    @ApiModelProperty(value = "登录密码", required = true)
    private String password; // 登录密码
    @ApiModelProperty(value = "确认密码", required = true)
    private String rePassword; // 确认密码
}
