package com.wzliulan.mall.cloud.article.controller.security;


import com.wzliulan.mall.cloud.article.service.ICommentService;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论信息表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Api(description = "评论相关服务api")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @ApiOperation("文章评论递归查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article_id", value = "文章ID", required = true),
    })
    @GetMapping("/list/{article_id}")
    public ApiResponse queryByArticle(@PathVariable("article_id") String articleId) {
        return commentService.queryByArticle(articleId);
    }

    @ApiOperation("评论递归删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comment_id", value = "评论ID", required = true),
    })
    @DeleteMapping("/delete/{comment_id}")
    public ApiResponse deleteById(@PathVariable("comment_id") String commentId) {
        return commentService.deleteById(commentId);
    }

}
