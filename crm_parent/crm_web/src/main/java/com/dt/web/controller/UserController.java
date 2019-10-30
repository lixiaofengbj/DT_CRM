package com.dt.web.controller;

import com.dt.common.bean.ResponseBean;
import com.dt.common.utils.MD5;
import com.dt.pojo.CrmUserVo;
import com.dt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/login")
    @ApiOperation("登陆")
    public ResponseBean login(@ApiParam(name = "userName", value = "用户名") @RequestParam(name = "userName") String userName,
                              @ApiParam(name = "password", value = "密码") @RequestParam(name = "password") String password,
                              @ApiIgnore HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(userName, MD5.sign(password));
        subject.login(token);

        CrmUserVo user = (CrmUserVo) subject.getPrincipal();
        session.setAttribute("userSession", user);
        return null;
    }

    @GetMapping("/list")
    @ApiOperation("获取用户list")
    public ResponseBean userList(@ApiParam(name = "userName", value = "用户名")
                                 @RequestParam(required = false, name = "userName") String userName) {
        return this.userService.getUserList(userName);
    }
}
