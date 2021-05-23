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
 * 商品类型
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
@TableName("m_type")
@ApiModel(value="Type对象", description="商品类型")
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型ID")
    @TableId(value = "cat_id", type = IdType.ASSIGN_ID)
    private String catId;

    @ApiModelProperty(value = "类型名称")
    private String catName;

    @ApiModelProperty(value = "是否可用：0=禁用，1=可用")
    private Integer enabled;

    @ApiModelProperty(value = "属性组")
    private String attrGroup;

    @ApiModelProperty(value = "所属入驻商ID")
    private String supplierId;

    private Date createTime;

    private Date updateTime;


}
