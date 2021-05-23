package com.wzliulan.mall.cloud.domain.dto.blog;

import com.wzliulan.mall.cloud.domain.model.article.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@ApiModel("文章查询请求Dto")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleQueryDto extends BaseQueryDto<Article> {
    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String title;
    /**
     * 文章状态：0=已删除，1=未审核，2=审核通过，3=审核未通过
     */
    @ApiModelProperty(value = "文章状态：0=已删除，1=未审核，2=审核通过，3=审核未通过")
    private Integer status;
}
