package org.example.demo.service;

import org.example.core.common.mp.base.BaseService;
import org.example.demo.entity.User;

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

}
