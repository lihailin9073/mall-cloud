package com.wzliulan.mall.cloud.system.mapper;

import com.wzliulan.mall.cloud.domain.model.system.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 用户绑定的角色查询方法
     * @param userId 用户ID
     * @return
     */
    List<String> findRoleIdByUser(@Param("userId") String userId);

    /**
     * 用户角色关系删除方法
     * @param userId 用户ID
     * @return
     */
    boolean deleteUserRoleByUserId(@Param("userId") String userId);
    /**
     * 用户角色分配
     * @param userId 用户ID
     * @param roleList 角色ID清单
     * @return
     */
    boolean saveUserRole(@Param("userId") String userId, @Param("roleList") List<String> roleList);
}
