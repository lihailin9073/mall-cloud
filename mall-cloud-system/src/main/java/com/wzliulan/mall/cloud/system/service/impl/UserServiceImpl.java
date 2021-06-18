package com.wzliulan.mall.cloud.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.*;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.system.User;
import com.wzliulan.mall.cloud.feign.IArticleFeign;
import com.wzliulan.mall.cloud.feign.IQuestionFeign;
import com.wzliulan.mall.cloud.system.mapper.UserMapper;
import com.wzliulan.mall.cloud.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<String> findRoleIdByUser(String userId) {
        List<String> roleIdList = baseMapper.findRoleIdByUser(userId);
        return roleIdList;
    }

    @Transactional
    @Override
    public boolean saveUserRole(String userId, List<String> roleList) {
        try {
            // 1、删除用户原来分配的角色
            baseMapper.deleteUserRoleByUserId(userId);
            // 2、保存用户重新分配的角色
            if (CollectionUtils.isNotEmpty(roleList)) {
                baseMapper.saveUserRole(userId, roleList);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public IPage<User> queryByPage(UserQueryDto userQueryDto) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(userQueryDto.getName())) {
            wrapper.like("username", userQueryDto.getName());
        }
        if (StringUtils.isNotEmpty(userQueryDto.getPhone())) {
            wrapper.like("mobile", userQueryDto.getPhone());
        }
        wrapper.orderByDesc("update_date");
        // 分页查询
        IPage<User> iPage = baseMapper.selectPage(userQueryDto.getPage(), wrapper);
        return iPage;
    }

    @Override
    public boolean deleteUser(String userId) {
        User user = baseMapper.selectById(userId);
        if (null == user) {
            return false;
        }
        user.setIsEnabled(0); // 0=已删除，1=未删除
        user.setUpdateDate(new Date());
        try {
            baseMapper.updateById(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ApiResponse verifyPassword(UserVerifyPasswordDto verifyPasswordDto) {
        if (StringUtils.isEmpty(verifyPasswordDto.getUserId())) {
            return ApiResponse.error("用户ID为空，请检查");
        }
        if (StringUtils.isEmpty(verifyPasswordDto.getOldPassword())) {
            return ApiResponse.error("用户旧密码为空，请检查");
        }

        User user = baseMapper.selectById(verifyPasswordDto.getUserId());
        if (null == user) {
            return ApiResponse.error("用户不存在");
        }

        // 校验密码
        boolean result = passwordEncoder.matches(verifyPasswordDto.getOldPassword(), user.getPassword());
        if (result) {
            return ApiResponse.ok("密码校验成功");
        }
        return ApiResponse.error("密码校验失败");
    }

    @Override
    public ApiResponse updatePassword(UserUpdatePasswordDto updatePasswordDto) {
        if (StringUtils.isEmpty(updatePasswordDto.getUserId())) {
            return ApiResponse.error("用户ID为空，请检查");
        }
        if (StringUtils.isEmpty(updatePasswordDto.getNewPassword())) {
            return ApiResponse.error("用户新密码为空，请检查");
        }
        if (StringUtils.isEmpty(updatePasswordDto.getRepeatPassword())) {
            return ApiResponse.error("用户确认密码为空，请检查");
        }
        if (!updatePasswordDto.getNewPassword().equals(updatePasswordDto.getRepeatPassword())) {
            return ApiResponse.error("用户新旧密码不一致，请检查");
        }

        User user = baseMapper.selectById(updatePasswordDto.getUserId());
        if (null == user) {
            return ApiResponse.error("用户不存在");
        }

        // 提供了原密码、则校验新旧密码
        if (StringUtils.isNotEmpty(updatePasswordDto.getOldPassword())) {
            boolean result = passwordEncoder.matches(updatePasswordDto.getOldPassword(), user.getPassword());
            if (!result) return ApiResponse.error("原密码错误");
        }

        // 设置新密码
        String securityPassword = passwordEncoder.encode(updatePasswordDto.getNewPassword());
        user.setPassword(securityPassword);
        user.setPwdUpdateDate(new Date());

        try {
            baseMapper.updateById(user);
            return ApiResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("密码更新失败，请重试");
        }
    }

    @Autowired
    private IArticleFeign articleFeign;
    @Autowired
    private IQuestionFeign questionFeign;

    @Override
    public ApiResponse updateUserInfo(UserInfoUpdateDto userInfoUpdateDto) {
        // 1、查询原用户信息
        User user = baseMapper.selectById(userInfoUpdateDto.getId());

        // 2、更新本地用户信息表 b_user
        BeanUtils.copyProperties(userInfoUpdateDto, user);
        user.setUpdateDate(new Date());
        try {
            baseMapper.updateById(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }

        // 3、更新远程各微服务中的用户昵称和头像
        if (StringUtils.equals(userInfoUpdateDto.getNickName(), user.getNickName())
                ||StringUtils.equals(userInfoUpdateDto.getImageUrl(), user.getImageUrl())) { // 判断用户的昵称、头像是否被更改
            // 准备数据
            UserBaseInfoUpdateDto userBaseInfoUpdateDto = UserBaseInfoUpdateDto.builder().build();
            //BeanUtils.copyProperties(userInfoUpdateDto, userBaseInfoUpdateDto);
            userBaseInfoUpdateDto.setUserId(user.getId());
            userBaseInfoUpdateDto.setNickName(user.getNickName());
            userBaseInfoUpdateDto.setUserImage(user.getImageUrl());
            // 更新文章微服务中的用户信息
            articleFeign.updateUserInfo(userBaseInfoUpdateDto);
            // 更新问答微服务中的用户信息
            questionFeign.updateUserInfo(userBaseInfoUpdateDto);
        }

        return ApiResponse.ok();
    }

    @Override
    public ApiResponse countTotalUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("is_enabled", 1);
        Integer total = baseMapper.selectCount(wrapper);
        return ApiResponse.ok(total);
    }

    @Override
    public ApiResponse userIsExists(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName);
        User user = baseMapper.selectOne(wrapper);
        if (null == user) {
            return ApiResponse.ok(0);
        }
        return ApiResponse.ok(1);
    }

    @Override
    public ApiResponse register(UserRegisterDto userRegisterDto) {
        if (StringUtils.isEmpty( userRegisterDto.getUserName())) {
            return ApiResponse.error("注册账号为空，请检查");
        }
        if (StringUtils.isEmpty(userRegisterDto.getPassword())) {
            return ApiResponse.error("登录密码为空，请检查");
        }
        if (StringUtils.isEmpty(userRegisterDto.getRePassword())) {
            return ApiResponse.error("确认密码为空，请检查");
        }
        if (!StringUtils.equals(userRegisterDto.getPassword(), userRegisterDto.getRePassword())) {
            log.info("登录密码={}，确认密码={}", userRegisterDto.getPassword(), userRegisterDto.getRePassword());
            return ApiResponse.error("登录密码和确认密码不一致，请检查");
        }
        String exists = this.userIsExists(userRegisterDto.getUserName()).getData().toString();
        if (StringUtils.equals(exists, "1")) { // 账号已存在
            return ApiResponse.error("账号已存在，请使用其它账号注册");
        }

        User user = User.builder()
                .username(userRegisterDto.getUserName())
                .nickName(userRegisterDto.getUserName())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .build();

        // 注册用户
        try {
            this.save(user);
            return ApiResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public User findByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        User user = baseMapper.selectOne(queryWrapper);
        return user;
    }
}
