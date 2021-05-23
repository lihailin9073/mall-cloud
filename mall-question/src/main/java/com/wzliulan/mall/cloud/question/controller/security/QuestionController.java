package com.wzliulan.mall.cloud.question.controller.security;


import com.wzliulan.mall.cloud.domain.dto.blog.QuestionAddOrUpdateDto;
import com.wzliulan.mall.cloud.domain.dto.blog.QuestionUserQueryDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.question.Question;
import com.wzliulan.mall.cloud.question.service.IQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @ApiOperation("问题新增接口")
    @PostMapping("/save")
    public ApiResponse save(@RequestBody QuestionAddOrUpdateDto questionAddOrUpdateDto) {
        Question question = Question.builder().build();
        BeanUtils.copyProperties(questionAddOrUpdateDto, question);
        return questionService.updateOrSave(question);
    }

    @ApiOperation("问题更新接口")
    @PutMapping("/update")
    public ApiResponse update(@RequestBody QuestionAddOrUpdateDto questionAddOrUpdateDto) {
        Question question = Question.builder().build();
        BeanUtils.copyProperties(questionAddOrUpdateDto, question);
        return questionService.updateOrSave(question);
    }

    @ApiOperation("问题点赞数更新接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问题ID", required = true),
            @ApiImplicitParam(name = "count", value = "点赞数[+1 或 -1]", required = true)
    })
    @PutMapping("/thumbsup/{id}/{count}")
    public ApiResponse updateArticleThumbsup(@PathVariable("id") String id, @PathVariable("count") int count) {
        return questionService.updateQuestionThumbsup(id, count);
    }

    @ApiOperation("个人问题查询接口")
    @PostMapping("/user")
    public ApiResponse findByUserId(@RequestBody QuestionUserQueryDto questionUserQueryDto) {
        return questionService.findByUserId(questionUserQueryDto);
    }

    @ApiOperation("提问总数统计接口")
    @GetMapping("/count")
    public ApiResponse countTotalQuestionNum() {
        return questionService.countTotalQuestionNum();
    }

    @ApiOperation("问题删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问题ID", required = true),
    })
    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable("id") String id) {
        return questionService.deleteById(id);
    }

}
