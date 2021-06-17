package com.wzliulan.mall.cloud.domain.dto.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("菜单创建实体类")
public class MenuAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单 ID")
    private String id;

    @ApiModelProperty(value = "父菜单 ID (0为顶级菜单)")
    private String parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "类型(1目录，2菜单，3按钮)")
    private Integer type;

    @ApiModelProperty(value = "授权标识符")
    private String code;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    private String remark;

}
