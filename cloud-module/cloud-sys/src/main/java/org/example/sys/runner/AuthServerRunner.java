package org.example.sys.runner;

import org.example.core.common.jwt.RsaKeyHelper;
import org.example.core.tool.utils.RedisUtils;
import org.example.sys.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Map;

/**
 * 系统启动时生成密钥对
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
@Order(value = 100)
@Configuration
public class AuthServerRunner implements CommandLineRunner {

    private static final String REDIS_USER_PRI_KEY = "CLOUD:SYS:AUTH:JWT:PRI";
    private static final String REDIS_USER_PUB_KEY = "CLOUD:SYS:AUTH:JWT:PUB";

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public void run(String... args) throws Exception {
        if (redisUtils.hasKey(REDIS_USER_PRI_KEY) && redisUtils.hasKey(REDIS_USER_PUB_KEY)) {
            jwtConfig.setUserPriKey(RsaKeyHelper.toBytes(redisUtils.get(REDIS_USER_PRI_KEY).toString()));
            jwtConfig.setUserPubKey(RsaKeyHelper.toBytes(redisUtils.get(REDIS_USER_PUB_KEY).toString()));
        } else {
            Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(jwtConfig.getUserSecret());
            jwtConfig.setUserPriKey(keyMap.get("pri"));
            jwtConfig.setUserPubKey(keyMap.get("pub"));
            redisUtils.set(REDIS_USER_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
            redisUtils.set(REDIS_USER_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));
        }
    }

}
