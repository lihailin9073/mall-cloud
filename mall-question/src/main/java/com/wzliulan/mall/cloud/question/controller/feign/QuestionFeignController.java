package com.wzliulan.mall.cloud.question.controller.feign;

import com.wzliulan.mall.cloud.domain.dto.blog.UserBaseInfoUpdateDto;
import com.wzliulan.mall.cloud.feign.QuestionFeign;
import com.wzliulan.mall.cloud.question.service.IQuestionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(description = "提供给其它微服务进行Feign远程调用的接口")
@RestController
public class QuestionFeignController implements QuestionFeign {
    @Autowired
    private IQuestionService questionService;

    /**
     * 更新问题表、回复表中的用户信息
     * @param userBaseInfoUpdateDto
     * @return
     */
    @Override
    public boolean updateUserInfo(UserBaseInfoUpdateDto userBaseInfoUpdateDto) {
        log.info("请求已进入此方法...");
        return questionService.updateArticleUserInfo(userBaseInfoUpdateDto);
    }
}
