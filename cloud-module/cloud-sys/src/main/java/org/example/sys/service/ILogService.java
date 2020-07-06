package org.example.sys.service;

import org.example.core.common.mp.base.BaseService;
import org.example.sys.entity.Log;

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @author phh
 * @since 2020-07-06
 */
public interface ILogService extends BaseService<Log> {

    /**
     * 创建或更新
     *
     * @param log
     * @return
     */
    Log insertOrUpdate(Log log);

}
