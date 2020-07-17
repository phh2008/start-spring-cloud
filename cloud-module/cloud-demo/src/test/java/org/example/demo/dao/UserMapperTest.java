package org.example.demo.dao;

import org.example.DemoApplication;
import org.example.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
@TestPropertySource(locations = {"classpath:application.yml"})
public class UserMapperTest {


    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<User> list = userMapper.selectList(null);
        Assert.assertTrue(list != null && list.size() > 0);
    }

}
