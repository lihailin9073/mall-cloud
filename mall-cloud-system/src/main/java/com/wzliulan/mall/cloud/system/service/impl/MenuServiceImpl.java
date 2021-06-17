package com.wzliulan.mall.cloud.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzliulan.mall.cloud.domain.dto.blog.MenuQueryDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.enums.ResultResDtoEnum;
import com.wzliulan.mall.cloud.domain.model.system.Menu;
import com.wzliulan.mall.cloud.system.mapper.MenuMapper;
import com.wzliulan.mall.cloud.system.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单信息表 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> queryTreeMenu(MenuQueryDto menuQueryDto) {
        // 1、条件查询所有菜单
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(menuQueryDto.getName())) {
            queryWrapper.like("name", menuQueryDto.getName());
        }
        queryWrapper.orderByAsc("sort").orderByDesc("update_date");
        List<Menu> menuList = baseMapper.selectList(queryWrapper);

        // 2、将菜单封装为树状结构
        List<Menu> treeMenuList = this.buildTree(menuList);
        return treeMenuList;
    }

    @Transactional
    @Override
    public boolean deleteById(String id) {
        try {
            // 1、删除ID对应的菜单
            baseMapper.deleteById(id);

            // 2、删除直属子菜单
            LambdaQueryWrapper<Menu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Menu::getParentId, id); // 匹配出parentId=id的所有菜单
            baseMapper.delete(lambdaQueryWrapper);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 树形菜单构建方法
     * @param menuList 所有菜单列表
     * @return 返回构建成树形结构的菜单列表
     */
    private List<Menu> buildTree(List<Menu> menuList) {
        // 1、获取所有的根菜单
        List<Menu> rootMenuList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getParentId().equals("0")) { // 寻找根菜单
                rootMenuList.add(menu);
            }
        }

        // 2、获取每个根菜单所包含的子菜单
        for (Menu rootMenu : rootMenuList) {
            // 遍历每一个根菜单，并找到它包含的所有（多级）子菜单
            this.findChildrenMenu(rootMenu, menuList);
        }
        return rootMenuList;
    }

    /**
     * 多级子菜单递归构建方法
     * @param parentMenu 父菜单对象
     * @param menuList 所有菜单列表
     * @return 返回
     */
    private Menu findChildrenMenu(Menu parentMenu, List<Menu> menuList) {
        // 查找子菜单
        List<Menu> childrenMenus = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getParentId().equals(parentMenu.getId())) {
                Menu subMenu = this.findChildrenMenu(menu, menuList);// 递归寻找下一级的子菜单
                childrenMenus.add(subMenu); // 将递归找到的子菜单封装到集合
            }
        }

        // 将找到的子菜单设置到根菜单中
        parentMenu.setChildren(childrenMenus);

        // 返回
        return parentMenu;
    }

    @Override
    public ApiResponse findUserMenuTree(String userId) {
        // 1、根据用户ID查询所有的权限菜单（目录、菜单、按钮）
        List<Menu> menuList = baseMapper.findByUserId(userId);
        // 查询结果集有两种情况：（1）userId对应的用户不存在，menuList为空；（2）userId对应的用户存在但是没有分配权限，则只有一条空记录[因为是多表查询，只要用户存在那么b_user表就会有记录，哪怕查找结果集中只取了b_menu的字段]
        if (CollectionUtils.isEmpty(menuList) || menuList.get(0)==null) {
            return ApiResponse.build(ResultResDtoEnum.MENU_NO);
        }

        // 2、整理结果集：目录和菜单存储在一个List中，按钮存储在另一个List中
        List<Menu> dirMenuList = new ArrayList<>(); // 存放目录和菜单
        List<String> buttonList = new ArrayList<>(); // 存放按钮
        for (Menu menu: menuList) { // 归档目录、菜单
            log.info("菜单类型={}", menu.getType());
            if (menu.getType().equals(1) || menu.getType().equals(2)) {
                log.info("---菜单类型={}", menu.getType());
                dirMenuList.add(menu);
            } else { // 归档按钮
                buttonList.add(menu.getCode());
            }
        }

        // 3、封装成树状结构
        List<Menu> menuTreeList = this.buildTree(dirMenuList);

        // 返回数据
        Map<String, Object> data = new HashMap<>();
        data.put("menuTreeList", menuTreeList);
        data.put("buttonList", buttonList);

        return ApiResponse.ok(data);
    }

    @Override
    public List<Menu> findByUserId(String userId) {
        List<Menu> menuList = baseMapper.findByUserId(userId);
        // 查询结果集有两种情况：（1）userId对应的用户不存在，menuList为空；（2）userId对应的用户存在但是没有分配权限，则只有一条空记录[因为是多表查询，只要用户存在那么b_user表就会有记录，哪怕查找结果集中只取了b_menu的字段]
        if (CollectionUtils.isEmpty(menuList) || menuList.get(0)==null) {
            return null;
        }
        return menuList;
    }
}
