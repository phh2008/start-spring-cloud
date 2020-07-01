package org.example.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.api.sys.vo.UserInfoVO;
import org.example.core.boot.handler.BaseController;
import org.example.core.common.annotation.Authorize;
import org.example.core.common.annotation.Log;
import org.example.core.common.context.UserContextHandler;
import org.example.core.common.jwt.IJwtInfo;
import org.example.core.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/30
 */
@Api(value = "User", tags = {"用户管理"})
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 测试接口
     *
     * @return
     */
    @Log("测试接口:用户add")
    @ApiOperation(value = "添加用户(测试)", httpMethod = "GET", response = Result.class)
    @Authorize("hasPermit('sys:user:add')")
    @GetMapping("/add")
    public Result add() {

        return Result.ok();
    }

    /**
     * 测试接口
     *
     * @return
     */
    @Log("测试接口:用户edit")
    @ApiOperation(value = "编辑用户(测试)", httpMethod = "GET", response = Result.class)
    @Authorize("hasAnyPermit('sys:user:edit','sys:user:add')")
    @GetMapping("/edit")
    public Result edit() {

        return Result.ok();
    }

    @Log("获取用户信息")
    @ApiOperation(value = "获取用户信息", httpMethod = "GET", response = Result.class)
    @GetMapping("/getUserInfo")
    public Result<UserInfoVO> getUserInfo() {
        IJwtInfo jwtInfo = UserContextHandler.getJwtInfoThrow();
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUsername(jwtInfo.getUserName());
        userInfoVO.setUserId(Long.parseLong(jwtInfo.getId()));
        userInfoVO.setRealName(jwtInfo.getRealName());
        return Result.ok(userInfoVO);
    }

}
