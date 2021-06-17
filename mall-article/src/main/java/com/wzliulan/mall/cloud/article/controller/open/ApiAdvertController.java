package com.wzliulan.mall.cloud.article.controller.open;


import com.wzliulan.mall.cloud.article.service.IAdvertService;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 广告信息表 前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
@Api(description = "广告相关服务api")
@RestController
@RequestMapping("/api/advert")
public class ApiAdvertController {
    @Autowired
    private IAdvertService advertService;

    @ApiOperation("指定位置广告信息搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "position", value = "广告位编号", required = true),
    })
    @GetMapping("/find/{position}")
    public ApiResponse find(@PathVariable("position") Integer position) {
        return advertService.findByPosition(position);
    }
}
