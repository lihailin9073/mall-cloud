package com.wzliulan.mall.cloud.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.RoleQueryDto;
import com.wzliulan.mall.cloud.domain.model.system.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
public interface IRoleService extends IService<Role> {
    /**
     * 角色分页查询方法
     * @param roleQueryDto 查询条件对象
     * @return
     */
    IPage<Role> queryByPage(RoleQueryDto roleQueryDto);

    /**
     * 角色删除方法
     * @param roleId 角色Id
     * @return
     */
    boolean deleteRole(String roleId);

    /**
     * 角色绑定的菜单查询方法
     * @param roleId 角色ID
     * @return
     */
    List<String> findMenuIdByRole(String roleId);

    /**
     * 角色菜单分配
     * @param roleId 角色ID
     * @param menuList 菜单ID清单
     * @return
     */
    boolean saveRoleMenu(String roleId, List<String> menuList);
}
