package com.wzliulan.mall.cloud.question.mapper;

import com.wzliulan.mall.cloud.domain.model.question.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 回答信息表 Mapper 接口
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-18
 */
public interface ReplyMapper extends BaseMapper<Reply> {
    /**
     * 回复递归查询方法
     * @param questionId 问题ID
     * @return
     */
    List<Reply> findByQuestionId(@Param("questionId") String questionId);
}
