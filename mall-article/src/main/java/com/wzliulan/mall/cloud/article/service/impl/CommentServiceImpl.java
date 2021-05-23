package com.wzliulan.mall.cloud.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Comment;
import com.wzliulan.mall.cloud.article.mapper.CommentMapper;
import com.wzliulan.mall.cloud.article.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论信息表 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public ApiResponse queryByArticle(String articleId) {
        if (StringUtils.isEmpty(articleId)) {
            ApiResponse.error("参数错误，请检查");
        }
        List<Comment> comments = baseMapper.queryByArticle(articleId);
        return ApiResponse.ok(comments);
    }

    @Override
    public ApiResponse deleteById(String commentId) {
        if (StringUtils.isEmpty(commentId)) {
            ApiResponse.error("参数错误，请检查");
        }

        List<String> ids = new ArrayList<>();
        ids.add(commentId);
        this.getIds(commentId, ids); // 获取所有子评论ID
        log.info("待删除的所有评论数量：{}", ids.size());

        // 批量删除
        baseMapper.deleteBatchIds(ids);
        return ApiResponse.ok();
    }
    private void getIds(String parentId, List<String> ids) {
        // 根据评论ID查找其所有的子评论ID
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<Comment> commentList = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(commentList)) {
            for (Comment comment : commentList) {
                // 将当前查询得到的子评论ID封装到待删除集合中
                String id = comment.getId();
                ids.add(id);
                // 递归查找所有下层的子评论
                this.getIds(id, ids);
            }
        }
    }
}
