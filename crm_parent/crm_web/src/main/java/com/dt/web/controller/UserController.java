package com.dt.web.controller;

import com.dt.common.bean.ResponseBean;
import com.dt.pojo.CrmUserVo;
import com.dt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/4 14:21
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户API")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation("获取用户list")
    public ResponseBean findList(@ApiParam(name = "page", value = "页数")
                                 @RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @ApiParam(name = "pageSize", value = "条数")
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                 @ApiParam(name = "userName", value = "用户名")
                                 @RequestParam(required = false, name = "userName") String userName) {
        return this.userService.findList(page, pageSize, userName);
    }

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public ResponseBean register(@RequestBody @ApiParam(name = "crmUser", value = "用户信息") CrmUserVo crmUser) {
        return this.userService.register(crmUser);
    }
}
