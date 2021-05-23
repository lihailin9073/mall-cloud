package com.wzliulan.mall.cloud.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.LabelQueryDto;
import com.wzliulan.mall.cloud.domain.model.article.Label;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
public interface ILabelService extends IService<Label> {
    /**
     * 标签查询方法
     * @param labelReqDto 标签查询请求Dto
     * @return 返回标签分页查询数据 IPage<Label>
     */
    IPage<Label> queryByPage(LabelQueryDto labelReqDto);
}
