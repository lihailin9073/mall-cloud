package com.wzliulan.mall.cloud.system.mapper;

import com.wzliulan.mall.cloud.domain.model.system.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 角色菜单关系删除方法
     * @param roleId 角色ID
     * @return
     */
    boolean deleteRoleMenuByRoleId(@Param("roleId") String roleId);

    /**
     * 角色绑定的菜单查询方法
     * @param roleId 角色ID
     * @return
     */
    List<String> findMenuIdByRole(@Param("roleId") String roleId);

    /**
     * 角色菜单分配
     * @param roleId 角色ID
     * @param menuList 菜单ID清单
     * @return
     */
    boolean saveRoleMenu(@Param("roleId") String roleId, @Param("menuList") List<String> menuList);
}
