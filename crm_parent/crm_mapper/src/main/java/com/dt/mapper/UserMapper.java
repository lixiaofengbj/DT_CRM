package com.dt.mapper;

import com.dt.pojo.CrmUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<CrmUser> {

    List<CrmUser> findList(String userName);

    CrmUser findByUserName(String userName);

    List<Integer> findRoleIdByUserId(Integer userId);
}
