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
import java.util.List;

/**
 * <p>
 * 文章分类表
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("b_category")
@ApiModel(value="Category对象", description="文章分类表")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间", example = "2020-01-01 00:00:00")
    private Date createDate;

    @ApiModelProperty(value = "更新时间", example = "2020-01-01 00:00:00")
    private Date updateDate;

    @ApiModelProperty(value = "分类下的标签集合")
    @TableField(exist = false)
    private List<Label> labelList;
}
