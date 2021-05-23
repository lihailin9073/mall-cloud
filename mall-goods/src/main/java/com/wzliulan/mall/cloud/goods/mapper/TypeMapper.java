package com.wzliulan.mall.cloud.goods.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.mall.TypeQueryDto;
import com.wzliulan.mall.cloud.domain.entity.mall.TypeDomain;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品类型 Mapper 接口
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-17
 */
public interface TypeMapper extends BaseMapper<Type> {
    /**
     * 类型管理列表查询方法
     * @param page 分页对象
     * @param typeQueryDto 查询条件对象
     * @return 返回商品类型的分页列表
     *
     * 分页查询
     *    说明：->自定义SQL分页，只需要在mapper.xml中写不带分页查询功能的sql语句即可，MyBatis-Plus会自动将sql做分页处理
     *          ->需满足：第一个参数是IPage对象，第二个参数是查询条件，最终会将查询到的数据封装到IPage对象中
     */
    IPage<TypeDomain> typeManageList(IPage<TypeDomain> page, @Param("typeQueryDto")TypeQueryDto typeQueryDto);
}
