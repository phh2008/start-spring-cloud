package org.example.core.boot.support;

import org.example.core.common.dto.LogInfo;

/**
 * 切面日志处理
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/8
 */
@FunctionalInterface
public interface LogAspectHandler {

    /**
     * 切面日志处理（这是个异步调用）
     *
     * @param logInfo
     */
    void asyncHandle(LogInfo logInfo);

}
