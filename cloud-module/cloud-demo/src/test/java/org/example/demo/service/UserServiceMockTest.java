package org.example.demo.service;

import org.example.api.sys.dto.LogDTO;
import org.example.api.sys.feign.LogFeign;
import org.example.core.common.result.Result;
import org.example.demo.dao.UserMapper;
import org.example.demo.entity.User;
import org.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/17
 */
@ExtendWith(SpringExtension.class)
public class UserServiceMockTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;
    @Mock
    private LogFeign logFeign;

    @BeforeEach
    public void init() {
        //如果是和Springboot test 整合，可以这样注入参数
        //MockitoAnnotations.initMocks(this);
        //如果参数自动注入不了，还可以反射注入
        //ReflectionTestUtils.setField(userService,"logFeign",logFeign);
    }

    @Test
    public void testCreateUser() {
        when(userMapper.insert(Mockito.any())).thenReturn(1);
        when(logFeign.add(Mockito.any())).thenReturn(Result.ok(new LogDTO()));
        User r = userService.createRandomUser();
        Assertions.assertTrue(r != null);
    }

}
