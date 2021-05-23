package com.wzliulan.mall.cloud.domain.dto.mall;

import com.wzliulan.mall.cloud.domain.dto.blog.BaseQueryDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Brand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@ApiModel("品牌查询Domain")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class BrandQueryDto extends BaseQueryDto<Brand> {
    /**
     * 品牌名称
     */
    @ApiModelProperty("品牌名称")
    private String name;

    /**
     * 是否显示：1=显示，0=隐藏
     */
    @ApiModelProperty("是否显示：1=显示，0=隐藏")
    private Integer display;
}
