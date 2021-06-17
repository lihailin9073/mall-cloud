package com.wzliulan.mall.cloud.article.controller.open;


import com.wzliulan.mall.cloud.article.service.ICommentService;
import com.wzliulan.mall.cloud.domain.dto.blog.CommentAddDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Comment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/api/comment")
public class ApiCommentController {
    @Autowired
    private ICommentService commentService;

    @ApiOperation("评论发表接口")
    @PostMapping("/add")
    public ApiResponse add(@RequestBody CommentAddDto commentAddDto) {
        Comment comment = Comment.builder().build();
        BeanUtils.copyProperties(commentAddDto, comment);
        commentService.save(comment);
        return ApiResponse.ok();
    }

    @ApiOperation("文章评论递归搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article_id", value = "文章ID", required = true),
    })
    @GetMapping("/list/{article_id}")
    public ApiResponse queryByArticle(@PathVariable("article_id") String articleId) {
        return commentService.queryByArticle(articleId);
    }
}
