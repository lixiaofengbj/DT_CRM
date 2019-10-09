package com.dt.crm.service.controller;

import com.dt.crm.service.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Object getUserList(@RequestParam(required = false, name = "userName") String userName) {
        return this.userService.getUserList(userName);
    }

}
