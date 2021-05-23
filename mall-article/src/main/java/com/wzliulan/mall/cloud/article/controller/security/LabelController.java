package com.wzliulan.mall.cloud.article.controller.security;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.article.service.ILabelService;
import com.wzliulan.mall.cloud.domain.dto.blog.LabelQueryDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Label;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Api(description = "标签相关服务api")
@Slf4j
@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private ILabelService labelService;

    @ApiOperation("标签查询接口")
    @PostMapping("/search")
    public ApiResponse search(@RequestBody LabelQueryDto labelReqDto) {
        //log.info("--------->search");
        try {
            IPage<Label> labelIPage = labelService.queryByPage(labelReqDto);
            return ApiResponse.ok("查询成功", labelIPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常");
        }
    }

    @ApiOperation("标签详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true),
    })
    @GetMapping("/find/{id}")
    public ApiResponse find(@PathVariable("id") String id) {
        Label label = null;
        try {
            label = labelService.getById(id);
            return ApiResponse.ok(label);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查找服务异常！");
        }
    }

    @ApiOperation("标签更新接口")
    @PutMapping("/update")
    public ApiResponse update(@RequestBody Label label) {
        boolean res = labelService.updateById(label);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("更新失败");
        }
    }

    @ApiOperation("标签创建接口")
    @PostMapping("/add")
    public ApiResponse add(@RequestBody Label label) {
        boolean res = labelService.save(label);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("创建失败");
        }
    }

    @ApiOperation("标签删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true)
    })
    @DeleteMapping("/remove/{id}")
    public ApiResponse remove(@PathVariable("id") String id) {
        boolean res = labelService.removeById(id);
        if (res) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.error("删除失败");
        }
    }
}
