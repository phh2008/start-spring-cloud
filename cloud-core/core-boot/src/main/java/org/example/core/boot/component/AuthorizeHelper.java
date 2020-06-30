package org.example.core.boot.component;

import lombok.extern.slf4j.Slf4j;
import org.example.api.sys.dto.CheckAuthorizeDTO;
import org.example.api.sys.feign.AuthorizeFeign;
import org.example.core.common.constant.RoleConst;
import org.example.core.common.context.UserContextHandler;
import org.example.core.common.exception.CloudException;
import org.example.core.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 签权类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/29
 */
@Slf4j
@Component
public class AuthorizeHelper {

    @Autowired
    private AuthorizeFeign authorizeFeign;

    /**
     * 可通过，只要用户登录
     *
     * @return boolean
     */
    public boolean permitAll() {
        return true;
    }

    /**
     * 不可通过，除非是超级管理员
     *
     * @return boolean
     */
    public boolean denyAll() {
        return hasRole(RoleConst.ADMIN);
    }

    /**
     * 是否有此角色
     *
     * @param role 单角色
     * @return boolean
     */
    public boolean hasRole(String role) {
        return hasAnyRole(role);
    }

    /**
     * 是否有其中任一角色
     *
     * @param roles 角色
     * @return boolean
     */
    public boolean hasAnyRole(String... roles) {
        String userId = UserContextHandler.getJwtInfoThrow().getId();
        CheckAuthorizeDTO dto = new CheckAuthorizeDTO();
        dto.setUserId(Long.valueOf(userId));
        dto.setRoles(Arrays.asList(roles));
        Result<Boolean> result = authorizeFeign.hasAnyRole(dto);
        if (!result.isSuccess()) {
            throw new CloudException(result.getCode(), result.getMsg());
        }
        return result.getData() != null && result.getData();
    }

    /**
     * 是否有此权限
     *
     * @param permit 权限
     * @return boolean
     */
    public boolean hasPermit(String permit) {
        return hasAnyPermit(permit);
    }

    /**
     * 是否有其中任一权限
     *
     * @param permits 权限
     * @return boolean
     * @author phh
     * @date 2020/6/29
     */
    public boolean hasAnyPermit(String... permits) {
        String userId = UserContextHandler.getJwtInfoThrow().getId();
        CheckAuthorizeDTO dto = new CheckAuthorizeDTO();
        dto.setUserId(Long.valueOf(userId));
        dto.setPermits(Arrays.asList(permits));
        Result<Boolean> result = authorizeFeign.hasAnyPermit(dto);
        if (!result.isSuccess()) {
            throw new CloudException(result.getCode(), result.getMsg());
        }
        return result.getData() != null && result.getData();
    }


}
