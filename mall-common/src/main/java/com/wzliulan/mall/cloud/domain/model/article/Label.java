package com.wzliulan.mall.cloud.domain.model.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("b_label")
@ApiModel(value="Label对象", description="标签表")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "分类id")
    private String categoryId;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "创建时间", example = "2020-01-01 00:00:00")
    private Date createDate;

    @ApiModelProperty(value = "更新时间", example = "2020-01-01 00:00:00")
    private Date updateDate;

    @ApiModelProperty(value = "分类名称")
    @TableField(exist = false) // 标志这个字段不是 b_label 表字段，只是用于其它业务的实现
    private String categoryName;
}
