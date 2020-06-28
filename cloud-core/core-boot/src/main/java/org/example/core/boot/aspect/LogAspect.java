package org.example.core.boot.aspect;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentParser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.core.common.annotation.Log;
import org.example.core.tool.utils.JsonUtils;
import org.example.core.tool.utils.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/27
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(public * org.example.*.controller..*.*(..))@annotation(org.example.core.common.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("logPointCut()")
    public void before(JoinPoint joinPoint) {
        // 保存日志
        saveRequestLog(joinPoint);
    }

    private void saveRequestLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log action = method.getAnnotation(Log.class);
        if (action == null) {
            return;
        }
        //方法名称
        String methodName = method.getName();
        //请求参数
        String arguments = "";
        Object[] args = joinPoint.getArgs();
        try {
            arguments = JsonUtils.writeAsString(args);
        } catch (Exception e) {
            log.error("parse json error", e);
        }
        //日志描述
        String description = action.value();
        // 获取request
        HttpServletRequest request = WebUtils.getRequest();
        //请求URL
        String url = request.getRequestURL().toString();
        // 设置IP地址
        String ipAddr = ServletUtil.getClientIP(request);
        // 设置浏览器和设备系统
        String userAgentHeader = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgentParser.parse(userAgentHeader);
        Browser browser = userAgent.getBrowser();
        //浏览器名称与版本
        String browserName = browser.getName() + ":" + browser.getVersion(userAgentHeader);
        //设备系统
        String os = userAgent.getPlatform().getName();

        log.info("请求方法：{}", methodName);
        log.info("请求参数：{}", arguments);
        log.info("请求描术：{}", description);
        log.info("请求URL：{}", url);
        log.info("请求IP地址：{}", ipAddr);
        log.info("请求浏览器：{}", browserName);
        log.info("请求设备系统：{}", os);

        //TODO 当前用户信息

        //TODO 异步保存系统日志
    }

}
