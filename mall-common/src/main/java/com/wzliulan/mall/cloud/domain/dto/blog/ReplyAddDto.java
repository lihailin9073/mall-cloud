package com.wzliulan.mall.cloud.domain.dto.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 回答信息表
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="Reply新增数据对象", description="Reply新增数据对象")
public class ReplyAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "-1 表示正常回答，其他值表示是回答的回复")
    private String parentId;

    @ApiModelProperty(value = "回答者id")
    private String userId;

    @ApiModelProperty(value = "回答者用户昵称")
    private String nickName;

    @ApiModelProperty(value = "回答者头像url")
    private String userImage;

    @ApiModelProperty(value = "问题id")
    private String questionId;

    @ApiModelProperty(value = "md问题内容")
    private String mdContent;

    @ApiModelProperty(value = "html问题内容")
    private String htmlContent;

}
