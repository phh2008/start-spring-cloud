package org.example.core.boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.core.tool.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.ResourceRegionHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/**
 * web mvc config
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/23
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class CloudWebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(x -> x instanceof StringHttpMessageConverter || x instanceof AbstractJackson2HttpMessageConverter);
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new ResourceRegionHttpMessageConverter());
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(objectMapper);
        converters.add(messageConverter);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // swagger 的静态资源映射
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // hystrix 的静态资源映射
        registry.addResourceHandler("/hystrix/**")
                .addResourceLocations("classpath:/static/hystrix/");
    }

    /**
     * 参数校验器
     * 这里重新设置 MessageSource
     * 默认的国际化配置文件必须以ValidationMessages开头，
     * 比如ValidationMessages.properties 或者 ValidationMessages_en.properties
     * 使得我们自己可定义随意的校验消息文件
     *
     * @return
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new NativeLocaleResolver();
    }

    public static class NativeLocaleResolver implements LocaleResolver {
        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String language = request.getParameter("language");
            Locale locale = Locale.getDefault();
            if (!StringUtils.isEmpty(language)) {
                String[] split = language.split("_");
                locale = new Locale(split[0], split[1]);
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        }
    }

}
