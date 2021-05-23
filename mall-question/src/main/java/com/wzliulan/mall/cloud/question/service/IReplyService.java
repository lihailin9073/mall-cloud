package com.wzliulan.mall.cloud.question.service;

import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.question.Reply;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 回答信息表 服务类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-18
 */
public interface IReplyService extends IService<Reply> {
    /**
     * 回复递归查询方法
     * @param questionId 问题ID
     * @return
     */
    ApiResponse findByQuestionId(@Param("questionId") String questionId);

    /**
     * 回复递归删除方法
     * @param replyId 回复ID
     * @return
     */
    ApiResponse deleteById(String replyId);

    /**
     * 回复发表方法
     * @param reply 回复表对象
     * @return
     */
    ApiResponse add(Reply reply);
}
