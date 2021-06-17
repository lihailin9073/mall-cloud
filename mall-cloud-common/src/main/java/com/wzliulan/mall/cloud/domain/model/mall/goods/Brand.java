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
 * 
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-10
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_brand")
@ApiModel(value="Brand对象", description="Brand对象")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "brand_id", type = IdType.ASSIGN_ID)
    private String brandId;

    private String brandName;

    private String brandLogo;

    private String brandImg;

    private String brandDesc;

    private String wapBrandDesc;

    private String siteUrl;

    private Integer sortOrder;

    private Integer isShow;

    private String brandName1;

    private Integer langFlag;

    private Date createTime;

    private Date updateTime;

}
