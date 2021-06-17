package com.wzliulan.mall.cloud.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleQueryDto;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleQueryIndexDto;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleUserDto;
import com.wzliulan.mall.cloud.domain.dto.blog.UserBaseInfoUpdateDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wzliulan.mall.cloud.enums.ArticleStatusEnum;

/**
 * <p>
 * 文章信息表 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
public interface IArticleService extends IService<Article> {
    /**
     * 文章查询方法
     * @param articleQueryDto 文章查询请求Dto
     * @return 返回文章的分页查询数据 IPage<Article>
     */
    IPage<Article> queryByPage(ArticleQueryDto articleQueryDto);

    /**
     * 通过文章ID查询文章详情及标签
     * @param id 文章ID
     * @return 返回文章对象 Article
     */
    Article findArticleAndLabel(String id);

    /**
     * 新增或修改文章
     * @param article 文章对象 Article
     * @return
     */
    boolean saveOrUpdateArticle(Article article);

    /**
     * 文章状态更新方法
     * @param id
     * @param articleStatusEnum 文章状态枚举类
     * @return
     */
    boolean updateArticleStatus(String id, ArticleStatusEnum articleStatusEnum);

    /**
     * 文章、评论表中关联的用户信息更新方法
     * @param userBaseInfoUpdateDto 用户信息更新Dto
     * @return
     */
    boolean updateArticleUserInfo(UserBaseInfoUpdateDto userBaseInfoUpdateDto);

    /**
     * 用户已发布文章搜索接口
     * @param articleUserDto 查询条件对象
     * @return
     */
    ApiResponse findUserArticle(ArticleUserDto articleUserDto);

    /**
     * 文章点赞数更新方法
     * @param id 文章ID
     * @param count 操作标志：点赞+1，取消点赞-1
     * @return
     */
    ApiResponse updateArticleThumbsup(String id, int count);

    /**
     * 文章浏览数更新方法
     * @param id 文章ID
     * @return
     */
    ApiResponse updateArticleViewCount(String id);

    /**
     * 已发表文章查询方法
     * @param articleQueryIndexDto 查询条件对象
     * @return
     */
    ApiResponse queryPortalIndexArticle(ArticleQueryIndexDto articleQueryIndexDto);

    /**
     * 已发表文章数统计方法
     * @return
     */
    ApiResponse countTotalArticleNum();

    /**
     * 各频道文章数统计方法
     * @return
     */
    ApiResponse countCategoryArticleNums();

    /**
     * 近6个月文章数统计方法
     * @return
     */
    ApiResponse count6MonthArticleNums();
}
