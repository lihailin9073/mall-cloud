package com.wzliulan.mall.cloud.domain.dto.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel("广告更新Dto")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertUpdateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "广告标题")
    private String title;

    @ApiModelProperty(value = "广告图片")
    private String imageUrl;

    @ApiModelProperty(value = "广告链接")
    private String advertUrl;

    @ApiModelProperty(value = "广告跳转方式（_blank：新窗口打开，_self：当前窗口打开）")
    private String advertTarget;

    @ApiModelProperty(value = "广告位置(1:首页轮播)")
    private Integer position;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
