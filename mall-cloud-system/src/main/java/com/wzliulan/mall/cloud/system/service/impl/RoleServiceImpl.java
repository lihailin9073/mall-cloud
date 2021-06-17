package com.wzliulan.mall.cloud.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.RoleQueryDto;
import com.wzliulan.mall.cloud.domain.model.system.Role;
import com.wzliulan.mall.cloud.system.mapper.RoleMapper;
import com.wzliulan.mall.cloud.system.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public IPage<Role> queryByPage(RoleQueryDto roleQueryDto) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(roleQueryDto.getName())) {
            wrapper.like("name", roleQueryDto.getName());
        }
        IPage<Role> iPage = baseMapper.selectPage(roleQueryDto.getPage(), wrapper);
        return iPage;
    }

    @Transactional
    @Override
    public boolean deleteRole(String roleId) {
        try {
            baseMapper.deleteById(roleId);
            baseMapper.deleteRoleMenuByRoleId(roleId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<String> findMenuIdByRole(String roleId) {
        return baseMapper.findMenuIdByRole(roleId);
    }

    @Transactional
    @Override
    public boolean saveRoleMenu(String roleId, List<String> menuList) {
        try {
            // 1、删除角色原来分配的菜单
            baseMapper.deleteRoleMenuByRoleId(roleId);
            // 2、保存角色重新分配的菜单
            if (CollectionUtils.isNotEmpty(menuList)) {
                baseMapper.saveRoleMenu(roleId, menuList);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
