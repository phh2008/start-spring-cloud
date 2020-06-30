package org.example.api.sys.feign;

import org.example.core.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 安全操作
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
@FeignClient(name = "cloud-sys")
public interface SecurityFeign {

    String API_PREFIX = "/security";

    /**
     * 获取用户公钥
     *
     * @return
     */
    @RequestMapping(value = API_PREFIX + "/userPubKey", method = RequestMethod.POST)
    Result<byte[]> getUserPublicKey();


}
