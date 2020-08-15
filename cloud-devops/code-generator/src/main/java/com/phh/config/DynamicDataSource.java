package com.phh.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 描述
 *
 * @author huihui.peng
 * @version V1.0
 * @date 2020/8/15
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHelper.get();
    }
}
