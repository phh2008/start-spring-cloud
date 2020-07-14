package org.example.core.boot.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.example.core.boot.sentinel.IpRequestOriginParser;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * sentinel 配置
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/14
 */
@Configuration
public class SentinelConfiguration {

    @PostConstruct
    public void init() {
        //黑白名单
        WebCallbackManager.setRequestOriginParser(new IpRequestOriginParser());
    }

}
