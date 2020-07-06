package org.example.api.sys.feign;

import org.example.api.sys.dto.LogDTO;
import org.example.core.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/6
 */
@FeignClient(name = "cloud-sys")
public interface LogFeign {


    /**
     * 新增日志
     *
     * @param dto
     * @return result
     * @author phh
     * @date 2020/7/6
     * @version V1.0
     */
    @RequestMapping(value = "/log/add", method = RequestMethod.POST)
    Result<LogDTO> add(@RequestBody LogDTO dto);

}
