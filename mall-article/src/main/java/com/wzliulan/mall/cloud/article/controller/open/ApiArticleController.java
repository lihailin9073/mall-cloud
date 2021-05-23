package com.wzliulan.mall.cloud.article.controller.open;

import com.wzliulan.mall.cloud.article.service.IArticleService;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleQueryIndexDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 【/api/xxx】此控制层接口，不需要身份认证就可以调用到
 */
@Api(description = "文章相关服务api")
@Slf4j
@RestController
@RequestMapping("/api/article")
public class ApiArticleController {
    @Autowired
    private IArticleService articleService;

    @ApiOperation("文章详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true),
    })
    @GetMapping("/find/{id}")
    public ApiResponse find(@PathVariable("id") String id) {
        // int res = 100/0;
        try {
            return ApiResponse.ok( articleService.findArticleAndLabel(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查找服务异常！");
        }
    }

    @ApiOperation("文章浏览数更新接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true),
    })
    @PutMapping("/view-count/{id}")
    public ApiResponse updateArticleViewCount(@PathVariable("id") String id) {
        return articleService.updateArticleViewCount(id);
    }

    @ApiOperation("已发表文章查询接口")
    @PostMapping("/portal/index/articles")
    public ApiResponse queryPortalIndexArticle(@RequestBody ArticleQueryIndexDto articleQueryIndexDto) {
        return articleService.queryPortalIndexArticle(articleQueryIndexDto);
    }

}
