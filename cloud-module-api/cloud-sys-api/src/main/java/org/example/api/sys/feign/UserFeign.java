package org.example.api.sys.feign;

import org.example.api.sys.vo.UserInfoVO;
import org.example.core.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/1
 */
@FeignClient(name = "cloud-sys")
public interface UserFeign {

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/user/getUserInfo")
    Result<UserInfoVO> getUserInfo();

}
