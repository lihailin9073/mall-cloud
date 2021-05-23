package com.wzliulan.mall.cloud.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.CategoryQueryDto;
import com.wzliulan.mall.cloud.domain.model.article.Category;
import com.wzliulan.mall.cloud.article.mapper.CategoryMapper;
import com.wzliulan.mall.cloud.article.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文章分类表 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Override
    public IPage<Category> queryByPage(CategoryQueryDto categoryReqDto) {
        // 1、构造查询条件
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(categoryReqDto.getName())) { // 封装分类名称作为查询条件
            wrapper.like("name", categoryReqDto.getName());
        }
        if (null != categoryReqDto.getStatus()) { // 封装分类状态作为查询条件
            wrapper.eq("status", categoryReqDto.getStatus());
        }
        wrapper.orderByDesc("status").orderByAsc("sort"); // 排序

        // 2、分页查询数据
        IPage<Category> categoryIPage = baseMapper.selectPage(categoryReqDto.getPage(), wrapper);

        // 返回查询结果
        return categoryIPage;
    }

    @Override
    public boolean updateById(Category category) {
        // 设置更新时间
        category.setUpdateDate(new Date());
        // 更新分类数据
        return super.updateById(category);
    }

    @Override
    public List<Category> findAllNormal() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "1");
        List<Category> categories = baseMapper.selectList(queryWrapper);
        return categories;
    }

    @Override
    public List<Category> findNormalCategoryAndLabel() {
        List<Category> categories = baseMapper.queryNormalCategoryAndLabel();
        return categories;
    }
}
