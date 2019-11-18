package com.dt.service.fallback;

import com.dt.common.bean.ResponseBean;
import com.dt.pojo.CrmRoleVo;
import com.dt.service.RoleService;
import org.springframework.stereotype.Component;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 10:11
 */
@Component
public class RoleServiceFallback extends DefaultFallBackImpl implements RoleService {
    @Override
    public ResponseBean findMenuIdByRoleId(Integer roleId) {
        return super.fallBack();
    }

    @Override
    public ResponseBean save(CrmRoleVo crmRole) {
        return super.fallBack();
    }

    @Override
    public ResponseBean findList(Integer page, Integer pageSize, String roleName) {
        return super.fallBack();
    }


}
