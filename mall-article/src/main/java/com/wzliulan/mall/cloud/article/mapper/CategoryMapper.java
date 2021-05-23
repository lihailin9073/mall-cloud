package com.wzliulan.mall.cloud.article.mapper;

import com.wzliulan.mall.cloud.domain.model.article.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 文章分类表 Mapper 接口
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 查询正常状态下的分类及其下的标签信息
     * @return 返回 List<Category>
     */
    List<Category> queryNormalCategoryAndLabel();
}
