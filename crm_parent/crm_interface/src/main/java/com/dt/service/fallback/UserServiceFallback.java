package com.dt.service.fallback;

import com.dt.common.bean.ResponseBean;
import com.dt.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/5 11:20
 */
@Component
public class UserServiceFallback extends DefaultFallBackImpl implements UserService {

    @Override
    public ResponseBean getUserList(String userName) {
        return super.fallBack();
    }
}
