package com.wzliulan.mall.cloud.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.*;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.system.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wzliulan.mall.cloud.domain.dto.blog.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
public interface IUserService extends IService<User> {
    /**
     * 用户分页查询方法
     * @param userQueryDto
     * @return
     */
    IPage<User> queryByPage(UserQueryDto userQueryDto);

    /**
     * 用户绑定的菜单查询方法
     * @param userId 用户ID
     * @return
     */
    List<String> findRoleIdByUser(String userId);

    /**
     * 用户角色分配
     * @param userId 用户ID
     * @param roleList 角色ID清单
     * @return
     */
    boolean saveUserRole(String userId, List<String> roleList);

    /**
     * 用户逻辑删除方法
     * @param userId 用户ID
     * @return
     */
    boolean deleteUser(String userId);

    /**
     * 密码校验方法
     * @param verifyPasswordDto 密码校验参数对象
     * @return
     */
    ApiResponse verifyPassword(UserVerifyPasswordDto verifyPasswordDto);

    /**
     *  密码更新方法
     * @param updatePasswordDto 密码更新参数对象
     * @return
     */
    ApiResponse updatePassword(UserUpdatePasswordDto updatePasswordDto);

    /**
     * 更新用户信息
     * @param userInfoUpdateDto 用户信息Dto
     * @return
     */
    ApiResponse updateUserInfo(UserInfoUpdateDto userInfoUpdateDto);

    /**
     * 统计系统用户总数
     * @return
     */
    ApiResponse countTotalUser();

    /**
     * 检查用户是否存在
     * @param userName 用户账号
     * @return 0=不存在，1=已存在
     */
    ApiResponse userIsExists(String userName);

    /**
     * 用户注册方法
     * @param userRegisterDto 用户注册Dto
     * @return
     */
    ApiResponse register(UserRegisterDto userRegisterDto);

    /**
     * 用户查找方法
     * @param userName 用户账号
     * @return
     */
    User findByUserName(String userName);
}
