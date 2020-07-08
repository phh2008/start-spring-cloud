package org.example.core.authc.runner;

import lombok.extern.slf4j.Slf4j;
import org.example.core.authc.config.UserAuthConfig;
import org.example.core.authc.feign.AuthFeign;
import org.example.core.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 获取Jwt使用的公钥
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
@Slf4j
@Configuration
public class AuthClientRunner implements CommandLineRunner {

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Autowired
    private AuthFeign authFeign;

    @Override
    public void run(String... args) {
        log.debug("初始化加载用户pubKey");
        try {
            loadUserPubKey();
        } catch (Exception e) {
            log.error("初始化加载用户pubKey失败,1分钟后自动重试!", e);
        }
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserPubKey() {
        log.debug("重新加载用户pubKey");
        loadUserPubKey();
    }

    private void loadUserPubKey() {
        Result<byte[]> resp = authFeign.getUserPublicKey();
        if (resp.isSuccess()) {
            this.userAuthConfig.setPubKeyByte(resp.getData());
        }
    }

}