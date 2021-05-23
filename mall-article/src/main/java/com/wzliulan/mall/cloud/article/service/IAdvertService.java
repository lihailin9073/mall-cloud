package com.wzliulan.mall.cloud.article.service;

import com.wzliulan.mall.cloud.domain.dto.blog.AdvertQueryDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Advert;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 广告信息表 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
public interface IAdvertService extends IService<Advert> {
    /**
     * 广告查询方法
     * @param advertQueryDto 广告查询请求Dto
     * @return
     */
    ApiResponse queryByPage(AdvertQueryDto advertQueryDto);

    /**
     * 广告删除方法
     * @param id 广告ID
     * @return
     */
    ApiResponse deleteById(String id);

    /**
     * 指定位置广告信息查询方法
     * @param position 广告位编号
     * @return
     */
    ApiResponse findByPosition(Integer position);
}
