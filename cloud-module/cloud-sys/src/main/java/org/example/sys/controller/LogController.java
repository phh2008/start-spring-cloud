package org.example.sys.controller;

import io.swagger.annotations.Api;
import org.example.api.sys.dto.LogDTO;
import org.example.core.boot.handler.BaseController;
import org.example.core.common.annotation.WithoutAuthentication;
import org.example.core.common.result.Result;
import org.example.core.tool.utils.BeanCopierUtils;
import org.example.sys.entity.Log;
import org.example.sys.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/6
 */
@Api(value = "Log", tags = {"日志控制器"})
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    private ILogService logService;

    @WithoutAuthentication
    @PostMapping("/add")
    public Result<LogDTO> add(@RequestBody LogDTO dto) {
        Log log = BeanCopierUtils.copy(dto, Log.class);
        log = logService.insertOrUpdate(log);
        return Result.ok(BeanCopierUtils.copy(log, LogDTO.class));
    }


}
