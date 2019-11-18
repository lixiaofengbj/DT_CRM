package com.dt.crm.service.controller;

import com.dt.crm.service.serviceimpl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 14:29
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping("/list")
    public Object findList(@RequestParam(name = "page") Integer page,
                           @RequestParam(name = "pageSize") Integer pageSize,
                           @RequestParam(required = false, name = "menuName") String menuName) {
        return this.menuService.findList(page, pageSize, menuName);
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable(value = "id") Integer id) {
        return this.findById(id);
    }
}
