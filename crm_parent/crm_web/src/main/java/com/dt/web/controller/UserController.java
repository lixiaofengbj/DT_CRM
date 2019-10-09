package com.dt.web.controller;

import com.dt.common.bean.ResponseBean;
import com.dt.pojo.CrmUserVo;
import com.dt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;

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
    public ResponseBean userList(@ApiParam(name = "userName", value = "用户名") @RequestParam(required = false) String userName) {
        return this.userService.getUserList(userName);
    }
}
