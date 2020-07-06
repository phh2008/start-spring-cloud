package org.example.demo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.core.boot.handler.BaseController;
import org.example.core.common.annotation.WithoutAuthentication;
import org.example.core.common.result.Result;
import org.example.core.tool.utils.StringUtils;
import org.example.demo.entity.User;
import org.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author phh
 * @since 2020-07-01
 */
@Api(value = "用户管理", tags = "用户管理")
@WithoutAuthentication
@RestController
@RequestMapping("/demo/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户例表", httpMethod = "GET", response = Result.class)
    @GetMapping("/list")
    public Result<List<User>> list() {
        List<User> list = userService.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "添加随机用户", httpMethod = "GET", response = Result.class)
    @GetMapping("/addRandom")
    public Result<User> addRandom() {
        User user = new User();
        user.setPassword("123456");
        user.setUsername(StringUtils.random(6, StringUtils.RandomType.ALL));
        user.setRealName("临时用户");
        userService.save(user);
        return Result.ok(user);
    }


    @ApiOperation(value = "添加随机用户2", httpMethod = "GET", response = Result.class)
    @GetMapping("/addRandom2")
    public Result<User> addRandom2() {
        User user = userService.createRandomUser();
        return Result.ok(user);
    }

}
