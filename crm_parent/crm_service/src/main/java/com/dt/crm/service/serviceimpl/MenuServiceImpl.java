package com.dt.crm.service.serviceimpl;

import com.dt.mapper.MenuMapper;
import com.dt.mapper.RoleMapper;
import com.dt.pojo.CrmMenu;
import com.dt.pojo.CrmRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 14:32
 */
@Service
public class MenuServiceImpl {

    @Autowired
    private MenuMapper menuMapper;

    public PageInfo<CrmRole> findList(Integer page, Integer pageSize, String menuName) {
        PageHelper.startPage(page, pageSize);
        List<CrmRole> list = menuMapper.findList(menuName);
        return new PageInfo<CrmRole>(list);
    }

    public CrmMenu findById(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }
}
