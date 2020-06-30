package org.example.core.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/29
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Authorize {

    /**
     * 表达式：
     * permitAll() 可通过，只要用户登录
     * denyAll() 不可通过，除非是超级管理员 admin
     * hasRole('xx') 是否有此角色
     * hasAnyRole('xx') 是否有其中任一角色
     * hasPermit('xx') 是否有此权限
     * hasAnyPermit('xx') 是否有其中任一权限
     *
     * @return
     */
    String value();

}
