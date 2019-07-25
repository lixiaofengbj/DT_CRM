package com.dt.crm.service.aspect;

import com.alibaba.fastjson.JSONObject;
import com.dt.common.annotation.LogOperator;
import com.dt.common.aspect.AbstractRestControllerAspect;
import com.dt.common.utils.CommonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ControllerAspect extends AbstractRestControllerAspect {
    private Logger logger = LoggerFactory.getLogger(AbstractRestControllerAspect.class);
    private static final String POINT_CUT = "execution(* com.dt.crm.service.controller.*.*(..))";


    @Around(value = POINT_CUT, argNames = "proceedingJoinPoint")
    public Object methodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return super.methodAround(proceedingJoinPoint);
    }

    @Override
    protected void logPrint(LogOperator logOperator, ProceedingJoinPoint proceedingJoinPoint, Object[] args, Object resultData) {
        //TODO 添加日志处理逻辑
        String uuidFlag = CommonUtils.getRandowUUID();
        logger.info("日志编号:[{}],日志类型[{}],请求参数:{},描述:{}", uuidFlag, logOperator.type(), args, logOperator.description());
        logger.info("日志编号:[{}],result:{}", uuidFlag, JSONObject.toJSONString(resultData));
    }
}
