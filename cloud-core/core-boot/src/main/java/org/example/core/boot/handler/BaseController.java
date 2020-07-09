package org.example.core.boot.handler;

import org.example.core.common.context.UserContextHandler;
import org.example.core.common.jwt.IJwtInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 控制器基类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取登录用户信息
     *
     * @return null if not login
     */
    public IJwtInfo getSubject() {
        return UserContextHandler.getJwtInfo();
    }

    /**
     * 获取登录用户信息
     *
     * @return CloudException if not login
     */
    public IJwtInfo getSubjectThrow() {
        return UserContextHandler.getJwtInfoThrow();
    }

}
