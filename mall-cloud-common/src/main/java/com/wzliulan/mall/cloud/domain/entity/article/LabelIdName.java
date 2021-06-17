package com.wzliulan.mall.cloud.domain.entity.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="LabelIdName", description="Label编号及名称封装Entity")
public class LabelIdName implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标签id")
    private String id;

    @ApiModelProperty(value = "标签名称")
    private String name;

}
