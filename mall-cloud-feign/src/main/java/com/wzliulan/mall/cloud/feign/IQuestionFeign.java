package com.wzliulan.mall.cloud.feign;

import com.wzliulan.mall.cloud.domain.dto.blog.UserBaseInfoUpdateDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "question-service", contextId = "question-service-client-001" )//, configuration = FeignRequestInterceptor.class)
public interface IQuestionFeign {
    @ApiOperation("问题表、回复表中的用户信息更新接口")
    @PutMapping("/feign/question/user")
    boolean updateUserInfo(@RequestBody UserBaseInfoUpdateDto userBaseInfoUpdateDto);
}
