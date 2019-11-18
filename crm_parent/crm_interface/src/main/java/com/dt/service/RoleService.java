package com.dt.service;

import com.dt.common.bean.ResponseBean;
import com.dt.pojo.CrmRoleVo;
import com.dt.service.fallback.RoleServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 10:10
 */
@FeignClient(value = "crm-service", fallback = RoleServiceFallback.class)
public interface RoleService {

    @GetMapping("/role/list")
    ResponseBean findList(@RequestParam(name = "page") Integer page,
                          @RequestParam(name = "pageSize") Integer pageSize,
                          @RequestParam(required = false, name = "roleName") String roleName);

    @GetMapping("/role/menuId")
    ResponseBean findMenuIdByRoleId(@RequestParam(name = "roleId") Integer roleId);

    @PostMapping("/role/save")
    ResponseBean save(@RequestBody CrmRoleVo crmRole);
}
