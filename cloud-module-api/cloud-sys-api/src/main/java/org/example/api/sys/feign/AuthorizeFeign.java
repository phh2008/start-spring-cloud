package org.example.api.sys.feign;

import org.example.api.sys.dto.CheckAuthorizeDTO;
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
 * @date 2020/6/29
 */
@FeignClient(name = "cloud-sys")
public interface AuthorizeFeign {

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
