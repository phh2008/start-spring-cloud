package org.example.core.boot.openfeign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.example.core.boot.config.UserAuthConfig;
import org.example.core.common.context.UserContextHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * feign 拦截器
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/1
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String tokenKey = userAuthConfig.getTokenHeader();
        String token = UserContextHandler.getToken();
        log.info("tokenKey:{}", tokenKey);
        log.info("token:{}", token);
        requestTemplate.header(tokenKey, token);
    }


}
