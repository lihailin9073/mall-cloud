package com.wzliulan.mall.cloud.domain.entity.mall;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value="类型搜索结果Domain", description="类型搜索结果Domain")
public class TypeDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型ID")
    private String catId;

    @ApiModelProperty(value = "类型名称")
    private String catName;

    @ApiModelProperty(value = "是否可用：0=禁用，1=可用")
    private Integer enabled;

    @ApiModelProperty(value = "属性组")
    private String attrGroup;

    @ApiModelProperty(value = "所属入驻商ID")
    private String supplierId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    // ************** 以下为 m_attribute 表的字段 **************
    private String attrCount;

}
