package com.wzliulan.mall.cloud.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.LabelQueryDto;
import com.wzliulan.mall.cloud.domain.model.article.Label;
import com.wzliulan.mall.cloud.article.mapper.LabelMapper;
import com.wzliulan.mall.cloud.article.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {
    @Override
    public IPage<Label> queryByPage(LabelQueryDto labelReqDto) {
        IPage<Label> labelIPage = baseMapper.queryByPage(labelReqDto.getPage(), labelReqDto);
        return labelIPage;
    }

    @Override
    public boolean updateById(Label label) {
        // 设置更新时间
        label.setUpdateDate(new Date());
        // 更新标签数据
        return super.updateById(label);
    }
}
