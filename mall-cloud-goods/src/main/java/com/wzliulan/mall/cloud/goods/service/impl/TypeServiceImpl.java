package com.wzliulan.mall.cloud.goods.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.mall.TypeQueryDto;
import com.wzliulan.mall.cloud.domain.entity.mall.GoodsTypeDomain;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Type;
import com.wzliulan.mall.cloud.goods.service.IGoodsTypeService;
import com.wzliulan.mall.cloud.goods.mapper.GoodsTypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 商品类型 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-17
 */
@Service
public class TypeServiceImpl extends ServiceImpl<GoodsTypeMapper, Type> implements IGoodsTypeService {
    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public IPage<GoodsTypeDomain> search(TypeQueryDto typeQueryDto) {
        IPage<GoodsTypeDomain> typeDomainIPage = goodsTypeMapper.typeManageList(typeQueryDto.getPage(), typeQueryDto);
        return typeDomainIPage;
    }
}
