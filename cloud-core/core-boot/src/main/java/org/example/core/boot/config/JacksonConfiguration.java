package org.example.core.boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.core.tool.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/23
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfiguration {

    @Value("${spring.jackson.date-format}")
    private String dateFormat;

    @Value("${spring.jackson.time-zone}")
    private String timeZone;

    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        return new JsonUtils.JacksonObjectMapper(dateFormat,timeZone).getJsonObjectMapper();
    }

}
