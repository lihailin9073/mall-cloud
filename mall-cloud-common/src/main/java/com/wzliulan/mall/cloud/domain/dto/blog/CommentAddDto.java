package com.wzliulan.mall.cloud.domain.dto.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="CommentAddDto对象", description="评论发表Dto对象")
public class CommentAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "-1表示正常回复，其他值表示是评论的回复")
    private String parentId;

    @ApiModelProperty(value = "评论者用户id")
    private String userId;

    @ApiModelProperty(value = "评论者用户昵称")
    private String nickName;

    @ApiModelProperty(value = "评论者头像url")
    private String userImage;

    @ApiModelProperty(value = "文章id")
    private String articleId;

    @ApiModelProperty(value = "评论内容")
    private String content;


}
