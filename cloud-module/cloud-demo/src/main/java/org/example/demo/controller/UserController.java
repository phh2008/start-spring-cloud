package org.example.demo.controller;


import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.core.boot.handler.BaseController;
import org.example.core.boot.support.LocaleResult;
import org.example.core.common.annotation.Log;
import org.example.core.common.annotation.WithoutAuthentication;
import org.example.core.common.dto.UploadFileInfo;
import org.example.core.common.result.Result;
import org.example.core.tool.utils.StringUtils;
import org.example.demo.entity.User;
import org.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author phh
 * @since 2020-07-01
 */
@Api(value = "用户管理", tags = "用户管理")
@WithoutAuthentication
@RestController
@RequestMapping("/demo/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Log("用户例表")
    @ApiOperation(value = "用户例表", httpMethod = "GET", response = Result.class)
    @GetMapping("/list")
    public Result<Page<User>> list() {
        List<ParamFlowRule> paramRules = ParamFlowRuleManager.getRules();
        List<FlowRule> flowFules = FlowRuleManager.getRules();
        List<AuthorityRule> authorityRules = AuthorityRuleManager.getRules();
        System.out.println(">>>>>>>>paramRules：" + paramRules);
        System.out.println(">>>>>>>>flowFules：" + flowFules);
        System.out.println(">>>>>>>>authorityRules：" + authorityRules);
        Page<User> page = new Page<>(1, 2);
        page = userService.page(page, null);
        return Result.ok(page);
    }

    @Log("用户例表2")
    @ApiOperation(value = "用户例表2", httpMethod = "GET", response = Result.class)
    @GetMapping("/list2")
    public Result<Page<User>> list2(@RequestParam(defaultValue = "1") Long pageNo,
                                    @RequestParam(defaultValue = "10") Long pageSize,
                                    @RequestParam Map<String, Object> param) {
        Page<User> page = new Page<>(pageNo, pageSize);
        page = userService.queryPage(page, param);
        return LocaleResult.ok(page);
    }

    @ApiOperation(value = "添加随机用户", httpMethod = "GET", response = Result.class)
    @GetMapping("/addRandom")
    public Result<User> addRandom() {
        User user = new User();
        user.setPassword("123456");
        user.setUsername(StringUtils.random(6, StringUtils.RandomType.ALL));
        user.setRealName("临时用户");
        userService.save(user);
        return Result.ok(user);
    }


    @ApiOperation(value = "添加随机用户2", httpMethod = "GET", response = Result.class)
    @GetMapping("/addRandom2")
    public Result<User> addRandom2() {
        User user = userService.createRandomUser();
        return Result.ok(user);
    }


    @ApiOperation(value = "上传文件", response = Result.class)
    @PostMapping("/uploadFile")
    public Result<UploadFileInfo> uploadFile(MultipartFile file) {
        UploadFileInfo info = uploadFile(file, "/erp/user");
        return Result.ok(info);
    }

}
