package com.wzliulan.mall.cloud.article.service;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评论信息表 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
public interface ICommentService extends IService<Comment> {
    /**
     * 文章评论递归查询方法
     * @param articleId 文章ID
     * @return
     */
    ApiResponse queryByArticle(String articleId);

    /**
     * 评论递归删除方法
     * @param commentId 评论ID
     * @return
     */
    ApiResponse deleteById(String commentId);
}
