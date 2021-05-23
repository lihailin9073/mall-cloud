package com.wzliulan.mall.cloud.domain.model.mall.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品属性
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-17
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_attribute")
@ApiModel(value="Attribute对象", description="商品属性")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性ID")
    @TableId(value = "attr_id", type = IdType.ASSIGN_ID)
    private String attrId;

    @ApiModelProperty(value = "所属类型ID")
    private String catId;

    @ApiModelProperty(value = "属性名称")
    private String attrName;

    @ApiModelProperty(value = "属性值的输入类型：文本框、选择框、下拉菜单等")
    private Integer attrInputType;

    @ApiModelProperty(value = "属性类型")
    private Integer attrType;

    @ApiModelProperty(value = "属性值")
    private String attrValues;

    private Integer attrIndex;

    @ApiModelProperty(value = "属性排序")
    private Integer sortOrder;

    private Integer isLinked;

    private Integer attrGroup;

    private Integer isAttrGallery;

    @ApiModelProperty(value = "判断条形码是否显示")
    private Boolean attrTxm;

    private String attrName1;

    private String attrPid;

    private Integer langFlag;

    private Date createTime;

    private Date updateTime;


}
