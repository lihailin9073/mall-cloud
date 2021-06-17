package com.wzliulan.mall.cloud.domain.dto.mall;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
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
@ApiModel(value="标签创建Domain", description="标签创建Domain")
public class TagCreateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("商品ID")
    private String goodsId;

    @ApiModelProperty("标签名称")
    private String tagWords;

}
