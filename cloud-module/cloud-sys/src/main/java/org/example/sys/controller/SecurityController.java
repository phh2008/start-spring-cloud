package org.example.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.sys.config.JwtConfig;
import org.example.core.boot.handler.BaseController;
import org.example.core.common.annotation.Log;
import org.example.core.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 安全相关
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
@Api(value = "Security", tags = {"Security 配置"})
@RestController
@RequestMapping("/security")
public class SecurityController extends BaseController {

    @Autowired
    private JwtConfig jwtConfig;

    @Log("获取公钥")
    @ApiOperation(value = "获取公钥", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "测试变量", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/userPubKey", method = RequestMethod.POST)
    public Result<byte[]> getUserPublicKey() {
        return Result.ok(jwtConfig.getUserPubKey());
    }

}
