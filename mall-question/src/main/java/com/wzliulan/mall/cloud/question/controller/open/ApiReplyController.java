package com.wzliulan.mall.cloud.question.controller.open;


import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.question.service.IReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 回答信息表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-18
 */
@Api(description = "回复相关服务api")
@RestController
@RequestMapping("/api/reply")
public class ApiReplyController {
    @Autowired
    private IReplyService replyService;

    @ApiOperation("回复递归搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionId", value = "问题ID", required = true)
    })
    @GetMapping("/list/{questionId}")
    public ApiResponse findByQuestionId(@PathVariable("questionId") String questionId) {
        return replyService.findByQuestionId(questionId);
    }

}
