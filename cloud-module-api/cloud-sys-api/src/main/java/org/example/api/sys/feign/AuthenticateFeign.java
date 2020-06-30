package org.example.api.sys.feign;

import org.example.api.sys.vo.UserInfoVO;
import org.example.core.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 认证
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/30
 */
@FeignClient(name = "cloud-sys")
public interface AuthenticateFeign {


    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return Result<UserInfoVO>
     */
    @RequestMapping(value = "/authenticate/login", method = RequestMethod.POST)
    Result<UserInfoVO> login(@RequestParam("username") String username, @RequestParam("password") String password);


}
