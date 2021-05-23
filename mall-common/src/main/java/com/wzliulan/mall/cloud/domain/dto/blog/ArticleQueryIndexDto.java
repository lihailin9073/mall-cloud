package com.wzliulan.mall.cloud.domain.dto.blog;

import com.wzliulan.mall.cloud.domain.model.article.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@ApiModel("已发表文章查询接口Dto")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleQueryIndexDto extends BaseQueryDto<Article> {
    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID")
    private String categoryId;

    /**
     * 标签ID
     */
    @ApiModelProperty(value = "标签ID")
    private String labelId;
}
