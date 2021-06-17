package com.wzliulan.mall.cloud.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.mall.TagQueryDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-17
 */
public interface ITagService extends IService<Tag> {
    /**
     * 标签分页查询方法
     * @param queryDto 查询条件对象
     * @return 返回标签的分页列表
     */
    IPage<Tag> queryByPage(TagQueryDto queryDto);
}
