package com.dt.crm.service.serviceimpl;

import com.dt.common.bean.ResponseBean;
import com.dt.mapper.UserMapper;
import com.dt.pojo.CrmUser;
import com.dt.service.UserService;
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

    public Object getUserList() {
        List list = null;
        try {
            Integer all = this.userMapper.aaa();
            return all;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
