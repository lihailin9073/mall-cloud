package com.wzliulan.mall.cloud.domain.dto.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserAddDto对象", description="UserAddDto对象")
public class UserAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像url")
    private String imageUrl;

    @ApiModelProperty(value = "注册手机号")
    private String mobile;

    @ApiModelProperty(value = "注册邮箱")
    private String email;

}
