package com.phh.config;

import com.phh.constant.Constants;
import com.phh.mapper.GeneratorMapper;
import com.phh.mapper.SysGeneratorMysqlMapper;
import com.phh.mapper.SysGeneratorOracleMapper;
import com.phh.mapper.SysGeneratorSqlServerMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

@Configuration
public class DbConfig {

    @Value("${project.database}")
    private String database;
    @Resource
    private SysGeneratorMysqlMapper sysGeneratorMysqlMapper;
    @Resource
    private SysGeneratorOracleMapper sysGeneratorOracleMapper;
    @Resource
    private SysGeneratorSqlServerMapper sysGeneratorSqlServerMapper;

    @Bean
    @Primary
    public GeneratorMapper getGeneratorMapper() {
        if (Constants.DB_TYPE_MYSQL.equalsIgnoreCase(database)) {
            return sysGeneratorMysqlMapper;
        } else if (Constants.DB_TYPE_ORACLE.equalsIgnoreCase(database)) {
            return sysGeneratorOracleMapper;
        } else if (Constants.DB_TYPE_SQL_SERVER.equalsIgnoreCase(database)) {
            return sysGeneratorSqlServerMapper;
        } else {
            throw new RuntimeException("不支持当前数据库：" + database);
        }
    }
}
