package com.dt.crm.service.serviceimpl;

import com.dt.common.bean.ResponseBean;
import com.dt.common.utils.CommonUtils;
import com.dt.mapper.UserMapper;
import com.dt.pojo.CrmUser;
import com.dt.service.UserService;
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
    public Object getUserList(String userName) {
        /*if (CommonUtils.isNotEmpty(userName)) {
            CrmUser user = new CrmUser();
            user.setUserName(userName);
            return this.userMapper.select(user);
        }
        return this.userMapper.selectAll();*/
        System.out.println(userMapper.getClass()+"______________________________________");
        return this.userMapper.selectByUserName();
    }
}
