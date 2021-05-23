package com.wzliulan.mall.cloud.article.mapper;

import com.wzliulan.mall.cloud.domain.model.article.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论信息表 Mapper 接口
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 文章评论递归查询方法
     * @param articleId 文章ID
     * @return
     */
    List<Comment> queryByArticle(@Param("articleId") String articleId);
}
