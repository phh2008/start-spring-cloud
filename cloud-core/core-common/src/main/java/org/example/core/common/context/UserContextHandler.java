package org.example.core.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.example.core.common.constant.CommonConst;

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

    public static String getUserId() {
        Object value = get(CommonConst.CONTEXT_KEY_USER_ID);
        return returnObjectValue(value);
    }

    public static String getUserName() {
        Object value = get(CommonConst.CONTEXT_KEY_USER_NAME);
        return returnObjectValue(value);
    }

    public static String getRealName() {
        Object value = get(CommonConst.CONTEXT_KEY_REAL_NAME);
        return returnObjectValue(value);
    }

    public static String getToken() {
        Object value = get(CommonConst.CONTEXT_KEY_USER_TOKEN);
        return Objects.toString(value, "");
    }

    public static void setToken(String token) {
        set(CommonConst.CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setUserId(String userId) {
        set(CommonConst.CONTEXT_KEY_USER_ID, userId);
    }

    public static void setUserName(String userName) {
        set(CommonConst.CONTEXT_KEY_USER_NAME, userName);
    }

    public static void setRealName(String realName) {
        set(CommonConst.CONTEXT_KEY_REAL_NAME, realName);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void removeToken() {
        Map<String, Object> map = threadLocal.get();
        if (map != null) {
            map.remove(CommonConst.CONTEXT_KEY_USER_TOKEN);
        }
    }

    public static void remove() {
        threadLocal.remove();
    }

}
