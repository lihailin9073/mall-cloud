package com.wzliulan.mall.cloud.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleQueryIndexDto;
import com.wzliulan.mall.cloud.domain.dto.blog.UserBaseInfoUpdateDto;
import com.wzliulan.mall.cloud.domain.model.article.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章信息表 Mapper 接口
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 通过文章ID查询文章详情及标签
     * @param id 文章ID
     * @return 返回文章对象 Article
     */
    Article findArticleAndLabel(String id);

    /**
     * 根据文章ID，删除文章标签关联表中的关联数据
     * @param articleId 文章ID
     * @return
     */
    boolean deleteArticleLabel(String articleId);

    /**
     * 根据文章ID，保存文章标签关联表中的关联数据
     * @param articleId 文章ID
     * @param labelIds 标签ID清单
     * @return
     */
    boolean saveArticleLabel(@Param("articleId") String articleId, @Param("labelIds") List<String> labelIds);

    /**
     * 文章表、评论表关联的用户信息更新方法
     * @param userBaseInfoUpdateDto 数据更新对象
     * @return
     */
    @Transactional
    boolean updateUserInfo(UserBaseInfoUpdateDto userBaseInfoUpdateDto);

    /**
     * 已发表文章查询方法
     * @param page 分页对象
     * @param queryIndexDto 查询条件对象
     * @return
     */
    IPage<Article> queryPortalIndexArticle(IPage<Article> page, @Param("queryIndexDto") ArticleQueryIndexDto queryIndexDto);

    /**
     * 各频道文章数统计方法[调用视图]
     * @return
     */
    List<Map<String, Object>> countCategoryArticleNums();

    /**
     * 近6个月文章数统计方法[调用视图]
     * @return
     */
    List<Map<String, Object>> count6MothArticleNums();
}
