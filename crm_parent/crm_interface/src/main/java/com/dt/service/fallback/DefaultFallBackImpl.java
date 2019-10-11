package com.dt.service.fallback;

import com.dt.common.bean.ResponseBean;
import com.dt.common.constant.ErrorEnum;

/**
 * 公共默认fallback实现
 */
public class DefaultFallBackImpl {

    public ResponseBean fallBack() {
        return new ResponseBean(ErrorEnum.COMMUNICATION_ERROR);
    }
}
