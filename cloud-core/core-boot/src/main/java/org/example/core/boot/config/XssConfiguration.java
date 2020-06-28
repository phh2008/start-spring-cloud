package org.example.core.boot.config;

import org.example.core.tool.xss.XssFilter;
import org.example.core.tool.xss.XssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
@Configuration
@ConditionalOnProperty(value = "start.boot.xss.enable", havingValue = "true")
@EnableConfigurationProperties({XssProperties.class})
public class XssConfiguration {

    @Autowired
    private XssProperties xssProperties;

    /**
     * 防XSS注入
     */
    @Bean
    public FilterRegistrationBean<XssFilter> xssFilterRegistration() {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new XssFilter(xssProperties));
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<>(1);
        // excludes配置默认不需过滤URL
        initParameters.put(XssFilter.KEY_EXCLUDES, "/favicon.ico,/img/*,/js/*,/css/*");
        registration.setInitParameters(initParameters);
        return registration;
    }

}
