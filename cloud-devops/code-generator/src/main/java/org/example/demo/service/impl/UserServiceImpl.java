package org.example.demo.service.impl;

import org.example.demo.entity.User;
import org.example.demo.mapper.UserMapper;
import org.example.demo.service.IUserService;
import org.example.core.common.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phh
 * @since 2020-07-01
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

}
