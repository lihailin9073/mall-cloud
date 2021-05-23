package com.wzliulan.mall.cloud.domain.dto.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="Question更新或新增数据对象", description="Question更新或新增数据对象")
public class QuestionAddOrUpdateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "发布者用户id")
    private String userId;

    @ApiModelProperty(value = "发布者用户昵称")
    private String nickName;

    @ApiModelProperty(value = "发布者头像url")
    private String userImage;

    @ApiModelProperty(value = "问题标题")
    private String title;

    @ApiModelProperty(value = "md问题内容")
    private String mdContent;

    @ApiModelProperty(value = "html问题内容")
    private String htmlContent;

    @ApiModelProperty(value = "浏览次数")
    private Integer viewCount;

    @ApiModelProperty(value = "点赞数")
    private Integer thumhup;

    @ApiModelProperty(value = "回复数")
    private Integer reply;

    @ApiModelProperty(value = "所属标签的ID集合")
    private List<String> labelIds;

}
