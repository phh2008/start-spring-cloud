package org.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.seata.spring.annotation.GlobalTransactional;
import org.example.api.sys.dto.LogDTO;
import org.example.api.sys.feign.LogFeign;
import org.example.core.common.mp.base.BaseServiceImpl;
import org.example.core.common.result.Result;
import org.example.core.tool.utils.StringUtils;
import org.example.demo.dao.UserMapper;
import org.example.demo.entity.User;
import org.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author phh
 * @since 2020-07-01
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private LogFeign logFeign;

    /**
     * 测试 分布式事务
     * 测试 创建随机用户
     *
     * @return
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public User createRandomUser() {
        User user = new User();
        user.setPassword("123456");
        user.setUsername(StringUtils.random(6, StringUtils.RandomType.ALL));
        user.setRealName("临时用户");
        this.baseMapper.insert(user);

        //调用远程服务
        LogDTO logDto = new LogDTO();
        logDto.setCostTime(100L);
        logDto.setIp("localhost");
        logDto.setMethod("test");
        logDto.setLogContent("测试分布式事务");
        Result<LogDTO> result = logFeign.add(logDto);
        this.logger.info("invoke log feign , result {}, info {}", result.isSuccess(), result.getData());
        //模拟出错，测试是否回滚事务
        int a = 10 / 0;
        return user;
    }

    @Override
    public Page<User> queryPage(Page<User> page, Map<String, Object> param) {
        return this.baseMapper.queryPage(page,param);
    }


}
