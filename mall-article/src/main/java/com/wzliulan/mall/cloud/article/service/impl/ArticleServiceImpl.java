package com.wzliulan.mall.cloud.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleQueryDto;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleQueryIndexDto;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleUserDto;
import com.wzliulan.mall.cloud.domain.dto.blog.UserBaseInfoUpdateDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Article;
import com.wzliulan.mall.cloud.article.mapper.ArticleMapper;
import com.wzliulan.mall.cloud.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzliulan.mall.cloud.enums.ArticleStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 文章信息表 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Override
    public IPage<Article> queryByPage(ArticleQueryDto articleQueryDto) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(articleQueryDto.getTitle())) {
            queryWrapper.like("title", articleQueryDto.getTitle());
        }
        if (null != articleQueryDto.getStatus()) {
            queryWrapper.eq("status", articleQueryDto.getStatus());
        }
        queryWrapper.orderByDesc("update_date");
        IPage<Article> articleIPage = baseMapper.selectPage(articleQueryDto.getPage(), queryWrapper);
        return articleIPage;
    }

    @Override
    public Article findArticleAndLabel(String id) {
        Article articleAndLabel = baseMapper.findArticleAndLabel(id);
        return articleAndLabel;
    }

    /**
     * 新增或修改文章
     * @param article 文章对象 Article
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateArticle(Article article) {
        try {
            // 如果是更新操作：为文章设置更新时间、删除原来的文章标签关联表中的数据
            if (StringUtils.isNotEmpty(article.getId())) {
                article.setUpdateDate(new Date()); // 设置文章更新时间
                baseMapper.deleteArticleLabel(article.getId()); // 删除原来的文章标签关联表中的数据
            }

            // 根据博文的公开状态：设置博文的审核状态
            if (0 == article.getIspublic()) { // 非公开博文，设置审核状态为：审核通过
                article.setIspublic(ArticleStatusEnum.SUCCESS.getCode());
            } else { // 公开博文，设置审核状态为：待审核
                article.setIspublic(ArticleStatusEnum.WAIT.getCode());
            }

            // 新增或修改文章
            super.saveOrUpdate(article);

            // 新增关联数据到文章标签关联表
            if (CollectionUtils.isNotEmpty(article.getLabelIds())) {
                log.info("/////// baseMapper.saveArticleLabel();----------");
                baseMapper.saveArticleLabel(article.getId(), article.getLabelIds());
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ApiResponse findUserArticle(ArticleUserDto articleUserDto) {
        if (StringUtils.isEmpty(articleUserDto.getUserId())) {
            ApiResponse.error("用户ID为空，请检查");
        }

        QueryWrapper<Article> wrapper = new QueryWrapper();
        wrapper.eq("user_id", articleUserDto.getUserId());
        if (StringUtils.isNotEmpty(articleUserDto.getIspublic())) {
            wrapper.eq("ispublic", articleUserDto.getIspublic());
        }
        wrapper.orderByDesc("update_date");

        IPage<Article> articleIPage = baseMapper.selectPage(articleUserDto.getPage(), wrapper);
        return ApiResponse.ok(articleIPage);
    }

    @Override
    public boolean updateArticleUserInfo(UserBaseInfoUpdateDto userBaseInfoUpdateDto) {
        return baseMapper.updateUserInfo(userBaseInfoUpdateDto);
    }

    @Override
    public boolean updateArticleStatus(String id, ArticleStatusEnum articleStatusEnum) {
        Article article = baseMapper.selectById(id);
        try {
            article.setStatus(articleStatusEnum.getCode());
            article.setUpdateDate(new Date());
            baseMapper.updateById(article);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ApiResponse updateArticleThumbsup(String id, int count) {
        if (count!=1 && count!=-1) {
            return ApiResponse.error("无效的参数，请检查");
        }
        if (StringUtils.isEmpty(id)) {
            return ApiResponse.error("无效的文章ID，请检查");
        }

        Article article = baseMapper.selectById(id);
        if (null == article) {
            return ApiResponse.error("此文章不存在，请检查");
        }
        if (article.getThumhup()==0 && count==-1) {
            return ApiResponse.error("此文章未获得任何点赞，无法取消点赞，请检查");
        }
        article.setThumhup(article.getThumhup() + count);
        baseMapper.updateById(article);

        return ApiResponse.ok();
    }

    @Override
    public ApiResponse updateArticleViewCount(String id) {
        if (StringUtils.isEmpty(id)) {
            return ApiResponse.error("参数为空，请检查");
        }
        Article article = baseMapper.selectById(id);
        if (null == article) {
            return ApiResponse.error("此文章不存在，请检查");
        }

        article.setViewCount(article.getViewCount() + 1);
        baseMapper.updateById(article);

        return ApiResponse.ok();
    }

    @Override
    public ApiResponse queryPortalIndexArticle(ArticleQueryIndexDto articleQueryIndexDto) {
        IPage<Article> articleIPage = baseMapper.queryPortalIndexArticle(articleQueryIndexDto.getPage(), articleQueryIndexDto);
        return ApiResponse.ok(articleIPage);
    }

    @Override
    public ApiResponse countTotalArticleNum() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", ArticleStatusEnum.SUCCESS.getCode());
        queryWrapper.eq("ispublic", 1);
        Integer total = baseMapper.selectCount(queryWrapper);
        return ApiResponse.ok(total);
    }

    @Override
    public ApiResponse countCategoryArticleNums() {
        List<Map<String, Object>> nameValueList = baseMapper.countCategoryArticleNums();

        // 封装分类名称
        List<Object> nameList = new ArrayList<>();
        for (Map<String, Object> map : nameValueList) {
            nameList.add(map.get("name"));
        }
        // 封装响应数据
        Map<String, Object> data = new HashMap<>();
        data.put("nameList", nameList);
        data.put("nameValueList", nameValueList);

        return ApiResponse.ok(data);
    }

    @Override
    public ApiResponse count6MonthArticleNums() {
        List<Map<String, Object>> nameValueList = baseMapper.count6MothArticleNums();

        // 封装数据
        List<Object> yearMonthList = new ArrayList<>();
        List<Object> articleNums = new ArrayList<>();
        for (Map<String, Object> map : nameValueList) {
            yearMonthList.add(map.get("year_month"));
            articleNums.add(map.get("total"));
        }
        Map<String, Object> data = new HashMap<>();
        data.put("year_month", yearMonthList);
        data.put("total", articleNums);

        return ApiResponse.ok(data);
    }
}
