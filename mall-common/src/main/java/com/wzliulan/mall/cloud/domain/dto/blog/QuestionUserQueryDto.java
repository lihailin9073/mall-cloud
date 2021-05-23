package com.wzliulan.mall.cloud.domain.dto.blog;

import com.wzliulan.mall.cloud.domain.model.question.Question;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="Question查询条件对象", description="Question查询条件对象")
public class QuestionUserQueryDto extends BaseQueryDto<Question> {
    @ApiModelProperty(value = "发布者用户id", required = true)
    private String userId;

}
