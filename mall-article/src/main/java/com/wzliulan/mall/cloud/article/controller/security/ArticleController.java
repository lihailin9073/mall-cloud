package com.wzliulan.mall.cloud.article.controller.security;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.article.service.IArticleService;
import com.wzliulan.mall.cloud.article.utils.AuthUserUtil;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleQueryDto;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleUpdateDto;
import com.wzliulan.mall.cloud.domain.dto.blog.ArticleUserDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Article;
import com.wzliulan.mall.cloud.enums.ArticleStatusEnum;
import com.wzliulan.mall.cloud.domain.model.system.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文章信息表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Api(description = "文章相关服务api")
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @ApiOperation("文章搜索接口")
    @PreAuthorize("hasAuthority('article:view')") // 在调用方法前，会先执行权限校验
    @PostMapping("/search")
    public ApiResponse search(@RequestBody ArticleQueryDto articleQueryDto) {
        //log.info("--------->search");
        try {
            IPage<Article> articleIPage = articleService.queryByPage(articleQueryDto);
            return ApiResponse.ok(articleIPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("文章详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true),
    })
    @PreAuthorize("hasAnyAuthority('article:view', 'article:audit')") // 具备这两个权限中的任意一个就可以访问
    @GetMapping("/find/{id}")
    public ApiResponse find(@PathVariable("id") String id) {
        // 测试：获取认证用户信息
        User userInfo = AuthUserUtil.getAuthUserInfo();
        log.info("获取的用户信息：{}", userInfo);

        Article article = null;
        try {
            article = articleService.findArticleAndLabel(id);
            return ApiResponse.ok( article);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查找服务异常！");
        }
    }

    @ApiOperation("文章更新接口")
    @PutMapping("/update") // 此接口
    public ApiResponse update(@RequestBody ArticleUpdateDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        boolean res = articleService.saveOrUpdateArticle(article);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("更新失败");
        }
    }

    @ApiOperation("文章创建接口")
    @PostMapping("/add")
    public ApiResponse add(@RequestBody ArticleUpdateDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        boolean res = articleService.saveOrUpdateArticle(article);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("创建失败");
        }
    }

    @ApiOperation("文章删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    })
    @PreAuthorize("hasAuthority('article:delete')")
    @DeleteMapping("/remove/{id}")
    public ApiResponse remove(@PathVariable("id") String id) {
        boolean res = articleService.updateArticleStatus(id, ArticleStatusEnum.DELETE);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("删除失败");
        }
    }

    @ApiOperation("文章审核通过接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    })
    @PreAuthorize("hasAuthority('article:audit')")
    @GetMapping("/audit/success/{id}")
    public ApiResponse auditSuccess(@PathVariable("id") String id) {
        boolean res = articleService.updateArticleStatus(id, ArticleStatusEnum.SUCCESS);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("审核失败");
        }
    }

    @ApiOperation("文章审核不通过接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    })
    @PreAuthorize("hasAuthority('article:audit')")
    @GetMapping("/audit/fail/{id}")
    public ApiResponse auditFail(@PathVariable("id") String id) {
        boolean res = articleService.updateArticleStatus(id, ArticleStatusEnum.FAIL);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("审核失败");
        }
    }

    @ApiOperation("个人文章搜索接口")
    @PostMapping("/user")
    public ApiResponse queryUserArticle(@RequestBody ArticleUserDto articleUserDto) {
        return articleService.findUserArticle(articleUserDto);
    }

    @ApiOperation("文章点赞数更新接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true),
            @ApiImplicitParam(name = "count", value = "点赞数[+1 或 -1]", required = true)
    })
    @PutMapping("/thumbsup/{id}/{count}")
    public ApiResponse updateArticleThumbsup(@PathVariable("id") String id, @PathVariable("count") int count) {
        return articleService.updateArticleThumbsup(id, count);
    }

    @ApiOperation("已发表文章数统计接口")
    @GetMapping("/totals")
    public ApiResponse countTotalArticleNum() {
        return articleService.countTotalArticleNum();
    }

    @ApiOperation("各频道文章数统计接口")
    @GetMapping("/category/article-nums")
    public ApiResponse countCategoryArticleNum() {
        return articleService.countCategoryArticleNums();
    }

    @ApiOperation("近6个月文章数统计接口")
    @GetMapping("/6-month/article-nums")
    public ApiResponse count6MonthArticleNum() {
        return articleService.count6MonthArticleNums();
    }
}
