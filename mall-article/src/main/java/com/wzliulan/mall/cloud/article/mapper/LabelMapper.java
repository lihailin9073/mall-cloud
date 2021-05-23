package com.wzliulan.mall.cloud.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wzliulan.mall.cloud.domain.dto.blog.LabelQueryDto;
import com.wzliulan.mall.cloud.domain.model.article.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-12
 */
public interface LabelMapper extends BaseMapper<Label> {
    /**
     * 标签分页查询
     *  说明：->自定义SQL分页，只需要在mapper.xml中写不带分页查询功能的sql语句即可，MyBatis-Plus会自动将sql做分页处理
     *        ->需满足：第一个参数是IPage对象，第二个参数是查询条件，最终会将查询到的数据封装到IPage对象中
     * @return
     */
    IPage<Label> queryByPage(IPage<Label> iPage, @Param("labelReqDto") LabelQueryDto labelReqDto);
}
