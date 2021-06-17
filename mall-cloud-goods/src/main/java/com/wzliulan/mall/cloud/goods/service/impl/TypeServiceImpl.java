package com.wzliulan.mall.cloud.goods.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.mall.TypeQueryDto;
import com.wzliulan.mall.cloud.domain.entity.mall.TypeDomain;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Type;
import com.wzliulan.mall.cloud.goods.service.ITypeService;
import com.wzliulan.mall.cloud.goods.mapper.TypeMapper;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {
    @Resource
    private TypeMapper typeMapper;

    @Override
    public IPage<TypeDomain> search(TypeQueryDto typeQueryDto) {
        IPage<TypeDomain> typeDomainIPage = typeMapper.typeManageList(typeQueryDto.getPage(), typeQueryDto);
        return typeDomainIPage;
    }
}
