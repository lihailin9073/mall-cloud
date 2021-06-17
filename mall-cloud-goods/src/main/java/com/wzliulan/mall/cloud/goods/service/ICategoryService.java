package com.wzliulan.mall.cloud.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzliulan.mall.cloud.domain.dto.mall.CategoryQueryDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Category;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-11
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 树形品类查询方法
     * @param categoryQueryDto 查询条件对象
     * @return 返回树形结构的品类列表
     */
    List<Category> queryTreeCategory(CategoryQueryDto categoryQueryDto);
}
