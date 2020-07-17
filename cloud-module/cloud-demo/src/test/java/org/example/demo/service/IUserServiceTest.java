package org.example.demo.service;

import org.example.DemoApplication;
import org.example.core.tool.utils.StringUtils;
import org.example.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/2
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DemoApplication.class})
@TestPropertySource(locations = {"classpath:application.yml"})
public class IUserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    @Transactional
    @Rollback(true) //不提交事务
    public void testAdd() {
        User user = new User();
        user.setPassword("123456");
        user.setUsername(StringUtils.random(6, StringUtils.RandomType.ALL));
        user.setRealName("临时用户");
        boolean effect = userService.save(user);
        Assertions.assertTrue(effect);
    }

}
