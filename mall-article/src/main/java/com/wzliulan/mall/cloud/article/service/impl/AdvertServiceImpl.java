package com.wzliulan.mall.cloud.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.AdvertQueryDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Advert;
import com.wzliulan.mall.cloud.article.mapper.AdvertMapper;
import com.wzliulan.mall.cloud.article.service.IAdvertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzliulan.mall.cloud.properties.AliyunProperties;
import com.wzliulan.mall.cloud.utils.AliyunUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 广告信息表 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, Advert> implements IAdvertService {
    @Autowired
    private AliyunProperties aliyunProperties;

    @Override
    public ApiResponse queryByPage(AdvertQueryDto advertQueryDto) {
        QueryWrapper<Advert> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(advertQueryDto.getTitle())) {
            queryWrapper.like("title", advertQueryDto.getTitle());
        }
        if (advertQueryDto.getStatus() != null) {
            queryWrapper.eq("status", advertQueryDto.getStatus());
        }
        IPage<Advert> iPage = baseMapper.selectPage(advertQueryDto.getPage(), queryWrapper);
        return ApiResponse.ok(iPage);
    }

    @Override
    @Transactional
    public ApiResponse deleteById(String id) {
        try {
            Advert advert = baseMapper.selectById(id);
            if (StringUtils.isNotEmpty(advert.getImageUrl())) {
                AliyunUtil.delete(advert.getImageUrl(), aliyunProperties.getOss());
            }
            baseMapper.deleteById(id);
            return ApiResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ApiResponse findByPosition(Integer position) {
        QueryWrapper<Advert> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("position", position);
        queryWrapper.orderByDesc("sort");

        List<Advert> adverts = baseMapper.selectList(queryWrapper);
        return ApiResponse.ok(adverts);
    }
}
