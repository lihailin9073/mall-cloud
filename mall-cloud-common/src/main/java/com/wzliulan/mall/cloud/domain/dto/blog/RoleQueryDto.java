package com.wzliulan.mall.cloud.domain.dto.blog;

import com.wzliulan.mall.cloud.domain.model.system.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("角色查询条件实体类")
@Data
@Accessors(chain = true)
public class RoleQueryDto extends BaseQueryDto<Role> {
    @ApiModelProperty(value = "角色名称")
    private String name; // 角色名称
}
