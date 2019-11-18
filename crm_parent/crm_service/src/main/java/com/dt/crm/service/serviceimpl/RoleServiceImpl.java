package com.dt.crm.service.serviceimpl;

import com.dt.common.utils.CommonUtils;
import com.dt.mapper.RoleMapper;
import com.dt.pojo.CrmRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 13:36
 */
@Service
public class RoleServiceImpl {

    @Autowired
    private RoleMapper roleMapper;

    public PageInfo<CrmRole> findList(Integer page, Integer pageSize, String roleName) {
        PageHelper.startPage(page, pageSize);
        List<CrmRole> list = roleMapper.findList(roleName);
        return new PageInfo<CrmRole>(list);
    }

    public List<Integer> findMenuIdByRoleId(Integer roleId) {
        return roleMapper.findMenuIdByRoleId(roleId);
    }

    public Boolean save(CrmRole crmRole) {
        if (CommonUtils.isNotNull(crmRole.getId())) {
            return this.roleMapper.updateByPrimaryKeySelective(crmRole) != 0;
        }
        return this.roleMapper.insertSelective(crmRole) != 0;
    }
}
