package com.wzliulan.mall.cloud.domain.dto.blog;

import com.wzliulan.mall.cloud.domain.model.system.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("用户查询条件实体类")
@Data
@Accessors(chain = true)
public class UserQueryDto extends BaseQueryDto<User> {
    @ApiModelProperty(value = "用户名称")
    private String name; // 用户名称
    @ApiModelProperty(value = "手机号码")
    private String phone; // 手机号码
}
