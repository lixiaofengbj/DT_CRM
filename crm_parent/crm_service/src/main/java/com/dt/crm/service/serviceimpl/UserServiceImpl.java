package com.dt.crm.service.serviceimpl;

import com.dt.common.bean.ResponseBean;
import com.dt.common.utils.CommonUtils;
import com.dt.common.utils.MD5;
import com.dt.mapper.UserMapper;
import com.dt.pojo.CrmUser;
import com.dt.pojo.CrmUserVo;
import com.dt.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/5 11:58
 */
@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询
     *
     * @param userName
     * @return
     */
    public PageInfo<CrmUser> findList(Integer page, Integer pageSize, String userName) {
        PageInfo<CrmUser> pageInfo = new PageInfo();
        PageHelper.startPage(page, pageSize);
        if (CommonUtils.isNotEmpty(userName)) {
            pageInfo.setList(userMapper.findList(userName));
        } else {
            pageInfo.setList(userMapper.selectAll());
        }
        return pageInfo;
    }

    public Boolean register(CrmUser crmUser) {
        crmUser.setPassword(MD5.sign(crmUser.getPassword()));
        return userMapper.insert(crmUser) != 0;
    }

    public CrmUser findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    public List<Integer> findRoleIdByUserId(Integer userId) {
        return userMapper.findRoleIdByUserId(userId);
    }


}
