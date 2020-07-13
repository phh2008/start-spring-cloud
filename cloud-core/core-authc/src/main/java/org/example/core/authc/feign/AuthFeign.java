package org.example.core.authc.feign;

import org.example.core.common.dto.CheckAuthorizeDTO;
import org.example.core.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/8
 */
@FeignClient(name = "${start.auth.service-name}")
public interface AuthFeign {


    /**
     * 获取jwt公钥
     *
     * @return
     */
    @RequestMapping(value = "/userPubKey", method = RequestMethod.POST)
    Result<byte[]> getUserPublicKey();

    /**
     * 是否有其中任一角色
     *
     * @param dto 角色
     * @return boolean
     */
    @RequestMapping(value = "/authorize/hasAnyRole", method = RequestMethod.POST)
    Result<Boolean> hasAnyRole(@RequestBody CheckAuthorizeDTO dto);

    /**
     * 是否有其中任一权限
     *
     * @param dto 权限
     * @return boolean
     */
    @RequestMapping(value = "/authorize/hasAnyPermit", method = RequestMethod.POST)
    Result<Boolean> hasAnyPermit(@RequestBody CheckAuthorizeDTO dto);

}
