package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 网关入口
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/27
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
@RestController
@SpringBootApplication
@RefreshScope
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        log.info("网关中心启动成功！");
    }

    @Value("${server.port:}")
    private String serverPort;

    @RequestMapping("/get")
    public String get() {
        return serverPort;
    }

}
