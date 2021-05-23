package com.wzliulan.mall.cloud.question.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.UserBaseInfoUpdateDto;
import com.wzliulan.mall.cloud.domain.model.question.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 问题信息表 Mapper 接口
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-18
 */
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 问题表、回复表关联的用户信息更新方法
     * @param userBaseInfoUpdateDto
     * @return
     */
    @Transactional
    boolean updateUserInfo(UserBaseInfoUpdateDto userBaseInfoUpdateDto);

    /**
     * 标签ID检索问题分页查询方法
     * @param page 分页对象
     * @param labelId 标签ID
     * @return
     */
    IPage<Question> findByLabelId(IPage<Question> page, @Param("labelId") String labelId);

    /**
     * 问题及其归属标签ID清单查询方法
     * @param id 问题ID
     * @return
     */
    Question findQuestionAndLabelIds(@Param("id") String id);

    /**
     * 问题标签关系清除方法
     * @param questionId 问题ID
     * @return
     */
    boolean deleteQuestionLabel(@Param("questionId") String questionId);

    /**
     * 问题标签关系建立方法
     * @param questionId 问题ID
     * @param labelIds 标签ID集合
     * @return
     */
    boolean saveQuestionLabel(@Param("questionId") String questionId, @Param("labelIds")List<String> labelIds);
}
