package org.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.example.demo.entity.User;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author phh
 * @since 2020-07-01
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return
     */
    Page<User> queryPage(Page<User> page, @Param("pm") Map<String, Object> param);

}
