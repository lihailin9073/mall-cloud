package com.wzliulan.mall.cloud.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.mall.BrandQueryDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Brand;
import com.wzliulan.mall.cloud.goods.service.IBrandService;
import com.wzliulan.mall.cloud.goods.mapper.BrandMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-10
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
    @Override
    public IPage<Brand> queryByPage(BrandQueryDto brandQueryDto) {
        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(brandQueryDto.getName())) {
            wrapper.like("brand_name", brandQueryDto.getName());
        }
        if (null != brandQueryDto.getDisplay()) {
            wrapper.eq("is_show", brandQueryDto.getDisplay());
        }
        wrapper.orderByDesc("create_time").orderByAsc("is_show");

        IPage<Brand> brandIPage = baseMapper.selectPage(brandQueryDto.getPage(), wrapper);
        return brandIPage;
    }
}
