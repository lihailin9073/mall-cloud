package com.wzliulan.mall.cloud.domain.dto.blog;

import com.wzliulan.mall.cloud.domain.model.article.Advert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@ApiModel("广告查询请求Dto")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertQueryDto extends BaseQueryDto<Advert> {
    /**
     * 广告标题
     */
    @ApiModelProperty(value = "广告标题")
    private String title;
    /**
     * 广告状态：0=禁用，1=正常
     */
    @ApiModelProperty(value = "广告状态：0=禁用，1=正常")
    private Integer status;
}
