package com.wzliulan.mall.cloud.domain.dto.mall;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "品类查询Domain", description="品类查询Domain")
public class CategoryQueryDto {
    /**
     * 品类名称
     */
    @ApiModelProperty("品牌名称")
    private String name;

    /**
     * 是否显示：1=显示，0=隐藏
     */
    @ApiModelProperty("是否显示：1=显示，0=隐藏")
    private Integer display;
}
