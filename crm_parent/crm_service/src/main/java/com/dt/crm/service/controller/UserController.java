package com.dt.crm.service.controller;

import com.dt.crm.service.serviceimpl.UserServiceImpl;
import com.dt.pojo.CrmUser;
import com.dt.pojo.CrmUserVo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/5 13:50
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/list")
    public Object findList(@RequestParam(name = "page") Integer page,
                           @RequestParam(name = "pageSize") Integer pageSize,
                           @RequestParam(required = false, name = "userName") String userName) {
        return this.userService.findList(page, pageSize, userName);
    }

    @PostMapping("/register")
    public Object register(@RequestBody @ApiParam(name = "crmUser", value = "用户信息", required = true) CrmUser crmUser) {
        return this.userService.register(crmUser);
    }

    @GetMapping("/userName")
    public Object findByUserName(@RequestParam(name = "userName") String userName) {
        CrmUser user = this.userService.findByUserName(userName);
        return user;
    }

    @GetMapping("/roleId")
    public Object findRoleIdByUserId(@RequestParam(name = "userId") Integer userId) {
        return this.userService.findRoleIdByUserId(userId);
    }
}
