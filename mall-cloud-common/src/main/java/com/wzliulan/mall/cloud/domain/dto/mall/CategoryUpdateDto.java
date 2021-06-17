package com.wzliulan.mall.cloud.domain.dto.mall;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_category")
@ApiModel(value="品类更新Domain", description="品类更新Domain")
public class CategoryUpdateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "品类ID")
    private String catId;

    @ApiModelProperty(value = "品类名称")
    private String catName;

    @ApiModelProperty(value = "小程序分类简称")
    private String xcxCatName;

    @ApiModelProperty(value = "品类关键词")
    private String keywords;

    @ApiModelProperty(value = "品类描述")
    private String catDesc;

    @ApiModelProperty(value = "品类父ID")
    private Integer parentId;

    @ApiModelProperty(value = "品类排序")
    private Integer sortOrder;

    @ApiModelProperty(value = "品类模板")
    private String templateFile;

    @ApiModelProperty(value = "测量单位")
    private String measureUnit;

    @ApiModelProperty(value = "是否在导航栏显示")
    private Boolean showInNav;

    @ApiModelProperty(value = "显示样式")
    private String style;

    @ApiModelProperty(value = "是否显示")
    private Integer isShow;

    @ApiModelProperty(value = "是否在小程序中显示")
    private Integer isXcxShow;

    @ApiModelProperty(value = "品类级别")
    private Integer grade;

    @ApiModelProperty(value = "过滤属性")
    private String filterAttr;

    @ApiModelProperty(value = "品类主页")
    private Integer categoryIndex;

    @ApiModelProperty(value = "品类模板文件")
    private Boolean categoryIndexDwt;

    @ApiModelProperty(value = "主页模板文件")
    private String indexDwtFile;

    @ApiModelProperty(value = "是否在主页显示")
    private Integer showInIndex;

    @ApiModelProperty(value = "-废弃的属性")
    private String catIndexRightad;

    @ApiModelProperty(value = "链接类型")
    private String type;

    @ApiModelProperty(value = "广告图-1")
    private String catAdimg1;

    @ApiModelProperty(value = "广告跳转地址-1")
    private String catAdurl1;

    @ApiModelProperty(value = "广告图-2")
    private String catAdimg2;

    @ApiModelProperty(value = "广告跳转地址-2")
    private String catAdurl2;

    @ApiModelProperty(value = "-废弃的属性")
    private String catNameimg;

    @ApiModelProperty(value = "品类客服QQ")
    private String brandQq;

    @ApiModelProperty(value = "附属客服QQ")
    private String attrQq120029121;

    @ApiModelProperty(value = "-废弃的属性")
    private String pathName;

    @ApiModelProperty(value = "是否虚拟商品的品类")
    private Integer isVirtual;

    @ApiModelProperty(value = "显示的商品数量")
    private Integer showGoodsNum;

    @ApiModelProperty(value = "微信商城分类图标")
    private String typeImg;

}
