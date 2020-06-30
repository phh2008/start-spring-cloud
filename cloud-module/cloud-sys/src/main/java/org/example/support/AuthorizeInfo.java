package org.example.support;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 授权信息类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/30
 */
public class AuthorizeInfo implements Serializable {

    private static final long serialVersionUID = 7553355285046585971L;

    /**
     * 权限集合
     */
    private final Set<String> permits = new HashSet<>();

    /**
     * 角色集合
     */
    private final Set<String> roles = new HashSet<>();


    public Set<String> getPermits() {
        return permits;
    }

    public Set<String> getRoles() {
        return roles;
    }


    public void addPermits(List<String> permits) {
        this.permits.addAll(permits);
    }

    public void addRoles(List<String> roles) {
        this.roles.addAll(roles);
    }


}
