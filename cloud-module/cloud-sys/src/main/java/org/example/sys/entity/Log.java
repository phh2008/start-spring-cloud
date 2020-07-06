package org.example.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.core.common.mp.base.BaseColumnEntity;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author phh
 * @since 2020-07-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_log")
public class Log extends BaseColumnEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 日志类型（1登录日志，2操作日志）
     */
    private Boolean logType;

    /**
     * 日志内容
     */
    private String logContent;

    /**
     * 操作类型
     */
    private Boolean operateType;

    /**
     * 操作用户账号
     */
    private Long userId;

    /**
     * 操作用户名称
     */
    private String username;

    /**
     * IP
     */
    private String ip;

    /**
     * 请求java方法
     */
    private String method;

    /**
     * 请求路径
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 耗时
     */
    private Long costTime;

}
