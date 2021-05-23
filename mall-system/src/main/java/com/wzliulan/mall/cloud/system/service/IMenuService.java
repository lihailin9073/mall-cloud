package com.wzliulan.mall.cloud.system.service;

import com.wzliulan.mall.cloud.domain.dto.blog.MenuQueryDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.system.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 菜单树递归查询
     * @param menuQueryDto
     * @return
     */
    List<Menu> queryTreeMenu(MenuQueryDto menuQueryDto);

    /**
     * 根据菜单ID级联删除菜单（即只删除ID及其直属的子菜单，不会做多级删除）
     * @param id 菜单ID
     * @return
     */
    boolean deleteById(String id);

    /**
     * 查询指定用户所拥有的权限菜单树
     * @param userId 用户ID
     * @return
     */
    ApiResponse findUserMenuTree(String userId);

    /**
     * 根据用户ID查询权限菜单（目录、菜单、按钮）
     * @param userId 用户ID
     * @return
     */
    List<Menu> findByUserId(String userId);
}
