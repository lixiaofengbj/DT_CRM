package com.dt.web.controller;

import com.dt.common.bean.ResponseBean;
import com.dt.pojo.CrmRoleVo;
import com.dt.pojo.CrmUserVo;
import com.dt.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 13:14
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色API")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @ApiOperation("获取角色list")
    public ResponseBean findList(@ApiParam(name = "page", value = "页数")
                                 @RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @ApiParam(name = "pageSize", value = "条数")
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                 @ApiParam(name = "roleName", value = "角色名")
                                 @RequestParam(required = false, name = "roleName") String roleName) {
        return this.roleService.findList(page, pageSize, roleName);
    }

    @PostMapping("/save")
    @ApiOperation("新建角色")
    public ResponseBean save(@RequestBody @ApiParam(name = "crmRole", value = "角色信息", required = true) CrmRoleVo crmRole) {
        return this.roleService.save(crmRole);
    }
}
