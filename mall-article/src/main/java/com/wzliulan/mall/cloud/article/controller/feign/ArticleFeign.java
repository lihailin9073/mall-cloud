package com.wzliulan.mall.cloud.article.controller.feign;

import com.wzliulan.mall.cloud.article.service.IArticleService;
import com.wzliulan.mall.cloud.article.service.ILabelService;
import com.wzliulan.mall.cloud.domain.dto.blog.UserBaseInfoUpdateDto;
import com.wzliulan.mall.cloud.domain.model.article.Label;
import com.wzliulan.mall.cloud.feign.IArticleFeign;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(description = "提供给其它微服务进行Feign远程调用的接口")
@RestController
public class ArticleFeign implements IArticleFeign {
    @Autowired
    private ILabelService labelService;
    @Autowired
    private IArticleService articleService;

    @Override
    public List<Label> getLabelListByIds(List<String> labelIds) {
        // IService<Label>中已经实现了此批量查询标签信息功能，由mybatis-plus提供
        return labelService.listByIds(labelIds);
    }

    /**
     * 更新文章表、评论表中的用户信息
     * @param userBaseInfoUpdateDto 用户数据更新对象
     * @return
     */
    @Override
    public boolean updateUserInfo(UserBaseInfoUpdateDto userBaseInfoUpdateDto) {
        log.info("...更新文章微服务的用户信息...");
        return articleService.updateArticleUserInfo(userBaseInfoUpdateDto);
    }

}
