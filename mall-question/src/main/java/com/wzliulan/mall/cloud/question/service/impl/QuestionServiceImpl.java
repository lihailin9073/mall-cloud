package com.wzliulan.mall.cloud.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.BaseQueryDto;
import com.wzliulan.mall.cloud.domain.dto.blog.QuestionUserQueryDto;
import com.wzliulan.mall.cloud.domain.dto.blog.UserBaseInfoUpdateDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.article.Label;
import com.wzliulan.mall.cloud.domain.model.question.Question;
import com.wzliulan.mall.cloud.feign.IArticleFeign;
import com.wzliulan.mall.cloud.question.mapper.QuestionMapper;
import com.wzliulan.mall.cloud.question.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 问题信息表 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-18
 */
@Slf4j
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Autowired
    private IArticleFeign articleFeign;

    @Override
    public IPage<Question> queryHotQuestions(BaseQueryDto<Question> baseQueryDto) {
        String orderKey = "reply";
        IPage<Question> iPage = this.queryQuestions(baseQueryDto, orderKey);
        return iPage;
    }

    @Override
    public IPage<Question> queryLastestQuestions(BaseQueryDto<Question> baseQueryDto) {
        String orderKey = "update_date";
        IPage<Question> iPage = this.queryQuestions(baseQueryDto, orderKey);
        return iPage;
    }

    private IPage<Question> queryQuestions(BaseQueryDto<Question> baseQueryDto, String orderKey) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("status", Arrays.asList(1,2)); // 查询状态不为 0 的问题
        queryWrapper.orderByDesc(orderKey); // 排序
        IPage<Question> iPage = baseMapper.selectPage(baseQueryDto.getPage(), queryWrapper);
        log.info("iPage={}", iPage.getRecords());
        return iPage;
    }

    @Override
    public ApiResponse queryWaitQuestions(BaseQueryDto<Question> baseQueryDto) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("status", Arrays.asList(1,2)); // 查询状态不为 0 的问题
        queryWrapper.eq("reply", 0); // 回复数为 0，即表示待回答
        queryWrapper.orderByDesc("create_date"); // 排序
        IPage<Question> iPage = baseMapper.selectPage(baseQueryDto.getPage(), queryWrapper);
        return ApiResponse.ok(iPage);
    }

    @Override
    public ApiResponse findByLabelId(BaseQueryDto<Question> baseQueryDto, String labelId) {
        if (StringUtils.isEmpty(labelId)) {
            return ApiResponse.error("标签ID为空，请检查");
        }
        IPage<Question> iPage = baseMapper.findByLabelId(baseQueryDto.getPage(), labelId);
        return ApiResponse.ok(iPage);
    }

    @Override
    public ApiResponse findDetails(String id) {
        // 1、查询问题及其归属标签ID清单
        Question question = baseMapper.findQuestionAndLabelIds(id);
        if (null == question) {
            return ApiResponse.error("问题不存在，请检查");
        }

        // 2、Feign查询标签对象
        List<Label> labelList = null;
        if (CollectionUtils.isNotEmpty(question.getLabelIds())) {
            labelList = articleFeign.getLabelListByIds(question.getLabelIds());
            question.setLabelList(labelList);
        }

        return ApiResponse.ok(question);
    }

    @Override
    public ApiResponse updateViewCount(String id) {
        if (StringUtils.isEmpty(id)) {
            return ApiResponse.error("问题ID为空，请检查");
        }

        Question question = baseMapper.selectById(id);
        if (question == null) {
            return ApiResponse.error("问题不存在，请检查");
        }

        question.setViewCount(question.getViewCount() + 1);
        baseMapper.updateById(question);

        return ApiResponse.ok();
    }

    @Override
    @Transactional
    public ApiResponse updateOrSave(Question question) {
        try {
            // 如果是更新操作：为问题设置更新时间、删除原来的问题标签关联表中的数据
            if (StringUtils.isNotEmpty(question.getId())) {
                question.setUpdateDate(new Date()); // 设置问题更新时间
                baseMapper.deleteQuestionLabel(question.getId()); // 删除原来的问题标签关联表中的数据
            }
            // 新增或修改博文
            super.saveOrUpdate(question);

            // 新增关联数据到问题标签关联表
            if (CollectionUtils.isNotEmpty(question.getLabelIds())) {
                log.info("/////// baseMapper.updateOrSave();----------");
                baseMapper.saveQuestionLabel(question.getId(), question.getLabelIds());
            }

            return ApiResponse.ok(question.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ApiResponse deleteById(String id) {
        // 状态status：0=已删除， 1=未解决，2=已解决
        return this.updateStatus(id, 0);
    }

    /**
     * 问题状态更新方法
     * @param id 问题ID
     * @param status 问题状态：0=已删除， 1=未解决，2=已解决
     * @return
     */
    public ApiResponse updateStatus(String id, Integer status) {
        Question question = baseMapper.selectById(id);
        if (null == question) {
            return ApiResponse.error("问题不存在，请检查");
        }
        question.setStatus(status);
        question.setUpdateDate(new Date());

        // 更新状态
        baseMapper.updateById(question);
        return ApiResponse.ok();
    }

    @Override
    public ApiResponse updateQuestionThumbsup(String id, int count) {
        if (count!=1 && count!=-1) {
            return ApiResponse.error("无效的参数，请检查");
        }
        if (StringUtils.isEmpty(id)) {
            return ApiResponse.error("无效的问题ID，请检查");
        }

        Question question = baseMapper.selectById(id);
        if (null == question) {
            return ApiResponse.error("此问题不存在，请检查");
        }
        if (question.getThumhup()==0 && count==-1) {
            return ApiResponse.error("此问题未获得任何点赞，无法取消点赞，请检查");
        }
        question.setThumhup(question.getThumhup() + count);
        baseMapper.updateById(question);

        return ApiResponse.ok();
    }

    @Override
    public ApiResponse findByUserId(QuestionUserQueryDto questionUserQueryDto) {
        if (StringUtils.isEmpty(questionUserQueryDto.getUserId())) {
            return ApiResponse.error("用户ID为空，请检查");
        }
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", questionUserQueryDto.getUserId());
        queryWrapper.in("status", Arrays.asList(1, 2));
        queryWrapper.orderByDesc("update_date");

        IPage<Question> iPage = baseMapper.selectPage(questionUserQueryDto.getPage(), queryWrapper);

        return ApiResponse.ok(iPage);
    }

    @Override
    public ApiResponse countTotalQuestionNum() {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
        Integer countNum = baseMapper.selectCount(wrapper);
        return ApiResponse.ok(countNum);
    }

    @Override
    public boolean updateArticleUserInfo(UserBaseInfoUpdateDto userBaseInfoUpdateDto) {
        return baseMapper.updateUserInfo(userBaseInfoUpdateDto);
    }
}
