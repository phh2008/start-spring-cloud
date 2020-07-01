package org.example.sys.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.core.common.annotation.Log;
import org.example.core.common.annotation.WithoutAuthentication;
import org.example.core.common.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/1
 */
@WithoutAuthentication
@Api(value = "testSentinel", tags = {"测试sentinel熔断降级"})
@RestController
public class TestSentinelController {


    @SentinelResource(value = "testSentinel", fallback = "fallback", blockHandler = "blockHandler")
    @Log("测试sentinel熔断降级")
    @RequestMapping(value = "/testSentinel", method = RequestMethod.POST)
    @ApiOperation(value = "测试sentinel熔断降级", httpMethod = "POST", response = Result.class)
    public Result<String> testSentinel(@RequestParam Long id) {
        System.out.println("id=" + id);
        if (id == 1002L) {
            //制造一个异常
            long ex = id / 0L;
        }
        return Result.ok("ok,ok,ok!!!");
    }

    public Result<String> fallback(Long id, Throwable ex) {
        System.err.println("触发fallback..........");
        return Result.ok("触发fallback");
    }

    public Result<String> blockHandler(Long id, BlockException ex) {
        System.err.println("blockHandler方法触发.......................");
        return Result.ok("blockHandler方法触发");
    }

}
