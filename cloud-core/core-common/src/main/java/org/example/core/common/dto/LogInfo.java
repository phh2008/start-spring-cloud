package org.example.core.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志信息
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/8
 */
@Data
@ToString
public class LogInfo implements Serializable {

    private static final long serialVersionUID = -4389994304604225050L;

    /**
     * 请求的方法
     */
    private String method;
    /**
     * 请求的参数
     */
    private String args;
    /**
     * 日志描述
     */
    private String description;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求IP地址
     */
    private String ip;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 系统
     */
    private String os;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 请求时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestTime;

    /**
     * 异常
     */
    private Throwable exception;

}
