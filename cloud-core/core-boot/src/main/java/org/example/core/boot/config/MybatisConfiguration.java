package org.example.core.boot.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.example.core.common.mybatis.plugins.SqlLogInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 配置
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/23
 */
@Configuration
@MapperScan({"org.example.**.mapper.**", "org.example.**.dao.**"})
public class MybatisConfiguration {

    @Bean
    @ConditionalOnMissingBean(PaginationInterceptor.class)
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * sql 日志
     *
     * @return SqlLogInterceptor
     */
    @Bean
    @ConditionalOnProperty(value = "start.mybatis.sql.log", matchIfMissing = true)
    public SqlLogInterceptor sqlLogInterceptor() {
        return new SqlLogInterceptor();
    }

}
