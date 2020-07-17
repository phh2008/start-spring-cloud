package org.example.demo.service;

import org.example.api.sys.dto.LogDTO;
import org.example.api.sys.feign.LogFeign;
import org.example.core.common.result.Result;
import org.example.demo.dao.UserMapper;
import org.example.demo.entity.User;
import org.example.demo.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

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

    @Before
    public void init() {
        //如果是和Springboot test 整合，可以这样注入参数
        //MockitoAnnotations.initMocks(this);
        //如果有些参数自动注入不了，还可以反射注入
        //ReflectionTestUtils.setField(userService,"logFeign",logFeign);
    }

    @Test
    public void testCreateUser() {
        when(userMapper.insert(Mockito.any())).thenReturn(1);
        when(logFeign.add(Mockito.any())).thenReturn(Result.ok(new LogDTO()));
        User r = userService.createRandomUser();
        Assert.assertTrue(r != null);
    }

}
