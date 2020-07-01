package org.example.api.sys.feign;

import org.example.api.sys.vo.UserInfoVO;
import org.example.core.common.result.Result;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/1
 */
@Component
public class AuthenticateFeignFallback implements AuthenticateFeign {

    @Override
    public Result<UserInfoVO> login(String username, String password) {
        return Result.fail("登录失败，触发熔断降级");
    }

}
