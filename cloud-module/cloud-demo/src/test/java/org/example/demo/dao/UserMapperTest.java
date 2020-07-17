package org.example.demo.dao;

import org.example.DemoApplication;
import org.example.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/17
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DemoApplication.class})
@TestPropertySource(locations = {"classpath:application.yml"})
public class UserMapperTest {


    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<User> list = userMapper.selectList(null);
        Assertions.assertTrue(list != null && list.size() > 0);
    }

}
