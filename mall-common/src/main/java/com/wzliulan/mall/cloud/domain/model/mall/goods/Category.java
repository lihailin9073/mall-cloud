package com.wzliulan.mall.cloud.domain.model.mall.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-11
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_category")
@ApiModel(value="Category对象", description="Category对象")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cat_id", type = IdType.ASSIGN_ID)
    private String catId;

    private String catName;

    @ApiModelProperty(value = "小程序分类简称")
    private String xcxCatName;

    private String keywords;

    private String catDesc;

    private String parentId;

    private Integer sortOrder;

    private String templateFile;

    private String measureUnit;

    private Boolean showInNav;

    private String style;

    private Integer isShow;

    @ApiModelProperty(value = "是否在小程序中显示")
    private Integer isXcxShow;

    private Integer grade;

    private String filterAttr;

    private Integer categoryIndex;

    private Boolean categoryIndexDwt;

    private String indexDwtFile;

    private Integer showInIndex;

    private String catIndexRightad;

    @ApiModelProperty(value = "链接类型")
    private String type;

    private String catAdimg1;

    private String catAdurl1;

    private String catAdimg2;

    private String catAdurl2;

    private String catNameimg;

    private String brandQq;

    private String attrQq120029121;

    private String pathName;

    private Integer isVirtual;

    private Integer showGoodsNum;

    @ApiModelProperty(value = "微信商城分类图标")
    private String typeImg;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "子分类集合")
    @TableField(exist = false)
    private List<Category> children;
}
