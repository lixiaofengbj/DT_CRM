package com.dt.crm.service.controller;

import com.dt.crm.service.serviceimpl.RoleServiceImpl;
import com.dt.pojo.CrmRole;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 13:21
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/list")
    public Object findList(@RequestParam(name = "page") Integer page,
                           @RequestParam(name = "pageSize") Integer pageSize,
                           @RequestParam(required = false, name = "roleName") String roleName) {
        return this.roleService.findList(page, pageSize, roleName);
    }

    @GetMapping("/menuId")
    public Object findMenuIdByRoleId(@RequestParam(name = "roleId") Integer roleId) {
        return this.roleService.findMenuIdByRoleId(roleId);
    }

    @PostMapping("/save")
    public Object sava(@RequestBody @ApiParam(name = "crmRole", value = "角色信息", required = true) CrmRole crmRole) {
        return this.roleService.save(crmRole);
    }
}
