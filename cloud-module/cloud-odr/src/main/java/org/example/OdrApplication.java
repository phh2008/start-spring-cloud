package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 认证服务
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication
public class OdrApplication {


    public static void main(String[] args) {
        SpringApplication.run(OdrApplication.class, args);
        log.info("ODR服务启动成功！");
    }

}
