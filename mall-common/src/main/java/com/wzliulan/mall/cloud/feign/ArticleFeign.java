package com.wzliulan.mall.cloud.feign;

import com.wzliulan.mall.cloud.domain.dto.blog.UserBaseInfoUpdateDto;
import com.wzliulan.mall.cloud.domain.model.article.Label;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "article-service", contextId = "article-service-client-001")//, configuration = FeignRequestInterceptor.class)
public interface ArticleFeign {

    // allowMultiple = true 表示是数组格式的参数， dataType = "String" 数组中参数的类型
    @ApiImplicitParam(allowMultiple = true, dataType = "String", name="ids", value = "标签ID集合", required = true)
    @ApiOperation("标签查询接口")
    @GetMapping("/api/feign/label/list/{ids}")
    List<Label> getLabelListByIds(@PathVariable("ids") List<String> labelIds);

    @ApiOperation("文章表、评论表中的用户信息更新接口")
    @PutMapping("/feign/article/user")
    boolean updateUserInfo(@RequestBody UserBaseInfoUpdateDto userBaseInfoUpdateDto);

}
