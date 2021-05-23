package com.wzliulan.mall.cloud.question.controller.security;


import com.wzliulan.mall.cloud.domain.dto.blog.ReplyAddDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.question.Reply;
import com.wzliulan.mall.cloud.question.service.IReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private IReplyService replyService;

    @ApiOperation("回复递归删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "replyId", value = "回复ID", required = true)
    })
    @DeleteMapping("/delete/{replyId}")
    public ApiResponse findByQuestionId(@PathVariable("replyId") String replyId) {
        return replyService.deleteById(replyId);
    }

    @ApiOperation("回复发表接口")
    @PostMapping("/add")
    public ApiResponse add(@RequestBody ReplyAddDto replyAddDto) {
        Reply reply = Reply.builder().build();
        BeanUtils.copyProperties(replyAddDto, reply);
        return replyService.add(reply);
    }
}
