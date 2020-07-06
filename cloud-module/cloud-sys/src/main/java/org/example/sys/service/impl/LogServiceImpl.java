package org.example.sys.service.impl;

import org.example.core.common.mp.base.BaseServiceImpl;
import org.example.sys.dao.LogMapper;
import org.example.sys.entity.Log;
import org.example.sys.service.ILogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author phh
 * @since 2020-07-06
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<LogMapper, Log> implements ILogService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Log insertOrUpdate(Log log) {
        if (log == null) {
            return null;
        }
        if (log.getId() != null) {
            this.updateById(log);
        } else {
            this.save(log);
        }
        return log;
    }

}
