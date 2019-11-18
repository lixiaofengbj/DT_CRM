package com.dt.service;

import com.dt.common.bean.ResponseBean;
import com.dt.service.fallback.MenuServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.websocket.server.PathParam;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 10:10
 */
@FeignClient(value = "crm-service", fallback = MenuServiceFallback.class)
public interface MenuService {

    @GetMapping("/menu/{id}")
    ResponseBean findById(@PathVariable(value = "id") Integer id);

    @GetMapping("/menu/list")
    ResponseBean findList(@RequestParam(name = "page") Integer page,
                          @RequestParam(name = "pageSize") Integer pageSize,
                          @RequestParam(required = false, name = "menuName") String menuName);
}
