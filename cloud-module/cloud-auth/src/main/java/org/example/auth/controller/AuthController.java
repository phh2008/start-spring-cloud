package org.example.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.api.sys.feign.AuthenticateFeign;
import org.example.api.sys.vo.UserInfoVO;
import org.example.core.boot.handler.BaseController;
import org.example.core.common.annotation.Log;
import org.example.core.common.annotation.WithoutAuthentication;
import org.example.core.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/30
 */
@RequestMapping("/auth")
@Api(value = "AuthController", tags = {"登录认证"})
@RestController
public class AuthController extends BaseController {

    @Autowired
    private AuthenticateFeign authenticateFeign;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return result
     */
    @Log("测试接口:用户登录")
    @ApiOperation(value = "用户登录(测试)", httpMethod = "POST", response = Result.class)
    @WithoutAuthentication
    @PostMapping("/login")
    public Result<UserInfoVO> login(@RequestParam String username, @RequestParam String password) {
        return authenticateFeign.login(username, password);
    }


}
