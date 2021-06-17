package com.wzliulan.mall.cloud.goods.controller.security;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.dto.mall.TagCreateDto;
import com.wzliulan.mall.cloud.domain.dto.mall.TagQueryDto;
import com.wzliulan.mall.cloud.domain.dto.mall.TagUpdateDto;
import com.wzliulan.mall.cloud.domain.model.mall.goods.Tag;
import com.wzliulan.mall.cloud.goods.service.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2021-04-17
 */
@Slf4j
@Api(description = "标签管理接口")
@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    private ITagService tagService;

    @ApiOperation("标签创建")
    @PreAuthorize("hasAuthority('tag:add')")
    @PostMapping("/create")
    public Object create(@RequestBody TagCreateDto tagCreateDto) {
        Tag tag = Tag.builder().build();
        BeanUtils.copyProperties(tagCreateDto, tag);
        tag.setCreateTime(new Date());
        try {
            boolean result = tagService.save(tag);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败！");
        }
    }

    @ApiOperation("标签删除")
    @ApiImplicitParam(name = "id", value = "标签ID", required = true)
    @PreAuthorize("hasAuthority('tag:delete')")
    @DeleteMapping("/remove/{id}")
    public Object remove(@PathVariable("id") String id) {
        boolean result = false;
        try {
            result = tagService.removeById(id);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败！");
        }
    }

    @ApiOperation("标签批量删除")
    @PreAuthorize("hasAuthority('tag:delete')")
    @DeleteMapping("/removes")
    public Object removes(String[] ids) {
        if (ids.length==0) {
            return ApiResponse.error("请提供需要删除的标签ID清单！");
        }

        List<String> idList = new ArrayList<>(Arrays.asList(ids));
        try {
            boolean result = tagService.removeByIds(idList);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败！");
        }
    }

    @ApiOperation("标签修改")
    @PreAuthorize("hasAuthority('tag:update')")
    @PutMapping("/edit")
    public Object edit(@RequestBody TagUpdateDto tagUpdateDto) {
        Tag tag = Tag.builder().build();
        BeanUtils.copyProperties(tagUpdateDto, tag);
        if (StringUtils.isEmpty(tag.getTagId())) {
            return ApiResponse.error("标签ID必须提供！");
        }
        tag.setUpdateTime(new Date());

        try {
            boolean result = tagService.updateById(tag);
            if (result) return ApiResponse.ok();
            return ApiResponse.error("操作失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("操作失败！");
        }
    }
    @ApiOperation("标签查找")
    @PreAuthorize("hasAuthority('tag:find')")
    @GetMapping("/find/{id}")
    public ApiResponse find(@PathVariable("id") Integer id) {
        // TODO
        return ApiResponse.ok(id);
    }

    @ApiOperation("标签搜索")
    @PreAuthorize("hasAuthority('tag:search')")
    @PostMapping("/search")
    public Object search(@RequestBody TagQueryDto queryDto) {
        try {
            IPage<Tag> tagIPage = tagService.queryByPage(queryDto);
            return ApiResponse.ok(tagIPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询服务异常！");
        }
    }

}
