package com.wzliulan.mall.cloud.question.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.BaseQueryDto;
import com.wzliulan.mall.cloud.domain.dto.blog.QuestionUserQueryDto;
import com.wzliulan.mall.cloud.domain.dto.blog.UserBaseInfoUpdateDto;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.question.Question;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 问题信息表 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-18
 */
public interface IQuestionService extends IService<Question> {
    /**
     * 热门问答分页查询方法
     * @param baseQueryDto 基准查询对象
     * @return
     */
    IPage<Question> queryHotQuestions(BaseQueryDto<Question> baseQueryDto);

    /**
     * 最新问答分页查询方法
     * @param baseQueryDto 基准查询对象
     * @return
     */
    IPage<Question> queryLastestQuestions(BaseQueryDto<Question> baseQueryDto);

    /**
     * 待回复问题清单查询方法
     * @param baseQueryDto 基准查询对象
     * @return
     */
    ApiResponse queryWaitQuestions(BaseQueryDto<Question> baseQueryDto);

    /**
     * 标签ID检索问题分页查询方法
     * @param baseQueryDto 基准查询对象
     * @param labelId 标签ID
     * @return
     */
    ApiResponse findByLabelId(BaseQueryDto<Question> baseQueryDto, String labelId);

    /**
     * 问题及其归属标签清单查询方法
     * @param id 问题ID
     * @return
     */
    ApiResponse findDetails(String id);

    /**
     * 问题浏览数更新方法
     * @param id 问题ID
     * @return
     */
    ApiResponse updateViewCount(String id);

    /**
     * 修改或新增问题
     * @param question 问题数据对象
     * @return
     */
    ApiResponse updateOrSave(Question question);

    /**
     * 问题删除方法
     * @param id 问题ID
     * @return
     */
    ApiResponse deleteById(String id);

    /**
     * 问题点赞数更新方法
     * @param id 问题ID
     * @param count 点赞数：+1/-1
     * @return
     */
    ApiResponse updateQuestionThumbsup(String id, int count);

    /**
     * 个人问题查询方法
     * @param questionUserQueryDto 查询条件封装Dto
     * @return
     */
    ApiResponse findByUserId(QuestionUserQueryDto questionUserQueryDto);

    /**
     * 提问总数统计方法
     * @return
     */
    ApiResponse countTotalQuestionNum();

    /**
     * 问题表、回复表中关联的用户信息更新方法
     * @param userBaseInfoUpdateDto 用户信息更新Dto
     * @return
     */
    boolean updateArticleUserInfo(UserBaseInfoUpdateDto userBaseInfoUpdateDto);

}
