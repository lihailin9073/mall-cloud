package com.wzliulan.mall.cloud.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.mall.TypeQueryDto;
import com.wzliulan.mall.cloud.domain.entity.mall.TypeDomain;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Type;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品类型 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-17
 */
public interface ITypeService extends IService<Type> {
    /**
     * 商品类型搜索方法
     * @param typeQueryDto 搜索条件对象
     * @return 返回商品类型的分页列表
     */
    IPage<TypeDomain> search(TypeQueryDto typeQueryDto);
}
