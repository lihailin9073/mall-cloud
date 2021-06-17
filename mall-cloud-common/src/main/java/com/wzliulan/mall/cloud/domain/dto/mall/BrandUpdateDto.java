package com.wzliulan.mall.cloud.domain.dto.mall;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 品牌更新Domain
 *
 */
@ApiModel("品牌修改Domain")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class BrandUpdateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "品牌ID", required = true)
    private String brandId;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("品牌Logo")
    private String brandLogo;

    @ApiModelProperty("品牌描述")
    private String brandDesc;

    @ApiModelProperty("品牌官网")
    private String siteUrl;

    @ApiModelProperty("显示序号")
    private Integer sortOrder;

    @ApiModelProperty("是否显示")
    private Integer isShow;

}
