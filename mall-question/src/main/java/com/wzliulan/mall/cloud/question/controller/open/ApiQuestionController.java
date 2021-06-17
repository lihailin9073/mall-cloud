package com.wzliulan.mall.cloud.question.controller.open;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.BaseQueryDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.question.Question;
import com.wzliulan.mall.cloud.question.service.IQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 问题信息表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-18
 */
@Api(description = "问题相关服务api")
@RestController
@RequestMapping("/api/question")
public class ApiQuestionController {
    @Autowired
    private IQuestionService questionService;

    @ApiOperation("热门问答分页搜索接口")
    @PostMapping("/query-hot")
    public ApiResponse queryHotQuestionList(@RequestBody BaseQueryDto<Question> baseQueryDto) {
        IPage<Question> iPage = null;
        try {
            iPage = questionService.queryHotQuestions(baseQueryDto);
            return ApiResponse.ok(iPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("最新问答分页搜索接口")
    @PostMapping("/query-lastest")
    public ApiResponse queryLastestQuestionList(@RequestBody BaseQueryDto<Question> baseQueryDto) {
        IPage<Question> iPage = null;
        try {
            iPage = questionService.queryLastestQuestions(baseQueryDto);
            return ApiResponse.ok(iPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("待回复问题清单搜索接口")
    @PostMapping("/query-wait")
    public ApiResponse queryWaitQuestionList(@RequestBody BaseQueryDto<Question> baseQueryDto) {
        return ApiResponse.ok(questionService.queryWaitQuestions(baseQueryDto));
    }

    @ApiOperation("标签ID检索问题分页搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "labelId", value = "标签ID", required = true),
    })
    @PostMapping("/query-by-label-id/{labelId}")
    public ApiResponse queryByLabelId(@RequestBody BaseQueryDto<Question> baseQueryDto, @PathVariable("labelId") String labelId) {
        IPage<Question> iPage = null;
        try {
            return questionService.findByLabelId(baseQueryDto, labelId);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("Feign查询标签及问题详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问题ID", required = true),
    })
    @GetMapping("/find/{id}")
    public ApiResponse find(@PathVariable("id") String id) {
        return questionService.findDetails(id);
    }

    @ApiOperation("问题浏览数更新接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问题ID", required = true),
    })
    @PutMapping("/view-count++/{id}")
    public ApiResponse updateViewCount(@PathVariable("id") String id) {
        return questionService.updateViewCount(id);
    }
}
