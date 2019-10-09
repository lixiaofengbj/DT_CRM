package com.dt.service;


import com.dt.common.bean.ResponseBean;
import com.dt.service.fallback.UserServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/5 10:56
 */
@FeignClient(value = "crm-service", fallback = UserServiceFallback.class)
public interface UserService {

    @GetMapping("/user/list")
    ResponseBean getUserList(@RequestParam(required = false, value = "userName") String userName);
}
