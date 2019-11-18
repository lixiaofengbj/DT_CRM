package com.dt.service.fallback;

import com.dt.common.bean.ResponseBean;
import com.dt.service.MenuService;
import org.springframework.stereotype.Component;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 10:12
 */
@Component
public class MenuServiceFallback extends DefaultFallBackImpl implements MenuService {
    @Override
    public ResponseBean findById(Integer id) {
        return super.fallBack();
    }

    @Override
    public ResponseBean findList(Integer page, Integer pageSize, String menuName) {
        return super.fallBack();
    }
}
