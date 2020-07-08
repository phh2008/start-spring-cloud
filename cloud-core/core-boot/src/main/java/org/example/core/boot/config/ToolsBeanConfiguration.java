package org.example.core.boot.config;

import org.example.core.tool.utils.SpringContextUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 其它工具类的配置
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/23
 */
@Configuration
public class ToolsBeanConfiguration {


    @Bean
    public SpringContextUtils springContextUtils() {
        return new SpringContextUtils();
    }


}
