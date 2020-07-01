package org.example.core.boot.config;

import feign.RequestInterceptor;
import org.example.core.boot.openfeign.FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * open feign 配置
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/1
 */
@Configuration
public class OpenFeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }

}
