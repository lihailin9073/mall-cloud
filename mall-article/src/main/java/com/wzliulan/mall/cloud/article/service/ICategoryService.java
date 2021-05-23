package com.wzliulan.mall.cloud.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.CategoryQueryDto;
import com.wzliulan.mall.cloud.domain.model.article.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章分类表 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 分类查询方法
     * @param categoryReqDto 分类查询请求Dto
     * @return 返回分类的分页查询数据 IPage<Category>
     */
    IPage<Category> queryByPage(CategoryQueryDto categoryReqDto);

    /**
     * 正常状态分类查询方法
     * @return 返回查询结果列表 List<Category>
     */
    List<Category> findAllNormal();

    /**
     * 查询正常状态下的分类及其下的标签信息
     * @return
     */
    List<Category> findNormalCategoryAndLabel();
}
