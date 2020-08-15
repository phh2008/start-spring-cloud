package com.phh.config;

/**
 * 描述
 *
 * @author huihui.peng
 * @version V1.0
 * @date 2020/8/15
 */
public class DataSourceContextHelper {

    private static final ThreadLocal<DataSourceKey> contextHolder = ThreadLocal.withInitial(() -> DataSourceKey.MYSQL);

    public static DataSourceKey get() {
        return contextHolder.get();
    }

    public static void set(DataSourceKey key) {
        contextHolder.set(key);
    }

    public static void clear() {
        contextHolder.remove();
    }

}
