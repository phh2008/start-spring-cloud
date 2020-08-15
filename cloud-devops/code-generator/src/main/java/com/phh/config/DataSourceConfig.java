package com.phh.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 描述
 *
 * @author huihui.peng
 * @version V1.0
 * @date 2020/8/15
 */
public class DataSourceConfig {

    @Bean("dataSourceMysql")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource dataSourceMysql() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dataSourceOracle")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource dataSourceOracle() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dataSourceSqlServer")
    @ConfigurationProperties(prefix = "spring.datasource.sqlServer")
    public DataSource dataSourceSqlServer() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源
     *
     * @return
     */
    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource(DataSource dataSourceMysql,
                                 DataSource dataSourceOracle,
                                 DataSource dataSourceSqlServer) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMysql);
        Map<Object, Object> multiDs = new LinkedHashMap<>(2);
        multiDs.put(DataSourceKey.MYSQL, dataSourceMysql);
        multiDs.put(DataSourceKey.ORACLE, dataSourceOracle);


        //TODO
        dynamicDataSource.setTargetDataSources(multiDs);
        return dynamicDataSource;
    }

    //@Bean(name = "sqlSessionFactory")
    //public SqlSessionFactoryBean sqlSessionFactory(DataSource dynamicDataSource) throws IOException {
    //    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    //    sqlSessionFactoryBean.setDataSource(dynamicDataSource);
    //    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    //    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
    //    sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("config/mybatis-config.xml"));
    //    return sqlSessionFactoryBean;
    //}
    //
    //@Bean
    //public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    //    return new SqlSessionTemplate(sqlSessionFactory);
    //}
    //
    //@Bean
    //public PlatformTransactionManager transactionManager(DataSource dynamicDataSource) {
    //    return new DataSourceTransactionManager(dynamicDataSource);
    //}

}
