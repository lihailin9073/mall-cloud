package com.wzliulan.mall.cloud.domain.dto.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("菜单查询条件实体类")
@Data
@Accessors(chain = true)
public class MenuQueryDto {
    @ApiModelProperty(value = "菜单名称")
    private String name; // 菜单名称
}
