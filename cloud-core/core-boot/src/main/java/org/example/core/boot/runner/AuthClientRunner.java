package org.example.core.boot.runner;

import lombok.extern.slf4j.Slf4j;
import org.example.api.sys.feign.SecurityFeign;
import org.example.core.boot.config.UserAuthConfig;
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
    private SecurityFeign securityFeign;

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
        Result<byte[]> resp = securityFeign.getUserPublicKey();
        if (resp.isSuccess()) {
            this.userAuthConfig.setPubKeyByte(resp.getData());
        }
    }

}