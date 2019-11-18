package com.dt.mapper;

import com.dt.pojo.CrmRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<CrmRole> {

    List<CrmRole> findList(String roleName);

    List<Integer> findMenuIdByRoleId(Integer roleId);
}
