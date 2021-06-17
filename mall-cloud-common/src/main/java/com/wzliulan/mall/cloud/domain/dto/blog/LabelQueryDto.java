package com.wzliulan.mall.cloud.domain.dto.blog;

import com.wzliulan.mall.cloud.domain.model.article.Label;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@ApiModel("标签查询请求Dto")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelQueryDto extends BaseQueryDto<Label> {
    /**
     * 标签名称
     */
    @ApiModelProperty("标签名称")
    private String name;
    /**
     * 分类ID
     */
    @ApiModelProperty("分类ID")
    private String categoryId;
}
