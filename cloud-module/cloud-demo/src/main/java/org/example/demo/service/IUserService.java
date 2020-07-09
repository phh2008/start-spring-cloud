package org.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.core.common.mp.base.BaseService;
import org.example.demo.entity.User;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author phh
 * @since 2020-07-01
 */
public interface IUserService extends BaseService<User> {

    /**
     * 测试 创建随机用户
     *
     * @param
     * @return
     * @throws
     * @author phh
     * @date 2020/7/6
     * @version V1.0
     */
    User createRandomUser();


    /**
     * 分页
     *
     * @param page
     * @param param
     * @return
     */
    Page<User> queryPage(Page<User> page, Map<String, Object> param);

}
