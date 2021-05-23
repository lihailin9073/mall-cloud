package com.wzliulan.mall.cloud.system.mapper;

import com.wzliulan.mall.cloud.domain.model.system.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单信息表 Mapper 接口
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-21
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 查询指定用户所用的菜单权限（目录、菜单、按钮）
     * @param userId 用户ID
     * @return
     */
    List<Menu> findByUserId(@Param("userId") String userId);
}
