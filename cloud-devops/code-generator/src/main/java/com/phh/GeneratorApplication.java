package com.phh;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 示例
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
@Slf4j
@MapperScan("com.phh")
@SpringBootApplication(scanBasePackages = {"org.example", "com.phh"})
public class GeneratorApplication {


    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
        log.info("code-generator 服务启动成功！");
    }

}
