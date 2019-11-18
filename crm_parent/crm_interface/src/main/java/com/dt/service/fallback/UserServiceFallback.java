package com.dt.service.fallback;

import com.dt.common.bean.ResponseBean;
import com.dt.pojo.CrmUser;
import com.dt.pojo.CrmUserVo;
import com.dt.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/5 11:20
 */
@Component
public class UserServiceFallback extends DefaultFallBackImpl implements UserService {

    @Override
    public ResponseBean findList(Integer page, Integer pageSize, String userName) {
        return super.fallBack();
    }

    @Override
    public ResponseBean register(CrmUserVo userVo) {
        return super.fallBack();
    }

    @Override
    public ResponseBean findByUserName(String userName) {
        return super.fallBack();
    }

    @Override
    public ResponseBean findRoleIdByUserId(Integer userId) {
        return super.fallBack();
    }
}
