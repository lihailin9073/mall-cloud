package com.wzliulan.mall.cloud.domain.dto.blog;

import com.wzliulan.mall.cloud.domain.model.article.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@ApiModel("个人文章查询Dto")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleUserDto extends BaseQueryDto<Article> {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;

    /**
     * 是否公开
     */
    @ApiModelProperty(value = "是否公开：0=不公开，1=公开", required = true)
    private String ispublic;
}
