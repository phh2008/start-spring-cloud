package org.example.sys.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * jwt 配置
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
@Data
@Configuration
public class JwtConfig {
    @Value("${jwt.rsa-secret}")
    private String userSecret;
    @Value("${jwt.expire-second:2592000}")
    private Integer expireSecond;

    private byte[] userPubKey;
    private byte[] userPriKey;
}
