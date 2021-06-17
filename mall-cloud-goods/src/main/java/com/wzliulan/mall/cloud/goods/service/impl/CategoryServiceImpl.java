package com.wzliulan.mall.cloud.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzliulan.mall.cloud.domain.dto.mall.CategoryQueryDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Category;
import com.wzliulan.mall.cloud.goods.service.ICategoryService;
import com.wzliulan.mall.cloud.goods.mapper.CategoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-11
 */
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Override
    public List<Category> queryTreeCategory(CategoryQueryDto categoryQueryDto) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(categoryQueryDto.getName())) {
            wrapper.like("cat_name", categoryQueryDto.getName());
        }
        if (null != categoryQueryDto.getDisplay()) {
            wrapper.eq("is_show", categoryQueryDto.getDisplay());
        }
        wrapper.orderByAsc("sort_order").orderByDesc("update_time");

        // 查询品类数据
        List<Category> categoryList = baseMapper.selectList(wrapper);

        // 封装品类数据
        List<Category> treeList = this.buildTree(categoryList);

        return treeList;
    }

    /**
     * 树形数据构建方法
     * @param categoryList 品类列表
     * @return 返回树形结构的品类列表
     */
    private List<Category> buildTree(List<Category> categoryList) {
        // 1、获取所有的根品类
        List<Category> rootCategoryList = new ArrayList<>();
        for (Category category : categoryList) {
            if (category.getParentId().equals("0")) { // 寻找根品类
                rootCategoryList.add(category);
            }
        }
        //log.error("------根品类数量：{}", rootCategoryList.size());

        // 2、获取每个根品类所包含的子品类
        for (Category category : rootCategoryList) {
            // 遍历每一个根品类，并找到它包含的所有（多级）子品类
            this.findChildrenMenu(category, categoryList);
        }
        return rootCategoryList;
    }
    /**
     * 多级子品类递归构建方法
     * @param parentCategory 父品类对象
     * @param categoryList 所有品类列表
     * @return 返回
     */
    private Category findChildrenMenu(Category parentCategory, List<Category> categoryList) {
        // 查找子品类
        List<Category> childrenCategoryList = new ArrayList<>();
        for (Category menu : categoryList) {
            if (menu.getParentId().equals(parentCategory.getCatId())) {
                Category subCategory = this.findChildrenMenu(menu, categoryList);// 递归寻找下一级的子品类
                childrenCategoryList.add(subCategory); // 将递归找到的子品类封装到集合
            }
        }

        // 将找到的子品类设置到根品类中
        parentCategory.setChildren(childrenCategoryList);

        // 返回
        return parentCategory;
    }
}
