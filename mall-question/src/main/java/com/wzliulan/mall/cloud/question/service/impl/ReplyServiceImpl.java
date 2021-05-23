package com.wzliulan.mall.cloud.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzliulan.mall.cloud.domain.dto.ApiResponse;
import com.wzliulan.mall.cloud.domain.model.question.Question;
import com.wzliulan.mall.cloud.domain.model.question.Reply;
import com.wzliulan.mall.cloud.question.mapper.QuestionMapper;
import com.wzliulan.mall.cloud.question.mapper.ReplyMapper;
import com.wzliulan.mall.cloud.question.service.IReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 回答信息表 服务实现类
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-18
 */
@Slf4j
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {
    @Resource
    private QuestionMapper questionMapper;

    @Override
    public ApiResponse findByQuestionId(String questionId) {
        if (StringUtils.isEmpty(questionId)) {
            return ApiResponse.error("问题ID为空，请检查");
        }

        List<Reply> replyList = baseMapper.findByQuestionId(questionId);
        return ApiResponse.ok(replyList);
    }

    @Override
    @Transactional
    public ApiResponse deleteById(String replyId) {
        if (StringUtils.isEmpty(replyId)) {
            ApiResponse.error("参数错误，请检查");
        }

        List<String> ids = new ArrayList<>();
        ids.add(replyId);
        this.getIds(replyId, ids); // 获取所有子回复ID
        log.info("待删除的所有回复数量：{}", ids.size());

        Reply reply = baseMapper.selectById(replyId);
        Question question = questionMapper.selectById(reply.getQuestionId());

        // 批量删除回复
        int size = baseMapper.deleteBatchIds(ids);
        if (size > 0) { // 更新问题的回复数
            question.setReply(question.getReply() - size);
            question.setUpdateDate(new Date());
            questionMapper.updateById(question);
        }

        return ApiResponse.ok();
    }
    private void getIds(String parentId, List<String> ids) {
        // 根据回复ID查找其所有的子回复ID
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<Reply> replyList = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(replyList)) {
            for (Reply reply : replyList) {
                // 将当前查询得到的子回复ID封装到待删除集合中
                String id = reply.getId();
                ids.add(id);
                // 递归查找所有下层的子回复
                this.getIds(id, ids);
            }
        }
    }

    @Override
    @Transactional
    public ApiResponse add(Reply reply) {
        if (StringUtils.isEmpty(reply.getQuestionId())) {
            return ApiResponse.error("问题ID为空，请检查");
        }

        // 1、新增回复
        baseMapper.insert(reply);
        //this.save(reply);

        // 2、更新问题表中的回复数
        Question question = questionMapper.selectById(reply.getQuestionId());
        question.setReply(question.getReply() + 1);
        question.setUpdateDate(new Date());
        questionMapper.updateById(question);

        return ApiResponse.ok();
    }
}
