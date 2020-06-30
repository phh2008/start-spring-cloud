package org.example.core.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.example.core.common.constant.CommonConst;
import org.example.core.common.exception.CloudException;
import org.example.core.common.jwt.IJwtInfo;
import org.example.core.common.result.ResultCodeEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户上下文信息
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/27
 */
public class UserContextHandler {

    public static TransmittableThreadLocal<Map<String, Object>> threadLocal = new TransmittableThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }


    public static IJwtInfo getJwtInfo() {
        return (IJwtInfo) get(CommonConst.CONTEXT_KEY_JWT_INFO);
    }

    public static IJwtInfo getJwtInfoThrow() {
        IJwtInfo jwtInfo = (IJwtInfo) get(CommonConst.CONTEXT_KEY_JWT_INFO);
        if (jwtInfo == null) {
            throw new CloudException(ResultCodeEnum.UNAUTHENTICATED);
        }
        return jwtInfo;
    }

    public static void setJwtInfo(IJwtInfo jwtInfo) {
        set(CommonConst.CONTEXT_KEY_JWT_INFO, jwtInfo);
    }

    public static String getToken() {
        Object value = get(CommonConst.CONTEXT_KEY_USER_TOKEN);
        return Objects.toString(value, "");
    }

    public static void setToken(String token) {
        set(CommonConst.CONTEXT_KEY_USER_TOKEN, token);
    }


    public static void removeByKey(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map != null) {
            map.remove(key);
        }
    }

    public static void remove() {
        threadLocal.remove();
    }

}
