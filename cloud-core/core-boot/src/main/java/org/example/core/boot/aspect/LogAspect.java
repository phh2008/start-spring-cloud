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
import org.example.core.boot.support.LogAspectHandler;
import org.example.core.common.annotation.Log;
import org.example.core.common.context.UserContextHandler;
import org.example.core.common.dto.LogInfo;
import org.example.core.common.jwt.IJwtInfo;
import org.example.core.tool.utils.JsonUtils;
import org.example.core.tool.utils.WebUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * 日志切面
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/27
 */
@Order(1)
@Aspect
@Slf4j
@Component
public class LogAspect {

    private LogAspectHandler logAspectHandler;

    private Executor taskExecutor;

    public LogAspect(ObjectProvider<LogAspectHandler> handlers, ObjectProvider<Executor> taskExecutor) {
        this.logAspectHandler = handlers.getIfAvailable();
        this.taskExecutor = taskExecutor.getIfAvailable();
    }

    /**
     * 切入点
     */
    @Pointcut("execution(public * org.example..*.controller..*.*(..))@annotation(org.example.core.common.annotation.Log)")
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
        handleRequestLog(joinPoint, null);
    }

    private void handleRequestLog(JoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log action = method.getAnnotation(Log.class);
        if (action == null) {
            return;
        }
        LogInfo logInfo = new LogInfo();
        //请求时间
        logInfo.setRequestTime(LocalDateTime.now());
        //方法名称
        String methodName = method.getDeclaringClass().getName() + "." + method.getName() + "()";
        logInfo.setMethod(methodName);
        //请求参数
        String arguments = "";
        Object[] args = joinPoint.getArgs();
        try {
            arguments = JsonUtils.writeAsString(args);
        } catch (Exception e) {
            log.error("parse json error", e);
        }
        logInfo.setArgs(arguments);
        //日志描述
        String description = action.value();
        logInfo.setDescription(description);
        // 获取request
        HttpServletRequest request = WebUtils.getRequest();
        //请求URL
        String url = request.getRequestURL().toString();
        logInfo.setUrl(url);
        // 设置IP地址
        String ipAddr = ServletUtil.getClientIP(request);
        logInfo.setIp(ipAddr);
        // 设置浏览器和设备系统
        String userAgentHeader = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgentParser.parse(userAgentHeader);
        Browser browser = userAgent.getBrowser();
        //浏览器名称与版本
        String browserName = browser.getName() + ":" + browser.getVersion(userAgentHeader);
        //设备系统
        String os = userAgent.getPlatform().getName();
        logInfo.setBrowser(browserName);
        logInfo.setOs(os);
        //异常
        logInfo.setException(ex);
        //当前用户信息
        IJwtInfo jwtInfo = UserContextHandler.getJwtInfo();
        if (jwtInfo != null) {
            logInfo.setUserId(jwtInfo.getId());
            logInfo.setUsername(jwtInfo.getUserName());
            logInfo.setRealName(jwtInfo.getRealName());
        }
        log.info("logInfo {}", logInfo);
        //处理日志信息
        if (this.logAspectHandler != null) {
            CompletableFuture.runAsync(() -> {
                this.logAspectHandler.asyncHandle(logInfo);
            }, taskExecutor != null ? taskExecutor : ForkJoinPool.commonPool());
        }
    }

}
