package org.example.sys.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.core.boot.handler.BaseController;
import org.example.core.common.annotation.Log;
import org.example.core.common.annotation.WithoutAuthentication;
import org.example.core.common.dto.CheckAuthorizeDTO;
import org.example.core.common.result.Result;
import org.example.sys.manager.AuthorizeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限校验
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/30
 */
@WithoutAuthentication
@Api(value = "Authorize", tags = {"Authorize 权限校验"})
@RestController
public class AuthorizeFeignController extends BaseController {

    @Autowired
    private AuthorizeManager authorizeManager;

    @SentinelResource(value = "hasAnyRole", fallback = "fallback", blockHandler = "blockHandler")
    @Log("校验是否有其中任一角色")
    @RequestMapping(value = "/authorize/hasAnyRole", method = RequestMethod.POST)
    @ApiOperation(value = "校验是否有其中任一角色", httpMethod = "POST", response = Result.class)
    public Result<Boolean> hasAnyRole(@RequestBody CheckAuthorizeDTO dto) {
        boolean hasRole = authorizeManager.hasAnyRoles(dto.getUserId(), dto.getRoles());
        return Result.ok(hasRole);
    }

    @Log("校验是否有其中任一权限")
    @RequestMapping(value = "/authorize/hasAnyPermit", method = RequestMethod.POST)
    @ApiOperation(value = "校验是否有其中任一权限", httpMethod = "POST", response = Result.class)
    public Result<Boolean> hasAnyPermit(@RequestBody CheckAuthorizeDTO dto) {
        boolean hasPermit = authorizeManager.hasAnyPermits(dto.getUserId(), dto.getPermits());
        return Result.ok(hasPermit);
    }

}
