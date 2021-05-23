package com.wzliulan.mall.cloud.domain.dto.mall;

import com.wzliulan.mall.cloud.domain.dto.blog.BaseQueryDto;
import com.wzliulan.mall.cloud.domain.entity.mall.TypeDomain;
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
@ApiModel(value="类型查询Domain", description="类型查询Domain")
public class TypeQueryDto extends BaseQueryDto<TypeDomain> {
    /**
     * 类型名称
     */
    @ApiModelProperty("类型名称")
    private String name;
}
