package com.wzliulan.mall.cloud.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.mall.TagQueryDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Tag;
import com.wzliulan.mall.cloud.goods.service.ITagService;
import com.wzliulan.mall.cloud.goods.mapper.TagMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-17
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {
    @Override
    public IPage<Tag> queryByPage(TagQueryDto queryDto) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(queryDto.getName())) {
            wrapper.like("tag_words", queryDto.getName());
        }
        wrapper.orderByDesc("create_time");

        IPage<Tag> tagIPage = baseMapper.selectPage(queryDto.getPage(), wrapper);
        return tagIPage;
    }
}
