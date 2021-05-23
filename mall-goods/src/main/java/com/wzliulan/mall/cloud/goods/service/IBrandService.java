package com.wzliulan.mall.cloud.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.mall.BrandQueryDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-10
 */
public interface IBrandService extends IService<Brand> {
    /**
     * 品牌查询方法
     * @param brandQueryDto 品牌查询对象
     * @return 品牌分页数据
     */
    IPage<Brand> queryByPage(BrandQueryDto brandQueryDto);
}
