package org.example.core.boot.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 异步线程配置属性
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/23
 */
@Data
@ConfigurationProperties("async")
public class AsyncProperties {

    /**
     * 异步核心线程数，默认：CPU数*2
     */
    private int corePoolSize = Runtime.getRuntime().availableProcessors()*2;
    /**
     * 异步最大线程数，默认：30
     */
    private int maxPoolSize = 30;
    /**
     * 队列容量，默认：15000
     */
    private int queueCapacity = 15000;
    /**
     * 线程存活时间，默认：300
     */
    private int keepAliveSeconds = 300;

}
