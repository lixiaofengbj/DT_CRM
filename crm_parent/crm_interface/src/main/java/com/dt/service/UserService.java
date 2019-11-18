package com.dt.service;


import com.dt.common.bean.ResponseBean;
import com.dt.pojo.CrmUser;
import com.dt.pojo.CrmUserVo;
import com.dt.service.fallback.UserServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/5 10:56
 */
@FeignClient(value = "crm-service", fallback = UserServiceFallback.class)
public interface UserService {

    @GetMapping("/user/list")
    ResponseBean findList(@RequestParam(name = "page") Integer page,
                          @RequestParam(name = "pageSize") Integer pageSize,
                          @RequestParam(required = false, name = "userName") String userName);

    @PostMapping("/user/register")
    ResponseBean register(@RequestBody CrmUserVo userVo);

    @GetMapping("/user/userName")
    ResponseBean findByUserName(@RequestParam(name = "userName") String userName);

    @GetMapping("/user/roleId")
    ResponseBean findRoleIdByUserId(@RequestParam(name = "userId") Integer userId);
}
