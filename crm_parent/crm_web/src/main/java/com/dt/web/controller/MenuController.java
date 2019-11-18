package com.dt.web.controller;

import com.dt.common.bean.ResponseBean;
import com.dt.service.MenuService;
import com.dt.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 14:24
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单API")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    @ApiOperation("获取菜单list")
    public ResponseBean findList(@ApiParam(name = "page", value = "页数")
                                 @RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @ApiParam(name = "pageSize", value = "条数")
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                 @ApiParam(name = "menuName", value = "角色名")
                                 @RequestParam(required = false, name = "menuName") String menuName) {
        return this.menuService.findList(page, pageSize, menuName);
    }
}
