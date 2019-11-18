package com.dt.web.controller;

import com.dt.common.bean.ResponseBean;
import com.dt.common.constant.ErrorEnum;
import com.dt.pojo.CrmUserVo;
import com.dt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 15:00
 */
@RestController
@RequestMapping("/login")
@Api(tags = "登陆API")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    @ApiOperation("登陆")
    public ResponseBean login(@ApiParam(name = "userName", value = "用户名") @RequestParam(name = "userName") String userName,
                              @ApiParam(name = "password", value = "密码") @RequestParam(name = "password") String password) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            CrmUserVo user = (CrmUserVo) subject.getPrincipal();
            Session session = subject.getSession();
            session.setAttribute("userSession", user);
            return new ResponseBean(ErrorEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBean(ErrorEnum.ERROR);
    }

    /*@GetMapping("/logout")
    @ApiOperation("登出")
    public ResponseBean logout() {
        SecurityUtils.getSubject().logout();
        return new ResponseBean(ErrorEnum.SUCCESS);
    }*/
}
