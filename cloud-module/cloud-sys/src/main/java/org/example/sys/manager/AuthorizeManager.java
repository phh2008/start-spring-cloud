package org.example.sys.manager;

import org.example.core.tool.utils.RedisUtils;
import org.example.support.AuthorizeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 权限校验管理类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/30
 */
@Service
public class AuthorizeManager {

    /**
     * 权限缓存 key
     */
    public static final String AUTHORIZE_CACHE_KEY = "authorize:info";

    @Autowired
    private RedisUtils redisUtils;

    public boolean hasAnyRoles(Long userId, List<String> roles) {
        if (userId == null || roles == null || roles.isEmpty()) {
            return false;
        }
        AuthorizeInfo authorizeInfo = this.getAuthorizeInfo(userId);
        if (authorizeInfo == null) {
            return false;
        }
        return authorizeInfo.getRoles().stream()
                .anyMatch(roles::contains);
    }

    public boolean hasAnyPermits(Long userId, List<String> permits) {
        if (userId == null || permits == null || permits.isEmpty()) {
            return false;
        }
        AuthorizeInfo authorizeInfo = this.getAuthorizeInfo(userId);
        if (authorizeInfo == null) {
            return false;
        }
        return authorizeInfo.getPermits().stream()
                .anyMatch(permits::contains);
    }


    private AuthorizeInfo doGetAuthorizeInfo(Long userId) {
        //TODO 从DB中获取用户角色与权限集合
        AuthorizeInfo authorizeInfo = new AuthorizeInfo();
        authorizeInfo.addRoles(Arrays.asList("admin", "sales"));
        authorizeInfo.addPermits(Arrays.asList("sys:user:view", "sys:user:add", "sys:user:del"));
        return authorizeInfo;
    }

    private void setAuthorizeInfoCache(Long userId, AuthorizeInfo authorizeInfo) {
        this.redisUtils.hset(AUTHORIZE_CACHE_KEY, String.valueOf(userId), authorizeInfo);
    }

    /**
     * 获取用户权限
     *
     * @param userId 用户ID
     * @return AuthorizeInfo
     */
    public AuthorizeInfo getAuthorizeInfo(Long userId) {
        AuthorizeInfo authorizeInfo = (AuthorizeInfo) redisUtils.hget(AUTHORIZE_CACHE_KEY, String.valueOf(userId));
        if (authorizeInfo == null) {
            authorizeInfo = doGetAuthorizeInfo(userId);
            setAuthorizeInfoCache(userId, authorizeInfo);
        }
        return authorizeInfo;
    }

    /**
     * 移除指定用户权限缓存
     *
     * @param userId 用户ID
     */
    public void removeAuthorizeInfoCache(Long userId) {
        redisUtils.hdel(AUTHORIZE_CACHE_KEY, String.valueOf(userId));
    }

    /**
     * 清空所有用户权限缓存
     */
    public void clearAuthorizeInfoCache() {
        redisUtils.del(AUTHORIZE_CACHE_KEY);
    }


}
