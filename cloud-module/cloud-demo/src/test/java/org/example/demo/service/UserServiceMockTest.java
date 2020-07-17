package org.example.demo.service;

import org.example.api.sys.dto.LogDTO;
import org.example.api.sys.feign.LogFeign;
import org.example.core.common.result.Result;
import org.example.demo.dao.UserMapper;
import org.example.demo.entity.User;
import org.example.demo.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/17
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;
    @Mock
    private LogFeign logFeign;

    @Test
    public void testCreateUser() {
        when(userMapper.insert(Mockito.any())).thenReturn(1);
        when(logFeign.add(Mockito.any())).thenReturn(Result.ok(new LogDTO()));
        User r = userService.createRandomUser();
        Assert.assertTrue(r != null);
    }

}
