package com.wzliulan.mall.cloud.domain.dto.mall;

import com.wzliulan.mall.cloud.domain.dto.blog.BaseQueryDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Tag;
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
@ApiModel(value="标签查询Domain", description="标签查询Domain")
public class TagQueryDto extends BaseQueryDto<Tag> {
    /**
     * 标签名称
     */
    @ApiModelProperty("标签名称")
    private String name;
}
