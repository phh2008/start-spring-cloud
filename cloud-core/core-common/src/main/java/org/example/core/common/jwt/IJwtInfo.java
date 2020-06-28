package org.example.core.common.jwt;

import java.util.Date;

/**
 * jwt 信息
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
public interface IJwtInfo {

    /**
     * 获取用户ID
     *
     * @return
     */
    String getId();

    /**
     * 获取用户名
     *
     * @return
     */
    String getUserName();

    /**
     * 获取姓名
     *
     * @return
     */
    String getRealName();

    /**
     * 获取过期时间
     *
     * @return
     */
    Date getExpireTime();
}
