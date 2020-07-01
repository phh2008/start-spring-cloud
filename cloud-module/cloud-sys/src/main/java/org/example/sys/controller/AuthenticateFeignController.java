package org.example.sys.controller;

import io.swagger.annotations.Api;
import org.example.api.sys.feign.AuthenticateFeign;
import org.example.api.sys.vo.UserInfoVO;
import org.example.core.boot.handler.BaseController;
import org.example.core.common.annotation.WithoutAuthentication;
import org.example.core.common.jwt.JwtHelper;
import org.example.core.common.jwt.JwtInfo;
import org.example.core.common.result.Result;
import org.example.core.tool.utils.RedisUtils;
import org.example.sys.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 登录认证
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/30
 */
@WithoutAuthentication
@Api(value = "Authenticate", tags = {"Authenticate 登录认证"})
@RestController
public class AuthenticateFeignController extends BaseController implements AuthenticateFeign {

    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private RedisUtils redisUtils;


    @Override
    @RequestMapping(value = "/authenticate/login", method = RequestMethod.POST)
    public Result<UserInfoVO> login(@RequestParam String username, @RequestParam String password) {
        //TODO 校验用户名密码
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(1000L);
        userInfoVO.setRealName("小李");
        userInfoVO.setUsername("xiaoli");
        //创建token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(String.valueOf(userInfoVO.getUserId()));
        jwtInfo.setRealName(userInfoVO.getRealName());
        jwtInfo.setUserName(userInfoVO.getUsername());
        String token = JwtHelper.createToken(jwtInfo, jwtConfig.getUserPriKey(), jwtConfig.getExpireSecond());
        //返回token
        userInfoVO.setToken(token);
        System.out.println("========================222222=================================");
        return Result.ok(userInfoVO);
    }


}
