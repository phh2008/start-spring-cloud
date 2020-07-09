package org.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.example.core.boot.support.LogAspectHandler;
import org.example.core.tool.utils.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/9
 */
@Slf4j
@Configuration
public class BeanConfig {


    @Bean
    public LogAspectHandler logAspectHandler() {
        return (logInfo) -> {
            log.info("demo log aspect handler {}", JsonUtils.writeAsString(logInfo));
        };
    }


}
